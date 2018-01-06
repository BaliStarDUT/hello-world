package main

import (
  "errors"
  "fmt"
  "net/http"
  "net/rpc"
)
type Args struct {
  A,B int
}
type Quetient struct{
  Que,Rem int
}
type Arith int

func (t *Arith) Multiply(args *Args,reply *int) error {
  *reply = args.A*args.B
  return nil
}

func (t *Arith) Divide(args *Args,quo *Quetient) error {
  if args.B == 0{
    return errors.New("divided by zero")
  }
  quo.Que = args.A/args.B
  quo.Rem = args.A % args.B
  return nil
}

func main(){
  arith :=new(Arith)
  rpc.Register(arith)
  rpc.HandleHTTP()
  err := http.ListenAndServe(":9999", nil)
  if err != nil {
		fmt.Println(err.Error())
	}
}
