#!/usr/bin/expect
set passwd "123456"
set host [lindex $argv 0]
set file [lindex $argv 1]
spawn rsync -av $file root@$host:$file
expect {
"yes/no" { send "yes\r"}
"password:" { send "$passwd\r" }
}
expect eof
