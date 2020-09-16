#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

/****************************************************************************************
**								创建子进程create a child process
** fork():
**	 pid_t fork(void);
** RETURN VALUE
** 	the PID of the child process is returned in the parent
	and 0 is returned in the child.
	On failure, -1  is  returned in the parent
	即成功情况下：子进程pid返回给父进程，而0返回给子进程
	即失败情况下：-1返回给父进程。内存不够或者id 号用尽，不过这种情况几乎很少发生。
** 注意：虽然else中两个i名字一样，但是并不是同一个；两个的地址并不一样，这是因为调用fork后父子进程几乎有一样的资源
****************************************************************************************/
int main()
{
	pid_t pid;
    int i = 100;

	pid = fork();

//创建fork出错
	if(pid == -1)
	{
		printf("Creat fork error!!!\n");
		exit(1);
	}

//父进程
	else if(pid)
	{
		i++;											//两个i地址并不相同，你可以把它想成C语言的作用域(帮助理解，实则不是这样的)
		printf("The father i = %d.\n",i);
		printf("The father return %d.\n",pid);			//子进程号返回给父进程
		printf("The father pid is %d.\n",getpid());		//父进程的pid
		printf("The father ppid is %d.\n",getppid());	//父进程的父进程pid
		while(1);		//这里用while(1);因为父、子进程是并发了，父进程退出了就看不到打印信息了
	}

//子进程
	else
	{
		i++;										//两个i地址并不相同，你可以把它想成C语言的作用域(帮助理解，实则不是这样的)
		printf("\nThe child i = %d.\n",i);
		printf("The child return %d.\n",pid);		//0返回给子进程
		printf("The child pid is %d.\n",getpid());	//子进程的pid
		printf("The child ppid is %d.\n",getppid());//子进程的父进程pid
		while(1);		//这里用while(1);因为父、子进程是并发了，子进程退出了就看不到打印信息了
	}

	return 0;
}
