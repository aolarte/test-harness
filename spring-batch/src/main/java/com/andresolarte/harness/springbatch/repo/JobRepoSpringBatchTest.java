package com.andresolarte.harness.springbatch.repo;

import com.andresolarte.harness.springbatch.model.Transaction;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.item.support.ListItemWriter;
import org.springframework.batch.support.DatabaseType;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Date;

@Configuration
@EnableBatchProcessing
@ComponentScan(basePackageClasses = JobRepoSpringBatchTest.class)
public class JobRepoSpringBatchTest {


    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private JobBuilderFactory jobs;

    @Bean
    protected ItemReader<Transaction> reader() {
        return new ListItemReader<>(Arrays.asList(build(1), build(2), build(3)));
    }

    private Transaction build(int id) {
        Transaction transaction = new Transaction();
        transaction.setAmount(id);
        transaction.setUsername("t" + id);
        return transaction;
    }

    @Bean
    protected ItemProcessor<Transaction, Transaction> processor() {
        return transaction -> {
            System.out.println("Get transaction");
            transaction.setTransactionDate(new Date());
            return transaction;
        };
    }

    @Bean
    protected ListItemWriter<Transaction> writer() {
        return new ListItemWriter<>();
    }


    @Bean
    protected Step step1(ItemReader<Transaction> reader,
                         ItemProcessor<Transaction, Transaction> processor,
                         ItemWriter<Transaction> writer) {
        return steps.get("step1").<Transaction, Transaction>chunk(10)
                .reader(reader).processor(processor).writer(writer).build();
    }

    @Bean
    public Job job(Step step1) {
        return jobs.get("firstBatchJob").start(step1).build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        databasePopulator.addScript(new ClassPathResource("org/springframework/batch/core/schema-h2.sql"));
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
        ;
        return dataSource;
    }


    @Bean
    public JobRepository jobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDatabaseType(DatabaseType.H2.name());
        factory.setDataSource(dataSource());
        factory.setTransactionManager(transactionManager());
        return factory.getObject();
    }

    @Bean
    public JobLauncher jobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }

    public static void main(String... args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(JobRepoSpringBatchTest.class);

        JobLauncher launcher = context.getBean(JobLauncher.class);
        Job job = context.getBean(Job.class);
        launcher.run(job, new JobParameters());
    }

}
