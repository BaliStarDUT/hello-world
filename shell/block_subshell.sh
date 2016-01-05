#!/bin/bash
echo 
echo "Block output is redirected to file 'block_current.out'."
( date;cd /etc;echo -n "Current Working dir:";pwd;)>block_current.out
echo "Current Working dir:$PWD"
echo 
exit 0
