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
@Table(name="t_bill")
public class Bill {
    
@Id
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid2")    
private String id;

@NotNull
@NotEmpty
@Column(name = "periode", nullable = false , unique = false)
private String periode;

@NotNull
@NotEmpty
@Column(name = "value", nullable = false , unique = false)
private String value;

@NotNull
@NotEmpty
@Column(name = "charge", nullable = false , unique = false)
private String charge;

@NotNull
@ManyToOne
@JoinColumn(name = "id_product", nullable = false)
private Product product;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
