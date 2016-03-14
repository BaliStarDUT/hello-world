#!/bin/bash
#count the words numbers in a text file
count(){
    if[ $#!=1 ] then
	echo "Need one file parameter to work!"
	exit 1
    fi
    tr'+=*.,;:{}()#!?<>"\n\t'''<$1 |\
    tr'A-Z''a-z'|\
    tr-s''|\
    tr'''\n'|\
    sort|\
    uniq -c |\
    sort -r|\
}
echo 
echo "This script can count words of a specified file."

while:
do 
    read -p "Enter the file path(or quit):"
    case "$REPLY" in
	[Qq]|[Qq][Uu][Ii][Tt]
	    echo "Bye."
	    exit 0
	    ;;
	*)
	    if[ -f "REPLY" ]&&[ -r "$REPLY" ]&&[ -s "$REPLY" ]
	    then
		count "$REPLY"
	    else
		echo "$REPLY can not be dealed with."
	    fi
	    ;;
	esac
done
exit 0
