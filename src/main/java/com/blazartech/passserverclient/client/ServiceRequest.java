/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.passserverclient.client;

/**
 *
 * @author aar1069
 */
public record ServiceRequest (
        String action,
        String resource,
        String dbUser
        ){

    public ServiceRequest(String resource, String dbUser) {
        this("GET", resource, dbUser);
    }

        
}
