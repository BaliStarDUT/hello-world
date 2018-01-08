package main

import (
	"encoding/json"
	"flag"
	"fmt"
	"html/template"
	"io/ioutil"
	"log"
	"net/http"
	"strings"
	"time"
)

const (
	//默认模版地址
	template_dir = "/Users/air/Documents/GitHub/hello-world/code/html+js/html5up-aerial"
	//默认静态资源路径前缀
	static_resource_prefix = "/assets/"
)

var count = 0

//configuration.
var Conf *Configuration

type Configuration struct {
	Server       string // server scheme, host and port
	StaticServer string // static resources server scheme, host and port
	StaticRoot   string // static resources file root path
	StaticPrefix string // 静态资源路径前缀
	Port         string // listen port
	LogLevel     string // logging level: trace/debug/info/warn/error/fatal
	count        int    //访问量
}

func index(w http.ResponseWriter, r *http.Request) {
	if r.Method == "GET" {
		printInfo(r)
		locals := make(map[string]interface{})
		count = count + 1
		Conf.count = count
		locals["agent"] = r.UserAgent()
		locals["host"] = r.Host
		locals["count"] = count
		locals["date"] = time.Now().Format(time.UnixDate)
		fmt.Print(locals)
		t, _ := template.ParseFiles(template_dir + "/index.html")
		t.Execute(w, locals)
	} else {
		printInfo(r)
	}
	// fmt.Fprint(w,"Hello,this is yang")
}

func static_res(w http.ResponseWriter, r *http.Request) {
	printInfo(r)
	static_resource := Conf.StaticRoot + Conf.StaticPrefix + r.URL.Path[len(Conf.StaticPrefix):]
	fmt.Println(static_resource)
	http.ServeFile(w, r, static_resource)
}

func printInfo(r *http.Request) {
	AL := r.Header.Get("Accept-Language")
	fmt.Println("Language:", AL)
	r.ParseForm()

	fmt.Println("host:", r.Host)
	fmt.Println("hostname:", r.URL.Hostname())
	fmt.Println("Form:", r.Form)
	fmt.Println("path:", r.URL.Path)
	fmt.Println("scheme:", r.URL.Scheme)
	fmt.Println("agent:", r.UserAgent())
	fmt.Println("referer:", r.Referer())
	fmt.Println("url_long:", r.Form["url_long"])
	fmt.Println("request param:------------")
	for k, v := range r.Form {
		fmt.Println("key:", k)
		fmt.Println("val:", strings.Join(v, ""))
	}
}
func loadConf() {
	confPath := flag.String("conf", "config/config.json", "path of pipe.json")
	flag.Parse()
	bytes, err := ioutil.ReadFile(*confPath)
	if err != nil {
		log.Fatal("loads configuration file [" + *confPath + "] failed: " + err.Error())
	}
	Conf = &Configuration{}
	err = json.Unmarshal(bytes, Conf)
	if err != nil {
		log.Fatal("parses ["+*confPath+"] failed: ", err)
	}
	log.Println(Conf)
}
func saveConf() {
	bytes, err := json.Marshal(Conf)
	if err != nil {
		log.Fatal("save configuration file [" + "config/config.json" + "] failed: " + err.Error())
	}
	err = ioutil.WriteFile("config/config.json", bytes, 0644)
	if err != nil {
		log.Fatal("save configuration file [" + "config/config.json" + "] failed: " + err.Error())
	}
}
func main() {
	fmt.Println("Start to run....")
	loadConf()
	//先处理静态资源类型
	http.HandleFunc(Conf.StaticPrefix+"/", static_res)
	http.HandleFunc("/", index)

	err := http.ListenAndServe(Conf.Server+":"+Conf.Port, nil)
	if err != nil {
		log.Fatal("ListenAndServe:", err)
	}
}
