#!/bin/bash
TIME=`date`
NAME=`uname -n`
KERNEL=`uname -s`
VERSION=`uname -r`
ARCH=`uname -m`
OS=`uname -o`
echo 
echo "Info about your computer"
echo "==========================================="
echo "Current Time:       $TIME"
echo "Host Name:          $NAME"
echo "Operating System:   $OS"
echo "Computer ARCH:      $ARCH"
echo "Kernel Version:     $KERNEL $VERSION"
echo 
c2="$(tput bold)$(tput setaf 2)"
echo "$c2  _     _            _                 ___ "
echo "$c2 | |   (_)          | |               / __)"
echo "$c2 | |  _ _  ____ ____| | _____ _____ _| |__ "
echo "$c2 | |_/ ) |/ ___) ___) || ___ (____ (_   __)"
echo "$c2 |  _ (| ( (__( (___| || ____/ ___ | | |   "
echo "$c2 |_| \_)_|\____)____)\_)_____)_____| |_|   "
echo "$(tput sgr0)"
exit 0
