!/usr/bin/expect
set passwd "123456"
spawn rsync -av root@192.168.11.18:/tmp/12.txt /tmp/
expect {
"yes/no" { send "yes\r"}
"password:" { send "$passwd\r" }
}
expect eof
