#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'

import pexpect

def ssh_cmd(ip,port, passwd, cmd):
    print(ip,port,passwd,cmd)
    ret = -1
    ssh = pexpect.spawn('ssh root@%s -p%s "%s"' % (ip,port,cmd))
    try:
        i = ssh.expect(['password:', 'continue connecting (yes/no)?'], timeout=5)
        if i == 0 :
            ssh.sendline(passwd)
        elif i == 1:
            ssh.sendline('yes\n')
            ssh.expect('password: ')
            ssh.sendline(passwd)
        ssh.sendline(cmd)
        r = ssh.read()
        print r
        ret = 0
    except pexpect.EOF:
        print "EOF"
        ssh.close()
        ret = -1
    except pexpect.TIMEOUT:
        print "TIMEOUT"
        ssh.close()
        ret = -2

    return ret

if __name__=='__main__':
    ssh_cmd('100.67.76.9','10015','AliOS%1688','cat /proc/meminfo;date')
