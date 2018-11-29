#!/bin/bash

#mongodb="mongodb://$MONGO_USER:$MONGO_PASS@$MONGO_SERVER/$MONGO_DATABASE"

#dockerize -wait tcp://$MONGO_SERVER -timeout 30s

java -jar /example-spring-boot.jar

dockerize -wait tcp://localhost:8080 -timeout 30s