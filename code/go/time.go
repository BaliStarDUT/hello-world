package main

import (
  "fmt"
  "time"
)

func show(){
  for{
    fmt.Println("child")
    time.Sleep(10000)
  }
}
func main(){
  go show() //如果不用go的话 会一直阻塞在show中输出child
  for{
    fmt.Println("parent ")
    time.Sleep(10000)
  }
}
