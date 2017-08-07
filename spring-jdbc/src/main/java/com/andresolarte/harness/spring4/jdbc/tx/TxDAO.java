package com.andresolarte.harness.spring4.jdbc.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TxDAO {

    @Autowired JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.NEVER)
    public int countRecords() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM TEST;", Integer.class);
    }

    @Transactional
    public void insertRecord(String name) {
        jdbcTemplate.update("INSERT INTO TEST (name) VALUES (?);", name);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertLog(String log) {
        jdbcTemplate.update("INSERT INTO LOG (log_msg) VALUES (?);", log);
    }
}
