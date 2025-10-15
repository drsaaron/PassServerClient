#! /bin/sh

SOCKET=/tmp/my_pass_socket
message='{ "action": "GET", "dbUser": "aar1069", "resource": "test" }'

echo $message | nc -U $SOCKET | jq
