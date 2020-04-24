#!/bin/bash
 
#$1 is the instance IP
#$2 is the private key

# Script
echo "Starting deploy script"

#Preparing the key file
echo $2 > key.pem
chmod 400 key.pem

# Scp to instance
scp -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null -i key.pem /home/travis/build/JulienB-Epfl/Coronhackathon/back/build/libs/backend-0.0.1-SNAPSHOT.jar ec2-user@18.234.142.249:/home/ec2-user

exit 0
