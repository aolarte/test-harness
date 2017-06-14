package com.andresolarte.harness.spring4.jdbc.tx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TxTest.class})
@Transactional
public class TxDAOTest {

    @Autowired
    private TxDAO dao;

    @Test
    public void test1 () {
        dao.insertRecord("john");
        assertEquals(dao.countRecords(), 1);
    }

    @Test
    public void test2 () {
        dao.insertRecord("mike");
        assertEquals(dao.countRecords(), 1);
    }
}
