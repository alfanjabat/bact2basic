/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppob.server.service.impl;

import static org.junit.Assert.*;
import com.ppob.server.domain.Bill;
import com.ppob.server.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author opaw
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:com/ppob/server/**/applicationContext.xml")
public class BillServiceTestIT {
    @Autowired TransactionService billService;
    
    @Test
    public void testFindAll(){
        Page<Bill> result = billService.findAllBill(new PageRequest(0, 10));
        assertEquals(new Integer(1), new Integer(result.getNumberOfElements()));
    }
    
    @Test
    public void testFindById(){
        Bill b = billService.findBillById("1");
        assertNotNull(b);
        assertEquals("2013-08-08", b.getPeriode());
        assertEquals("100000", b.getValue());
        assertEquals("10000", b.getCharge());
        assertEquals("1", b.getProduct().getId());
    
        assertNull(billService.findBillById(null));
        assertNull(billService.findBillById(""));
    }
}
