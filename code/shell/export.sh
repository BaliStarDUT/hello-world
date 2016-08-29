#!/bin/bash
if[ "$1"="--export" ]
then
	export LOCATION=USA
elif [ "$1"="--no-export" ]
then
	LOCATION=USA
else
	echo 
	echo -e "`basename $0` --export\texport parent process's variable to child process"
	echo -e "`basename $0` --no-export\tdon't export parent process's variable to child process"
	echo
	exit 0
fi
echo 
echo "Your parent is at '$LOCATION'."
bash child.sh
echo 
echo -e "Your parent is at '$lOCATION'."
echo -e "Child prcess and parent process have different LOCATION."
echo 
exit 0
