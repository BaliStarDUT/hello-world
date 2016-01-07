#!/bin/bash
#测试位置变量
echo "this is the file name: $0"
echo "this is the first parameter: $1"
echo "this is the second parameter: $2"
echo "this is the number of all parameter: $#"
echo "this is the all parameters: $*"
echo "this is the all parameters: $@"
echo "this is PID: $$"
