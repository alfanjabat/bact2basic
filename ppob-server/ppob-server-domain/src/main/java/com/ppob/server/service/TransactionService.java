/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppob.server.service;

import com.ppob.server.domain.Bill;
import com.ppob.server.domain.Payment;
import com.ppob.server.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author opaw
 */
public interface TransactionService {
    void save(Product product);
    void delete(Product product);
    Product findProductById(String id);
    Product findProductByName(String name);
    Page<Product> findAllProduct(Pageable pagination);
    Long countAllProdutct();
    
    
    void save(Bill bill);
    void delete(Bill bill);
    Bill findBillById(String id);
    Page<Bill> findAllBill(Pageable pagination);
    
    void save(Payment payment);
    void delete(Payment payment);
    Payment findPaymentById(String id);
    Page<Payment> findAllPayment(Pageable pagination);
}
