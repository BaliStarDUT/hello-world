package main
import (
  "fmt"
  "time"
  )
func main(){
  var i = 3
  go func(a int){
    fmt.Println(a)
    fmt.Println(1)
  }(i)
  // time.Sleep(1* time.Second)
  fmt.Println("2")
  time.Sleep(1* time.Second)
}

func main1(){
  go fmt.Println("1")
  time.Sleep(1* time.Second)
  fmt.Println("2")
}
