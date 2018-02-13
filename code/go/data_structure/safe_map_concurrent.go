package main

import (
	"log"
	"sync"
)

type SafeMap struct {
	sync.RWMutex
	Map map[int]int
}

func main() {
	done := make(chan int)
	safeMap := newSafeMap(10)
	for i := 0; i < 1000; i++ {
		go safeMap.writeMap(i, i, done)
		go safeMap.readMap(i, done)
	}
	go Done(done)
	for {

	}
}
func newSafeMap(size int) *SafeMap {
	sm := new(SafeMap)
	sm.Map = make(map[int]int)
	return sm
}

func (sm *SafeMap) readMap(key int, done chan int) int {
	sm.RWMutex.RLock()
	log.Printf("map:%d:%d", key, sm.Map[key])
	done <- key
	value := sm.Map[key]
	sm.RWMutex.RUnlock()
	return value
	// return 1
}
func (sm *SafeMap) writeMap(key int, value int, done chan int) {
	sm.RWMutex.Lock()
	sm.Map[key] = value
	sm.RWMutex.Unlock()
	log.Printf("value:%d:%d", key, value)
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
