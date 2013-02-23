/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppob.server.dao;

import com.ppob.server.domain.Payment;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author opaw
 */
public interface PaymentDao extends PagingAndSortingRepository<Payment, String> {
    
}
