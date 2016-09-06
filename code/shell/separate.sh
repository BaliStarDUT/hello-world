echo 
echo "Create two files in current dir:"
echo "'separate.out' for standard outpur,"
echo "'separate.err' for standard error output."
echo 
echo "Please check their contents."
ls /bin/bash /bin/ls /bin/dd /bin/this_file_not_exist 1>separate.out 2>separate.err
echo 
exit 0
