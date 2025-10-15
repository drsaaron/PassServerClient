/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.passserverclient.client;

import lombok.Data;

/**
 *
 * @author aar1069
 */
@Data
public class ServiceResponse {
    
    private String resource;
    private String dbUser;
    private String password;
    private String error;
}
