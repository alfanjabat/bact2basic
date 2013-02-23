/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppob.server.service.impl;

import com.ppob.server.dao.BillDao;
import com.ppob.server.dao.PaymentDao;
import com.ppob.server.dao.ProductDao;
import com.ppob.server.domain.Bill;
import com.ppob.server.domain.Payment;
import com.ppob.server.domain.Product;
import com.ppob.server.service.TransactionService;
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
@SuppressWarnings("unchecked")
@Service("transactionService")
@Transactional
public class TransactionServiceImpl implements TransactionService{
    
    @Autowired private ProductDao productDao;
    @Autowired private BillDao billDao;
    @Autowired private PaymentDao paymentDao;

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void delete(Product product) {
        productDao.delete(product);
    }

    @Override
    public Product findProductById(String id) {
        if(id == null || id.trim().length() < 1){
            return null;
        }
        return productDao.findOne(id);
    }

    @Override
    public Page<Product> findAllProduct(Pageable pagination) {
        return productDao.findAll(pagination);
    }  

    @Override
    public void save(Bill bill) {
        billDao.save(bill);
    }

    @Override
    public void delete(Bill bill) {
        billDao.delete(bill);
    }

    @Override
    public Bill findBillById(String id) {
        if(id == null || id.trim().length() < 1){
            return null;
        }
        return billDao.findOne(id);
    }

    @Override
    public Page<Bill> findAllBill(Pageable pagination) {
        return billDao.findAll(pagination);
    }

    @Override
    public void save(Payment payment) {
        paymentDao.save(payment);
    }

    @Override
    public void delete(Payment payment) {
        paymentDao.delete(payment);
    }

    @Override
    public Payment findPaymentById(String id) {
        if(id == null || id.trim().length() < 1){
            return null;
        }
        return paymentDao.findOne(id) ;
    }

    @Override
    public Page<Payment> findAllPayment(Pageable pagination) {
        return paymentDao.findAll(pagination);
    }

    @Override
    public Product findProductByName(String name) {
        if(!StringUtils.hasText(name)){
            return null;
        }
        return productDao.findByName(name);
    }

    @Override
    public Long countAllProdutct() {
        return productDao.count();
    }
}
