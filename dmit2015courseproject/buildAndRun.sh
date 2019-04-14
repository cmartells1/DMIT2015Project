#!/bin/sh
mvn clean package && docker build -t dmit2015/dmit2015courseproject .
docker rm -f dmit2015courseproject || true && docker run -d -p 8080:8080 -p 4848:4848 --name dmit2015courseproject dmit2015/dmit2015courseproject