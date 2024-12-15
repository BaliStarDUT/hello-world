package main

import (
	"flag"

	"github.com/golang/glog"
)

// ./glog2   -v 2 -log_dir ./log -stderrthreshold INFO
// go run scheduler.go -log_dir ./log -stderrthreshold INFO -alsologtostderr -filethreshold info
func main() {
	main2()

	flag.Parse()                    // 1
	glog.Info("This is a Info log") // 2
	glog.Warning("This is a Warning log")
	glog.Error("This is a Error log")
	a, err := glog.Names("dddd")
	glog.Info(a)
	if err != nil {
		glog.Info(a)
	}

	glog.V(1).Infoln("level 1") // 3
	glog.V(2).Infoln("level 2")
	// glog.Fatalln("This is a Fatal log")
	glog.Flush() // 4
	glog.Info("ddd")
	glog.Info()
	main1()

}
