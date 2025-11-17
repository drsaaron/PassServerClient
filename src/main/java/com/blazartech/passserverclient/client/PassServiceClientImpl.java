/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.passserverclient.client;

import java.io.IOException;
import java.net.StandardProtocolFamily;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

/**
 *
 * @author aar1069
 */
@Component
@Slf4j
public class PassServiceClientImpl implements PassServiceClient {

    @Autowired
    private UnixDomainSocketAddress unixSocket;

    @Autowired
    private ObjectMapper objectMapper;

    private static final int BUFFER_SIZE = 1024;

    @Override
    public ServiceResponse getPassword(String resource, String dbUser) {
        log.info("requesting password for resource {} and user {}", resource, dbUser);

        // build the request
        ServiceRequest request = new ServiceRequest();
        request.setDbUser(dbUser);
        request.setResource(resource);

        // convert to json
        String requestJson = objectMapper.writeValueAsString(request);

        // send to the unix socket and read response string.  based on https://www.baeldung.com/java-unix-domain-socket
        try {
            // access the Unix socket
            SocketChannel channel = SocketChannel.open(StandardProtocolFamily.UNIX);
            channel.connect(unixSocket);

            // send message
            sendSocketMessage(channel, requestJson);

            // read the response
            Optional<String> responseJsonOpt = readSocketMessage(channel);
            if (responseJsonOpt.isPresent()) {
                String responseJson = responseJsonOpt.get();
                ServiceResponse response = objectMapper.readValue(responseJson, ServiceResponse.class);

                // is there an error?
                if (response.getError() != null) {
                    throw new RuntimeException("service error: " + response.getError());
                }
                return response;
            } else {
                throw new RuntimeException("error reading response from service");
            }
        } catch (IOException e) {
            throw new RuntimeException("error calling service: " + e.getMessage(), e);
        }
    }

    private void sendSocketMessage(SocketChannel channel, String message) throws IOException {
        log.info("sending message {}", message);
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        buffer.clear();
        buffer.put(message.getBytes());
        buffer.flip();

        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }

    }

    private Optional<String> readSocketMessage(SocketChannel channel) throws IOException {
        log.info("reading reply");
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        int bytesRead = channel.read(buffer);
        if (bytesRead < 0) {
            return Optional.empty();
        }

        byte[] bytes = new byte[bytesRead];
        buffer.flip();
        buffer.get(bytes);
        String message = new String(bytes);
        return Optional.of(message);
    }
}
