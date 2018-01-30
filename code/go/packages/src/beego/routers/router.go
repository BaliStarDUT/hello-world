package routers

import (
	"beego/controllers"
	"log"

	"github.com/astaxie/beego"
)

func init() {
	log.Println("routers init")
	beego.SetStaticPath("/assets", "static")
	beego.Router("/", &controllers.MainController{})
	beego.Router("/about", &controllers.AboutController{})
	beego.Router("/api", &controllers.ApiController{})
}
