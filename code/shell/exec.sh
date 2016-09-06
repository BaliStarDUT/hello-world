#!/bin/bash
#bash提供的exec命令 关联一个自定义的文件描述符到一个特定的文件
echo 
echo "Open file descriptor 4,which is associated with file exec.out"
exec 4>exec.out
echo 

echo "Open another file descriptor 5,which is associated with file descriptor4."
exec 5>&4

echo "Sending some data...."

echo "Data stream from file descriport 1 STDOUT," 1>&5
echo "redirected to file descriptor 5,"1>&5
echo "Then data will go to file descriptor 4,which is associated with file exec.out.">&5
echo 

echo "Closing fd 4..."
exec 4>&-
echo "Closing fd 5..."
exec 5>&-
echo
exit 0
