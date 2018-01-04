package main

import "fmt"
func show(c chan int){
  for{
    data := <- c
    if 1==data {
      fmt.Println("receive:",data)
    }
  }
}
func main(){
  c := make(chan int)
  go show(c)
  for{
    c <-1
    fmt.Println("sent:",&c)
  }
}
