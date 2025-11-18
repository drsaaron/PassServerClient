/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.passserverclient.client;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author aar1069
 */
@Data
@NoArgsConstructor
public class ServiceRequest {
    
    @Setter(AccessLevel.NONE)
    private final String action = "GET";
    
    private String resource;
    private String dbUser;

    public ServiceRequest(String resource, String dbUser) {
        this.resource = resource;
        this.dbUser = dbUser;
    }
    
    
}
