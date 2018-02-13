package main

import "log"

func main() {
	done := make(chan int)
	Map := make(map[int]int)
	for i := 0; i < 90; i++ {
		go writeMap(Map, i, i, done)
		go readMap(Map, i, done)
	}
	go Done(done)
	for {

	}
}

func readMap(Map map[int]int, key int, done chan int) int {
	log.Printf("map:%d:%d", key, Map[key])
	done <- key
	return Map[key]
	// return 1
}
func writeMap(Map map[int]int, key int, value int, done chan int) {
	log.Printf("value:%d:%d", key, value)
	Map[key] = value
	done <- key
}
func Done(done chan int) {
	for {
		select {
		case v := <-done:
			log.Printf("done:%d", v)
		}
	}
}
