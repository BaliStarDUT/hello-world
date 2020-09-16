#!/bin/bash
echo "tftf"
true | false | true | false
RC=( "${PIPESTATUS[@]}" )
echo "RC[0] = ${RC[0]}"		# true = 0
echo "RC[1] = ${RC[1]}"		# false = 1
echo "RC[2] = ${RC[2]}"		# true = 0
echo "RC[3] = ${RC[3]}"		# false = 1

echo "ftft"
false | true | false | true
RC=( "${PIPESTATUS[@]}" )
echo "RC[0] = ${RC[0]}"		# false = 1
echo "RC[1] = ${RC[1]}"		# true = 0
echo "RC[2] = ${RC[2]}"		# false = 1
echo "RC[3] = ${RC[3]}"		# true = 0

echo "fftt"
false | false | true | true
RC=( "${PIPESTATUS[@]}" )
echo "RC[0] = ${RC[0]}"		# false = 1
echo "RC[1] = ${RC[1]}"		# false = 1
echo "RC[2] = ${RC[2]}"		# true = 0
echo "RC[3] = ${RC[3]}"		# true = 0

if [[ RC[0] != "0" -o RC[1] != "0" ]]; then
    echo "exit 1"
    exit 1
else
    echo "exit 0"
    exit 0
fi
