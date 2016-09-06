#!/bin/bash
#读取用户制定文件，在每一行的前面添加行号，并保存到当前目录下以.lined作扩展名的文件中
echo 
echo -n "This Program add line numbers for a text file.Specify a text file path:"
read file
echo 
echo "Processing each line of the $file ..."
echo 
count=0
filename=`basename $file`
while read line
do 
	count=$((count+1))
	echo $count:$line
done < $file > $filename.lined
echo "Output file is $filename.lined."
echo
exit 0
