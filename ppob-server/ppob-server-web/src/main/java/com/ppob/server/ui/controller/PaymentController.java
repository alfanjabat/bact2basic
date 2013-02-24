/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppob.server.ui.controller;

import com.ppob.server.domain.Payment;
import com.ppob.server.service.TransactionService;
import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

/**
 *
 * @author opaw
 */
@Controller
public class PaymentController {
    @Autowired private TransactionService transactionService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping("/transaction/payment/{id}")
    @ResponseBody
    public Payment findById(@PathVariable String id){
        return transactionService.findPaymentById(id);
    }
    
    @RequestMapping(value = "/transaction/payment", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid Payment payment, HttpServletRequest request, HttpServletResponse response){
        transactionService.save(payment);
        String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, payment.getId());
        response.setHeader("Location", uri.toASCIIString());
    }
    
    @RequestMapping(method= RequestMethod.PUT, value="/transaction/payment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @RequestBody @Valid Payment p){
        Payment payment = transactionService.findPaymentById(id);
        if(payment == null){
            logger.warn("User dengan id [{}] tidak ditemukan", id);
            throw new IllegalStateException();
        }
        p.setId(payment.getId());
        transactionService.save(p);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/transaction/payment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        Payment p = transactionService.findPaymentById(id);
        if (p == null) {
            logger.warn("User dengan id [{}] tidak ditemukan", id);
            throw new IllegalStateException();
        }
        transactionService.delete(p);
    }
    
    @RequestMapping(value = "/transaction/payment", method = RequestMethod.GET)
    @ResponseBody
    public List<Payment> findAll(Pageable pageable,HttpServletResponse response) {
        List<Payment> result = transactionService.findAllPayment(pageable).getContent();
        return result;
    }
}
