/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppob.server.service.impl;

import static org.junit.Assert.*;
import com.ppob.server.domain.Product;
import com.ppob.server.domain.User;
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
public class ProductServiceTestIT {
    @Autowired TransactionService productService;
    
    @Test
    public void testFindAll() {
        Page<Product> result = productService.findAllProduct(new PageRequest(0, productService.countAllProdutct().intValue()));
        assertTrue(result.getTotalElements() > 0);
    }
    
    @Test
    public void testFindById(){
        Product p = productService.findProductById("1");
        assertNotNull(p);
        assertEquals("1S", p.getCode());
        assertEquals("Speedy", p.getName());
    
        assertNull(productService.findProductById(null));
        assertNull(productService.findProductById(""));
    }
    
    @Test
    public void testFindByName() {
        assertNotNull(productService.findProductByName("Speedy"));
        assertNull(productService.findProductByName("PLN"));
    }
}
