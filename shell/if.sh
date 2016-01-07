#!/bin/bash
if[$# -lt 2]
then
	echo "Error!Arguments are not enough."
	echo "Usage:$0 operator file."
	exit 1
fi
