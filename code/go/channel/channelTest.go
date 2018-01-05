package main
import (
  "fmt"
  "time"
  )

func produce(p chan<- int){
  for i := 0; i < 10; i++ {
        p <- i
        fmt.Println("send:", i)
    }
}
func consumer(c <-chan int) {
  for i := 0; i < 10; i++ {
      v := <-c
      fmt.Println("receive:", v)
  }
}
func main() {
    ch := make(chan int,5)
    go produce(ch)
    go consumer(ch)
    time.Sleep(1 * time.Second)
}
func main2(){
  ch :=make(chan int,1)
  go func(){
    v := <-ch
    fmt.Println(v)
  }()
  ch <- 1
  fmt.Println("2")

}
func main1(){
  ch :=make(chan int,1)
  ch <-1
  go func(){
    v := <-ch
    fmt.Println(v)
  }()
  time.Sleep(1* time.Second)
  fmt.Println("2")

}
