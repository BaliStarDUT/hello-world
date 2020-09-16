#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>

char command[256];
int main()
{
    int rtn;
    printf(">>");

    fgets(command, 256, stdin);
    command[strlen(command)-1] = 0;
    if(fork()==0){
        exec(command, NULL);
        perror(command);
        exit(errno);
    } else {
        wait(&rtn);
        printf("child process return %d\n", rtn);
    }

    return 0;
}
