/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppob.server.service;

import com.ppob.server.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author opaw
 */
public interface PaymentService {
    void save(Product product);
    void delete(Product product);
    Product findProduckById(String id);
    Page<Product> findAllProduck(Pageable pagination);
}
