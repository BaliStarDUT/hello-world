package controllers

import (
	"runtime"

	"github.com/astaxie/beego"
)

type MetrixController struct {
	beego.Controller
}

func (this *MetrixController) Get() {
	this.Data["Website"] = "beego.me"
	this.Data["Email"] = "astaxie@gmail.com"
	// this.TplName = "index.tpl"
	this.Ctx.WriteString(runtime.Version())
}
