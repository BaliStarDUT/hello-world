#!/bin/bash
clear
echo "  File Operation List"
echo "=========================="
echo "Choose one of the following operations:"
echo 
echo "[O]pen File"
echo "[D]elete File"
echo "[R]ename File"
echo "[M]ove File"
echo "[C]opy File"
echo "[P]aste File"
echo
echo -n "Please enter your operation:"
read operation
case "$operation" in
"O"|"o")
	echo
	echo "Opening File ..."
	echo "Successed!"
	;;
"D"|"d")
	echo
	echo "Deleting File..."
	echo "Successed!"
	;;
"R"|"r")
	echo
	echo "Moving File 1 to File2..."
	echo "Successed!"
	;;
"C"|"c")
	echo
	echo "Coping File1 to File2..."
	;;
"P"|"p")
	echo 
	echo "Paste File"
	echo "Successed!"
	;;
*)
	echo
	echo "Error,Unknown operation."
	echo "Program exit!"
	exit 1
	;;
esac
echo 
exit 0
