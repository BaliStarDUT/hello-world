package controllers

import (
	"time"

	"github.com/astaxie/beego"
)

type ApiController struct {
	beego.Controller
}

func (this *ApiController) Get() {
	now := time.Now().String()
	this.Ctx.WriteString(now)
}
