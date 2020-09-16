package main

import (
	"fmt"
)

func main(){
	chana := make(chan int,10)
	var a = []int{1,3,5,7,9}
	var b = []int{0,2,4,6,8}
	for i := 0; i < 5; i++ {
		fmt.Println(b[i])
		chana <-b[i] 
		fmt.Println(a[i])
		chana <-a[i] 
	}
	
	go func()  {
		select {
		case b := <- chana:
			fmt.Println(b)
		}
	}()
}