#!/bin/bash
echo
echo "Which directory do you want to list:"
echo "(Press \"Enter\" directly to show menu again)"
echo 
select dir in /bin/ /usr/bin /usr/local/bin /sbin/ /usr/sbin/ /usr/local/sbin /usr/share/ quit
do
	if [ ! -z "$dir" ]
	then
		if [ "$dir"="quit" ];
			then
				echo
				echo "You entered quit,Byebye!"
				break;
		else
			echo
			echo "You selected dirctory \"$dir\",it contains following files:"
			echo
			ls -l $dir
		fi
	else
		echo
		echo "Error,invalid selectiion \"$REPLY\",chose again!"
fi
echo 
echo "Which dirctory do you want to list:"
echo "(Press \"Enter\" directly to show menu again)"
done
exit 0
