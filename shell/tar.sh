#!/bin/bash
if ["$(id -u)" -eq "0"]; then 
tar -czf /root/etc.tar.gz /etc
fi
