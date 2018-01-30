package main

import (
	_ "beego/routers"
	"log"

	"github.com/astaxie/beego"
)

func main() {
	// beego.Router("/", &MainController{})
	// logs.SetLogger(logs.AdapterConsole, "{\"level\":1}")
	log.Println("beego start...")
	beego.BConfig.Listen.EnableHTTPS = true
	beego.BConfig.Listen.HTTPSAddr = "127.0.0.1"
	beego.BConfig.Listen.HTTPSPort = 10443
	beego.BConfig.Listen.HTTPSKeyFile = "/Users/air/Documents/GitHub/hello-world/code/go/docker/key.pem"
	beego.BConfig.Listen.HTTPSCertFile = "/Users/air/Documents/GitHub/hello-world/code/go/docker/cert.pem"
	beego.Run()
}
