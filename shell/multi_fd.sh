#!/bin/bash
#通过多个不同的文件描述符把不同的数据重定向到不同的文件
echo
echo "Sending server data to file desciptor 3..."

echo "Server Date from file desciptor 3." >&3
echo 
echo "Sending Login data to file desciptor 4..."

echo "Login Date from file desciptor 4." >&4
echo
echo "Sending Error Message to file desciptor 5..."

echo "Error Message from file desciptor 5.">&5
echo

echo "Sending Statisties data to file desciptor 6.">&6
echo 
exit 0
