package controllers

import (
	"github.com/astaxie/beego"
)

type AboutController struct {
	beego.Controller
}

func (this *AboutController) Get() {
	this.Ctx.WriteString("About me: James")
}
