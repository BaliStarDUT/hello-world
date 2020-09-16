package main

import (
	"fmt"
	"log"
)

func main() {
	var mem1, mem2 int32
	var mem3 int
	mem1 = 1024 * 1048576
	log.Println(mem1)
	mem2 = mem1 / 1048576
	log.Println(mem2)
	mem3 = int(mem1 / 1048576 * 9 / 10)
	log.Println(mem3)
	// var arr []int
	// arr := make([]int,0)
	// arr := []int{}
	// arr := []int{nil}

	fmt.Printf("%v",nil)
	foo();
	sort_go();
}

func sort()  {
	var a = []int{1,3,5,7,9}
	var b = []int{0,2,4,6,8}
	for i := 0; i < 5; i++ {
		fmt.Println(b[i])
		fmt.Println(a[i])
	}
}
func sort_go(){

	chana := make(chan int,10)
	// chanb := make(chan int)


	var a = []int{1,3,5,7,9}
	var b = []int{0,2,4,6,8}
	for i := 0; i < 5; i++ {
		fmt.Println(b[i])
		chana <-b[i] 
		fmt.Println(a[i])
		chana <-a[i] 
	}
	
	go func()  {
		// b := int
		select {
		case b := <- chana:
			fmt.Println(b)
		}
	}()
}

// func rou(){
// 	// for i := 0; i < count; i++ {
// 	// 	fmt.Println(chana)
// 	// }
	
// }

func foo() (err error) {
	err = fmt.Errorf("error");

	return
}