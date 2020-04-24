#!/bin/bash

# Script
cd
killall java
java -jar backend-0.0.1-SNAPSHOT.jar &
disown
exit 0