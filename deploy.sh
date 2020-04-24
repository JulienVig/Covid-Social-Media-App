#!/bin/bash

# Script
echo "Starting deploy script"

# Scp to instance
cd ..
scp -v -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null -i amazonkey-coronhackathon.pem /home/travis/build/JulienB-Epfl/Coronhackathon/back/build/libs/backend-0.0.1-SNAPSHOT.jar ec2-user@18.234.142.249:/home/ec2-user

exit 0
