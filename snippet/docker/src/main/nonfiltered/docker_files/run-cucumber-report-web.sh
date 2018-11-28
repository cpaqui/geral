#!/bin/bash

mongodb="mongodb://$CUKE_MONGO_USER:$CUKE_MONGO_PASS@$CUKE_MONGO_SERVER/$CUKE_MONGO_DATABASE"

dockerize -wait tcp://$CUKE_MONGO_SERVER -timeout 30s

java -Dcucumber.report.db.mongo.uri=$mongodb -jar /cucumber-report-web-bootable.war

dockerize -wait tcp://localhost:8080 -timeout 30s