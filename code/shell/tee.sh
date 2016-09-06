#!/bin/bash
sort /etc/passwd | cat -n | tee sort.out
exit 0
