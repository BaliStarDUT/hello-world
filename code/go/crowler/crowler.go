package main
import (
  "fmt"
  "log"
  "net/http"
  "os"
)
func main(){
  resp,err :=http.Get("http://hunaner.top:8080")
  if err !=nil {
    fmt.Println(err)
    log.Fatal(err)
  }
  defer resp.Body.Close()

  if resp.StatusCode == http.StatusOK{
    fmt.Println(resp.StatusCode)
  }
  buf :=make([]byte,1024)
  f,err1 := os.OpenFile("hunaner.html",os.O_RDWR|os.O_CREATE|os.O_APPEND,os.ModePerm)

  if err1!=nil{
    panic(err1)
    return
  }
  defer f.Close()
  for{
    n,_ :=resp.Body.Read(buf)
    if 0==n{
      break
    }
    f.WriteString(string(buf[:n]))
  }
}
