package controllers

import (
	"time"

	"github.com/astaxie/beego"
)

var count = 0

type MainController struct {
	beego.Controller
}

func (this *MainController) Get() {
	count = count + 1
	this.Data["Website"] = "beego.me"
	this.Data["Email"] = "drawnkid@gmail.com"
	this.Data["agent"] = this.Controller.Ctx.Request.UserAgent()
	this.Data["host"] = this.Controller.Ctx.Request.Host
	this.Data["count"] = count
	this.Data["date"] = time.Now().Format(time.UnixDate)
	this.TplName = "index.html"
}
