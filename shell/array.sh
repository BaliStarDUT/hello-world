#!/bin/bash
index=0
for i in `cut -f 1 -d: /etc/passwd`
do
	user[$index]=$i
	let index=$index+1
done
index=1
for name in " ${user[*]}"
do 
	echo " $index:$name"
	let index=$index+1
done

echo "====================="
echo "Print all users in one line:"
echo 
echo "${user[*]}"
#echo ${user[@]}
#echo ${user[*]}
echo
echo "================================="
echo "Reassign th user array,the useer names will be lost:"
user=([50]=yang,zhen,ning)
echo
echo ${user[*]}
echo 
exit 0
