#! /usr/bin/env python3

import socket
import json

socketPath = '/tmp/my_pass_socket'

with socket.socket(socket.AF_UNIX, socket.SOCK_STREAM) as client:
    try:
        # Connect to the server
        client.connect(socketPath)
        
        # Send byte data
        request = {
            "action": "GET",
            "dbUser": "scott",
            "resource": "test"
        }
        message = json.dumps(request)
        client.sendall(message.encode())

        # read response
        response = client.recv(1024)
        print(f"received reply: {response}")
        
    except socket.error as e:
        print(f"Connection error: {e}")
