/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.passserverclient.client;

import java.io.IOException;
import java.net.StandardProtocolFamily;
import java.net.UnixDomainSocketAddress;
import java.nio.channels.SocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

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
    
    @Bean
    @Scope("prototype")
    public SocketChannel channel() throws IOException {
        return SocketChannel.open(StandardProtocolFamily.UNIX);
    }
}
