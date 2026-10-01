/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.passserverclient.client;

/**
 *
 * @author aar1069
 */
public record ServiceResponse (String resource, String dbUser, String password, String error) {
    
}
