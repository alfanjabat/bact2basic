/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppob.server.service.impl;

import com.ppob.server.dao.ProductDao;
import com.ppob.server.domain.Product;
import com.ppob.server.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
/**
 *
 * @author opaw
 */
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService{
    
    @Autowired private ProductDao productDao;

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void delete(Product product) {
        productDao.delete(product);
    }

    @Override
    public Product findProduckById(String id) {
        if(id == null || id.trim().length() < 1){
            return null;
        }
        return productDao.findOne(id);
    }

    @Override
    public Page<Product> findAllProduck(Pageable pagination) {
        return productDao.findAll(pagination);
    }  
}
