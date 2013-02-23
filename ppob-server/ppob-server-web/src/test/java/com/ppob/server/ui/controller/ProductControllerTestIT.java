/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppob.server.ui.controller;

import com.jayway.restassured.authentication.FormAuthConfig;
import org.junit.Test;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import com.ppob.server.domain.Product;

/**
 *
 * @author opaw
 */
public class ProductControllerTestIT {
    private String target = "http://localhost:10000/master/product";
    private String login = "http://localhost:10000/j_spring_security_check";
    private String username = "endy";
    private String password = "123";

    @Test
    public void testSaveInvalid(){
        Product p = new Product();
        
        given()
            .auth().form(username, password, new FormAuthConfig(login, "j_username", "j_password"))
            .contentType("application/json")
            .body(p)
            .expect().statusCode(400).when().post(target);
    }
    
//    @Test
//    public void testSaveUpdateDelete() {
//
//        Product p = new Product();
//        p.setCode("3P");
//        p.setName("PLN");
//
//        String id = testSave(target, p);
//        System.out.println("Id : " + id);
//        testGetExistingById(id, p);
//        
//        p.setCode("Change Code");
//        p.setName("Change Name");
//        
//        testUpdateExisting(id, p);
//        testGetExistingById(id, p);
//        testDeleteExistingById(id);
//    }
    
    private String testSave(String target, Product p) {
        String location = given()
                .auth().form(username, password, new FormAuthConfig(login, "j_username", "j_password"))
                .contentType("application/json")
                .body(p)
                .expect().statusCode(201).when().post(target)
                .getHeader("Location");

        assertNotNull(location);
        assertTrue(location.startsWith(target));

        String[] locationSplit = location.split("/");
        String id = locationSplit[locationSplit.length - 1];

        return id;
    }
    
    private void testGetExistingById(String id, Product p) {
        with().header("Accept", "application/json")
                .auth().form(username, password, new FormAuthConfig(login, "j_username", "j_password"))
                .expect()
                .statusCode(200)
                .body("code", equalTo(p.getCode()), "name", equalTo(p.getName()))
                .when().get(target + "/" + id);
    }
    
    private void testUpdateExisting(String id, Product p) {

        given()
                .auth().form(username, password, new FormAuthConfig(login, "j_username", "j_password"))
                .contentType("application/json")
                .body(p)
                .expect()
                .statusCode(200).when().put(target + "/" + id);
    }
    
    private void testDeleteExistingById(String id) {
        given().auth().form(username, password, new FormAuthConfig(login, "j_username", "j_password"))
                .expect().statusCode(200).when().delete(target + "/" + id);

        given().auth().form(username, password, new FormAuthConfig(login, "j_username", "j_password"))
                .expect().statusCode(404).when().get(target + "/" + id);
    }
    
    @Test
    public void testGetExistingById() {
        with().header("Accept", "application/json")
                .auth().form(username, password, new FormAuthConfig(login, "j_username", "j_password"))
                .expect()
                .statusCode(200)
                .body("id", equalTo("2"),
                "code", equalTo("2P"),
                "name", equalTo("PAM")).when()
                .get(target + "/" + "2");
    }
    
//    @Test
//    public void testGetNonExistentById() {
//        with()
//                .auth().form(username, password, new FormAuthConfig(login, "j_username", "j_password"))
//                .expect().statusCode(404).when().get(target + "/" + "/nonexistentconfig");
//    }
    
    @Test
    public void testFindAll() {
        with()
                .auth().form(username, password, new FormAuthConfig(login, "j_username", "j_password"))
                .header("Accept", "application/json").expect().statusCode(200)
                .body("id", hasItems("1","2")).when().get(target);
    }
}
