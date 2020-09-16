package main

import (
	"fmt"
	"log"
	"math"
	"math/big"
)

func main() {
	// Here are some calculations with bigInts:
	im := big.NewInt(math.MaxInt64)
	in := im
	io := big.NewInt(1956)
	ip := big.NewInt(1)
	ip.Mul(im, in).Add(ip, im).Div(ip, io)
	fmt.Printf("Big Int: %v\n", ip)
	// Here are some calculations with bigInts:
	rm := big.NewRat(math.MaxInt64, 1956)
	rn := big.NewRat(-1956, math.MaxInt64)
	ro := big.NewRat(19, 56)
	rp := big.NewRat(1111, 2222)
	rq := big.NewRat(1, 1)
	rq.Mul(rm, rn).Add(rq, ro).Mul(rq, rp)
	fmt.Printf("Big Rat: %v\n", rq)
	var mem float32
	mem = 1024
	log.Println(mem)
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

}
