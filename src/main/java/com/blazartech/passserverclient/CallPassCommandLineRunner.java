/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.passserverclient;

import com.blazartech.passserverclient.client.PassServiceClient;
import com.blazartech.passserverclient.client.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author aar1069
 */
@Component
@Slf4j
public class CallPassCommandLineRunner implements CommandLineRunner {

    @Autowired
    private PassServiceClient client;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("calling the server");
        
        ServiceResponse response = client.getPassword("test", "scott");
        log.info("response = {}", response);
    }
    
}
