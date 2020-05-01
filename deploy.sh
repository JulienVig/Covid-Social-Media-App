#!/bin/bash

# Script
echo "Starting deploy script"

# Scp to instance
cd ..
chmod 400 amazonkey-coronhackathon.pem

mv /home/travis/build/JulienB-Epfl/Coronhackathon/back/build/libs/backend-0.0.1-SNAPSHOT.jar /home/travis/build/JulienB-Epfl/Coronhackathon/back/build/libs/backend-bis.jar
scp -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null -i "amazonkey-coronhackathon.pem" /home/travis/build/JulienB-Epfl/Coronhackathon/back/build/libs/backend-bis.jar ec2-user@34.202.239.161:/home/ec2-user
ssh -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null -i "amazonkey-coronhackathon.pem" 34.202.239.161 < remote-script.sh

exit 0
