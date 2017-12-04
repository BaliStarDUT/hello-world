root@8d71900d8df0:/# mysqldump
Usage: mysqldump [OPTIONS] database [tables]
OR     mysqldump [OPTIONS] --databases [OPTIONS] DB1 [DB2 DB3...]
OR     mysqldump [OPTIONS] --all-databases [OPTIONS]
For more options, use mysqldump --help


mysqldump  --lock-all-tables  --databases bitnami_prestashop opencron > /mysqldump
