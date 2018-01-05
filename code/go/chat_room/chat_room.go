package main
import (
  "fmt"
  "os"
  "net"
)
func CheckError(err error,info string) (res bool){
  if(err!=nil){
    fmt.Println(info,"",err.Error())
    return false
  }
  return true
}

func Handler(conn net.Conn,messages chan string){
  fmt.Println("connection is connected from ...",conn.RemoteAddr().String())
  buf :=make([]byte,1024)
  for{
    length, err := conn.Read(buf)
    if(CheckError(err,"Connection")==false){
      conn.Close()
      break
    }
    if length >0{
      buf[length]=0
    }
    fmt.Println("Rec[",conn.RemoteAddr().String(),"] Say :" ,string(buf[0:length]))
    reciveStr :=string(buf[0:length])
    messages <- reciveStr
  }
}
func echoHandler(conns *map[string]net.Conn,messages chan string){
  for{
    msg := <-messages
    fmt.Println(msg)
    for key,value := range *conns{
       fmt.Println("connection is connected from ...",key)
       _,err := value.Write([]byte(msg))
       if(err != nil){
           fmt.Println(err.Error())
           delete(*conns,key)
       }
    }
  }
}

func StartServer(port string){
  service :=":"+port
  tcpAddr,err := net.ResolveTCPAddr("tcp4",service)
  CheckError(err,"ResolveTCPAddr")
  l,err := net.ListenTCP("tcp",tcpAddr)
  CheckError(err,"ListenTCP")
  conns := make(map[string]net.Conn)
  messages := make(chan string,10)
  go echoHandler(&conns,messages)
  for {
    fmt.Println("Listening...")
    conn,err := l.Accept()
    CheckError(err,"Accept")
    fmt.Println("Accepting...")
    conns[conn.RemoteAddr().String()] = conn
    go Handler(conn,messages)
  }
}

func chatSend(conn net.Conn){
  var input string
  username := conn.LocalAddr().String()
  for{
    fmt.Scanln(&input)
    if input == "/quit"{
      fmt.Println("Byebye..")
      conn.Close()
      os.Exit(0)
    }
    lens, err := conn.Write([]byte(username+"Say :::"+input))
    fmt.Println(lens)
    CheckError(err,"chatSend Write")
    if(err != nil){
          conn.Close()
          break
      }
  }
}

func StartClient(tcpaddr string){
  tcpAddr, err := net.ResolveTCPAddr("tcp4", tcpaddr)
    CheckError(err,"ResolveTCPAddr")
    conn, err := net.DialTCP("tcp", nil, tcpAddr)
    CheckError(err,"DialTCP")
    //启动客户端发送线程
    go chatSend(conn)

    //开始客户端轮训
    buf := make([]byte,1024)
    for{

        length, err := conn.Read(buf)
        if(CheckError(err,"Connection")==false){
            conn.Close()
            fmt.Println("Server is dead ...ByeBye")
            os.Exit(0)
        }
        fmt.Println(string(buf[0:length]))

    }
}

func main(){
  fmt.Println(len(os.Args))
  for _,arg := range os.Args{
    fmt.Println(arg)
  }
  if len(os.Args)!=4{
      fmt.Println("Wrong pare")
      os.Exit(0)
  }
  if os.Args[2]=="server" && len(os.Args)==4 {
      StartServer(os.Args[3])
  }
  if os.Args[2]=="client" && len(os.Args)==4 {
      StartClient(os.Args[3])
  }

}
