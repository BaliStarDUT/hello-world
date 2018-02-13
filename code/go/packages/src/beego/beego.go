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
	beego.BConfig.Log.AccessLogs = true
	beego.BConfig.Log.FileLineNum = true
	beego.SetLevel(beego.LevelInformational)
	beego.Run()
}
