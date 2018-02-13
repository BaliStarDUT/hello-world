package main

import (
	"log"
	"time"
)

func main() {
	message := make(chan int)
	go func() {
		time.Sleep(time.Second * 3)
		message <- 1
	}()
	go func() {
		time.Sleep(time.Second * 4)
		message <- 4
	}()
	go func() {
		time.Sleep(time.Second * 2)
		message <- 2
	}()
	go func() {
		for i := range message {
			log.Println(i)
		}
	}()
	//如果主routine不等待，将不会输出信息
	time.Sleep(time.Second * 5)
}
