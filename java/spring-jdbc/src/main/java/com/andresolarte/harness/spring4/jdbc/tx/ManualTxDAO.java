package com.andresolarte.harness.spring4.jdbc.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
public class ManualTxDAO {
    @Autowired
    private PlatformTransactionManager txManager;

    @Autowired
    private TxDAO dao;

    public void test() {
        TransactionDefinition definition= new DefaultTransactionDefinition();
        TransactionStatus txStatus = txManager.getTransaction(definition);

        try {
            dao.insertRecord("Name #1");
            System.out.println(dao.countRecords());

            txManager.commit(txStatus);

        } catch (Throwable ex) {
            txManager.rollback(txStatus);
        }


    }
}
