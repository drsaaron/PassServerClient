/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.blazartech.passserverclient.client;

/**
 *
 * @author aar1069
 */
public interface PassServiceClient {
    
    public ServiceResponse getPassword(String resource, String dbUser);
}
