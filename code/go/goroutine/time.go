package main

import (
	"fmt"
	"time"
)

func main() {
	var i int = 0

	ticker := time.NewTicker(2 * time.Second)
	for now := range ticker.C {
		fmt.Println(now, i)
		i++
		if i > 10 {
			break
		}
	}
}

func main2() {
	time.AfterFunc(5*time.Second, func() {
		fmt.Println("run func1")
	})
	time.AfterFunc(2*time.Second, func() {
		fmt.Println("run func2")
	})
	fmt.Println("main thread")
	time.Sleep(10 * time.Second)
}

func main1() {
	timer := time.NewTimer(2 * time.Second)
	go onTime(timer.C)
	fmt.Println("main thread")
	time.Sleep(10 * time.Second)
}

func onTime(timer <-chan time.Time) {
	for now := range timer {
		fmt.Println("ontime", now)
	}
}
