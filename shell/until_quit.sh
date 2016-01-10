#!/bin/bash
QUIT_COMMAND=quit
USER_INPUT=quit
until [ "$USER_INPUT"="$QUIT_COMMAND" ]
do
	echo "Please input command:"
	echo "(type command $QUIT_COMMAND to exit)"
	read USER_INPUT
	echo 
	echo ">>your command is $USER_INPUT"
	case $USER_INPUT in
		"open")
			echo ">>opening..."
			;;
		"close")
			echo ">>closed."
			;;
		*)
			echo ">>Unknown command."
			;;
	esac
	echo
done

echo "Bye."
exit 0
