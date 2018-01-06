package main

import (
	"fmt"
	"uc"
)

func main() {
	str1 := "USING package uc!"
	fmt.Println(uc.UpperCase(str1))
	fmt.Printf("Hello, world.  Sqrt(2) = %v\n", uc.Sqrt(2))
}
