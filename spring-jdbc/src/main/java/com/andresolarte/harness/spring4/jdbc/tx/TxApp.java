package com.andresolarte.harness.spring4.jdbc.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TxApp {

    @Autowired
    private TestTxService service;

    @Autowired
    private ManualTxDAO manualTxDAO;

    public void run() {
//        service.insertRecord("name1");
//
//        service.insertRecord("name2");
//
//        service.insertRecord("name3");
//
//        System.out.println(service.countRecords());
        manualTxDAO.test();
    }
}
