/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.passserverclient.client;

import java.net.UnixDomainSocketAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * configure the unix socket
 * @author aar1069
 */
@Configuration
public class SocketConfiguration {
    
    @Value("${pass.socketPath}")
    private String socketPath;

    @Bean
    public UnixDomainSocketAddress unixSocket() {
        return UnixDomainSocketAddress.of(socketPath);
    }
}
