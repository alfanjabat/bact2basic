/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppob.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author opaw
 */
@Entity
@Table(name="t_payment")
public class Payment {
    
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    
    @NotNull
    @NotEmpty
    @Column(name = "time", nullable = false, unique = false)
    private String time;
    
    @NotNull
    @NotEmpty
    @Column(name = "locket", nullable = false, unique = false)
    private String locket;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_bill", nullable = false)
    private Bill bill;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocket() {
        return locket;
    }

    public void setLocket(String locket) {
        this.locket = locket;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
