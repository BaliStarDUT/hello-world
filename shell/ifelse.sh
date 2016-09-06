#!/bin/bash
PLATFORM=`uname -s`
FREEBSD=
SUNOS=
MAC=
AIX=
MINIX=
LINUX=
echo 
echo "Identifying the platform on which this installer is running on.."
echo "$PLATFORM"
if [ "$PLATFORM"="FreeBSD" ]
then 
	echo "This is FreeBSD system."
	FREEBSD=1
elif [ "$PLATFORM"="SunOs" ]
then
	echo "This is Solaris system."
	SUNOS=1
elif [ "$PLATFORM"="Darwin" ]
then
	echo "This is Mac OSX system."
	MAC=1
elif [ "$PLATFORM"="AIX" ]
then 
	echo "This is AIX system."
	AIX=1
elif [ "$PLATFORM"="Linux" ]
then 
	echo "This is Linux system."
	LINUX=1
else
	echo "Failed to identify this Operating System,Abort!"
	exit 1
fi
echo "Starting to install the software..."
echo 

exit 0
