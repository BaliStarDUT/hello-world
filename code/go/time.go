package main

import (
	"fmt"
	"log"
	"time"
)

func show() {
	for {
		fmt.Println("child")
		time.Sleep(10000)
	}
}
func main() {
	// go show() //如果不用go的话 会一直阻塞在show中输出child
	fmt.Println("parent ")
	time.Sleep(10000)
	// for {
	// 	fmt.Println("parent ")
	// 	time.Sleep(10000)
	// }
	useTime()

}

func useTime() {
	log.Printf(time.Now().String())
	log.Printf(time.Now().Format("Mon Jan 2 15:04:05 -0700 MST 2006"))
	swarmTime, err := time.Parse("Mon Jan 2 15:04:05 -0700 MST 2006", "Wed Apr 11 14:06:29 +0800 CST 2018")
	if err != nil {
		log.Fatalf(err.Error())
	}
	log.Printf(swarmTime.String())
	log.Printf("now after %v:%v", swarmTime, time.Now().After(swarmTime))
	deadTime := time.Date(2018, time.April, 11, 0, 0, 0, 0, time.UTC)
	log.Printf(deadTime.Format(time.RFC3339))
	createTime, err := time.Parse("Mon Jan 2 15:04:05 -0700 MST 2006", "Wed Apr 11 07:33:30 +0000 UTC 2018")
	if err != nil {
		log.Fatalf(err.Error())
	}
	log.Printf(createTime.String())
	log.Printf("createTime after %v:%v", deadTime.Local(), createTime.After(deadTime.Local()))
	log.Printf("-----------")
	createTime1, err := time.Parse("2006-01-02 15:04:05 -0700 MST", "2018-04-11 12:00:00 +0000 UTC")
	if err != nil {
		log.Fatalf(err.Error())
	}
	log.Printf(createTime1.String())
	log.Printf("createTime after %v:%v", deadTime.Local(), createTime1.After(deadTime.Local()))
	// 2018-04-11 07:33:30 +0000 UTC
}
