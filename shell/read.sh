#!/bin.bash
read -p "Enter a password" password
if [ "$password"=="pass" ]
then 
echo "OK"
else 
echo "ERROR"
fi
