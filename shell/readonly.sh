#!/bin/bash
VERSION=2.0
readonly VERSION
echo 
echo -e "Defined readonly variable:\n VERSION=$VERSION"
echo 

echo "Try to modify readonly variable VERSION."
VERSION=3.0
echo 
echo -e "Readonly variable VERSION doesn't change,\n VERSION=$VERSION"
echo "============================================"
readonly PATCHLEVEL=1
echo 
echo -e "Defined another readonly variable:\n PATCHLEVEL"
echo 

echo "Try to modify readonly variable PATCHLEVEL."
PATCHLEVEL=3
echo 
echo -e "Readonly variable PATHLEVEL doesn't change,\n PATCHLEVEL=$PATCHLEVEL"
echo
echo "Try to unset readonly variable PATCHLEVEL"
unset PATCHLEVEL
echo 
echo -e "Readonly variable PATCHLEVEL doesn't change,\n PATCHLEVEL=$PATCHLEVEL"
echo
exit 0
