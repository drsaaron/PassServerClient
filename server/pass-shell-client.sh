#! /bin/sh

SOCKET=/tmp/my_pass_socket
message='{ "action": "GET", "dbUser": "scott", "resource": "test" }'

echo $message | nc -U $SOCKET | jq
