#! /usr/bin/env python3

# see https://www.baeldung.com/linux/python-unix-sockets

import socket
import os
import time
import threading
import subprocess
import json

def getUserPassword(resource, dbUser):
    cmd = "pass " + resource + "/" + dbUser
    process = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=True, text=True)
    output, error = process.communicate()
    return output.rstrip()

def logMessage(message):
    print('thread ' + str(threading.current_thread().ident) + ': ' + message)

# send a response object
def sendResponse(connection, resource, dbUser, password, error):
    response = None
    if (not error):
        response = {
            "resource": resource,
            "dbUser": dbUser,
            "password": password
        }
    else:
        response = {
            "error": error
        }
        
    responseJson = json.dumps(response)
    connection.sendall(responseJson.encode())
          
# handle a connection
def handleConnection(connection):

    try:
        logMessage('handling connection in thread')
        data = connection.recv(1024)
        if not data:
            return
        logMessage('Received data:' + data.decode())
        
        requestObject = json.loads(data)
        
        # get the data
        action = requestObject["action"]
        resource = requestObject["resource"]
        dbUser = requestObject["dbUser"]
        
        if action == "GET":
            password = getUserPassword(resource, dbUser)
            sendResponse(connection, resource, dbUser, password, None)

        else:
            sendResponse(connection, None, None, None, "Invalid action " + action)

    except Exception as e:
        sendResponse(connection, None, None, None, "error handling request " + str(e))

    finally:
        # close the connection
        connection.close()

# Set the path for the Unix socket
socket_path = '/tmp/my_pass_socket'

# remove the socket file if it already exists
try:
    os.unlink(socket_path)
except OSError:
    if os.path.exists(socket_path):
        raise

# Create the Unix socket server
server = socket.socket(socket.AF_UNIX, socket.SOCK_STREAM)

# Bind the socket to the path
server.bind(socket_path)

# Listen for incoming connections
server.listen(4)

# accept connections
print('Server is listening for incoming connections...')

try:
    # receive data from the client
    while True:
        connection, client_address = server.accept()
        logMessage('Connection from ' + str(connection).split(", ")[0][-4:])

        # handle me baby
        threading.Thread(target = handleConnection, args = (connection, )).start()

finally:
    # remove the socket file
    os.unlink(socket_path)
