#!/usr/bin/expect
set user [lindex $argv 0]
set host [lindex $argv 1]
set passwd "123456"
set cm [lindex $argv 2]

spawn ssh $user@$host

expect {
"yes/no" { send "yes\r"}
"password:" { send "$passwd\r" }
}
expect "]*"
send "$cm\r"
expect "]*"
send "exit\r"
