#!/bin/bash
mkdir ${HOME}/test > /dev/null 2>&1
if [ $? -ne 0 ]
then
	echo "Directory ${HOME}/test has already existed."
	echo "Do Nothing,Abort!"
	exit 1
else 
	echo "Create directory ${HOME}/test"
fi
echo "cp *.sh files into directory ${HOME}/test,and prefix \"test_\" before each filename."
for FILE in `ls -l *.sh`
do
	cp $FILE ${HOME}/test/test_$FILE
	chmod -x ${HOME}/test/test_$FILE
done
exit 0
