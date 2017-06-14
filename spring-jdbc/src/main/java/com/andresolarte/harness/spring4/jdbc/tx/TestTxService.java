package com.andresolarte.harness.spring4.jdbc.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TestTxService {

    @Autowired
    private TxDAO dao;

    @Transactional
    public void insertRecord(String name) {
        dao.insertRecord(name);
        System.out.println(dao.countRecords());
    }

    @Transactional
    public int countRecords() {
        return dao.countRecords();
    }

}
