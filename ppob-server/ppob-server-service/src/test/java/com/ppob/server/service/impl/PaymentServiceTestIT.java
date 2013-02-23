/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppob.server.service.impl;

import static org.junit.Assert.*;
import com.ppob.server.domain.Product;
import com.ppob.server.service.PaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.print.resources.serviceui;

/**
 *
 * @author opaw
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:com/ppob/server/**/applicationContext.xml")
public class PaymentServiceTestIT {
    
    @Autowired private PaymentService paymentService;
    
    @Test
    public void testFindAll(){
        Page<Product> result = paymentService.findAllProduck(new PageRequest(0, 10));
        assertEquals(new Integer(1), new Integer(result.getNumberOfElements()));
    }
    
    @Test
    public void testFindById(){
        Product p = paymentService.findProduckById("1");
        assertNotNull(p);
        assertEquals("1S", p.getCode());
        assertEquals("speedy", p.getName());
    
        assertNull(paymentService.findProduckById(null));
        assertNull(paymentService.findProduckById(""));
    }
    
}
