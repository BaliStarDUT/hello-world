package main

import (
  "fmt"
	"net/http"
	"strings"
	"log"
  "html/template"
  "time"
)

const (
  //模版地址
  template_dir = "/Users/air/Documents/GitHub/hello-world/code/html+js/html5up-aerial"
  //静态资源路径前缀
  static_resource_prefix = "/assets/"
)
//访问量
var count = 0

func index(w http.ResponseWriter,r *http.Request){
  if r.Method == "GET" {
    printInfo(r)
    locals := make(map[string] interface{})
    count = count+1
    locals["agent"] = r.UserAgent()
    locals["host"] = r.Host
    locals["count"] = count
    locals["date"] = time.Now().Format(time.UnixDate)
    fmt.Print(locals)
    t,_ := template.ParseFiles(template_dir+"/index.html")
    t.Execute(w, locals)
  }else{
    printInfo(r)
  }
  // fmt.Fprint(w,"Hello,this is yang")
}

func static_res(w http.ResponseWriter,r *http.Request){
  printInfo(r)
  static_resource := template_dir+"/assets/"+r.URL.Path[len(static_resource_prefix):]
  fmt.Println(static_resource)
  http.ServeFile(w, r, static_resource)
}

func printInfo(r *http.Request){
  AL := r.Header.Get("Accept-Language")
  fmt.Println("Language:",AL)
  // if strings.Contains(Al, "zh-CN") {
  //
  // }
  r.ParseForm()
  
  fmt.Println("host:",r.Host)
  fmt.Println("hostname:",r.URL.Hostname())
  fmt.Println("Form:",r.Form)
  fmt.Println("path:",r.URL.Path)
  fmt.Println("scheme:",r.URL.Scheme)
  fmt.Println("agent:",r.UserAgent())
  fmt.Println("referer:",r.Referer())
  fmt.Println("url_long:",r.Form["url_long"])
  fmt.Println("request param:------------")
  for k,v := range r.Form {
    fmt.Println("key:",k)
    fmt.Println("val:",strings.Join(v, ""))
  }
}

func main(){
  //先处理静态资源类型
  http.HandleFunc(static_resource_prefix, static_res)
  http.HandleFunc("/",index)

  err := http.ListenAndServe(":9999", nil)
  if err !=nil{
    log.Fatal("ListenAndServe:",err)
  }
}
