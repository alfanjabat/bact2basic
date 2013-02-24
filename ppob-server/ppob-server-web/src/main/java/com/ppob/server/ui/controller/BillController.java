/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppob.server.ui.controller;

import com.ppob.server.domain.Bill;
import com.ppob.server.service.TransactionService;
import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
public class BillController {
    @Autowired private TransactionService transactionService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping("/transaction/bill/{id}")
    @ResponseBody
    public Bill findById(@PathVariable String id){
        return transactionService.findBillById(id);
    }
    
    @RequestMapping(value = "/transaction/bill", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid Bill bill, HttpServletRequest request, HttpServletResponse response){
        transactionService.save(bill);
        String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, bill.getId());
        response.setHeader("Location", uri.toASCIIString());
    }
    
    @RequestMapping(method= RequestMethod.PUT, value="/transaction/bill/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @RequestBody @Valid Bill b){
        Bill bill = transactionService.findBillById(id);
        if(bill == null){
            logger.warn("User dengan id [{}] tidak ditemukan", id);
            throw new IllegalStateException();
        }
        b.setId(bill.getId());
        transactionService.save(b);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/transaction/bill/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        Bill b = transactionService.findBillById(id);
        if (b == null) {
            logger.warn("User dengan id [{}] tidak ditemukan", id);
            throw new IllegalStateException();
        }
        transactionService.delete(b);
    }
    
    @RequestMapping(value = "/transaction/bill", method = RequestMethod.GET)
    @ResponseBody
    public List<Bill> findAll(Pageable pageable, HttpServletResponse response) {
        List<Bill> result = transactionService.findAllBill(pageable).getContent();
        return result;
    }
}
