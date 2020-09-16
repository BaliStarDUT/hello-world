package main

import (
	"flag"
	"os"

	"github.com/golang/glog"

	"github.com/astaxie/beego"
)

//MainController is a
type MainController struct {
	beego.Controller
}

func (this *MainController) Get() {
	this.Ctx.WriteString("hello world")
}

func (this *MainController) Post() {
	this.Ctx.WriteString("this is Post")
}

func (this *MainController) Metrix() {
	this.Ctx.WriteString("Health")
}

func (this *MainController) HostName() {
	hostName, _ := os.Hostname()
	glog.Info("This hostname:" + hostName)
	this.Ctx.WriteString(hostName)
}

func (this *MainController) MemSize() {
	pageSize := os.Getpagesize()
	glog.Info("This pageSize:" + string(pageSize))
	this.Ctx.WriteString(string(pageSize))
}

func main() {
	flag.Parse() // 1
	beego.Router("/", &MainController{})
	beego.Router("/admin", &MainController{})
	beego.Router("/metrix", &MainController{}, "*:Metrix")
	beego.Router("/metrix/hostname", &MainController{}, "*:HostName")
	beego.Router("/metrix/mem", &MainController{}, "*:MemSize")

	beego.Router("/metrix/version", &MetrixController{}, "*:Get")

	beego.Run("localhost:8000")
}
