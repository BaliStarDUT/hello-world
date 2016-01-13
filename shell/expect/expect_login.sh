#! /usr/bin/expect
set host "192.168.0.108"
set passwd "yang"
#spawn command not found
spawn ssh root@host
expect{
	"Yes/No" { send "yes\r"; exp_continue }
	"password" { send "$passwd\r" }
}
interact
