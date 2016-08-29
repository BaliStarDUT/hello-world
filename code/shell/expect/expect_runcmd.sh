#!/usr/bin/expect
set usr "root"
set passwd "yang"
set host "192.168.0.108"
#spawn command not found
spawn ssh root@192.168.0.108
expect{
    "Yes/No" { send "yes\r"; exp_continue }
    "password" { send "$passwd\r" }
}
expect "]*"
send "touch /tmp/12.txt\r"
expect "]*"
send "echo 1212 > /tmp/12.txt\r"
expect "\*"
send "exit\r"
