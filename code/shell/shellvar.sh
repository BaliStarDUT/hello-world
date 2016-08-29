#!/bin/bash
echo -e "HOME directory:\t\t$HOME"
echo -e "Current Shell:\t\t\t$SHELL"
echo -e "User Name:\t\t$USER"
echo -e "User ID:\t\t\t$UID"
echo -e "Bash Version:\t\t\t$BASH_VERSION"
echo -e "Current Directory:\t\t$PWD"
echo -e "Previeous Working Directory:\t$OLDPWD"
echo
echo "****Will sleep 3 seconds...****"
sleep 3s
echo -e "Seconds since invoked:\t\t$SECONDS seconds"
echo -e "Bash Level:\t\t$SELVL"
echo -en "10 Random Numbers:\t\t"
i=10
until [$i -lt 1]
do
	echo -n "$RANDOM"
	let i=$i-1
done
echo
echo
echo "****Change \$IFS to '-'****"
IFS=-
echo "'2016-01-07' whill be retreated as 3 strings."
DATE=2016-01-07
printf"%s\n" $DATE
echo 
echo -e "Command Search in:\t\t$PATH"
echo
exit 0
