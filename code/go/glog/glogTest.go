package main

import (
	log "ac-common-go/glog"
	"flag"
	"os"
	"runtime"
	"time"
)

func main() {
	version := flag.Bool("version", false, "current service build version")
	flag.Parse()
	log.Infof("Version: %s", *version)
	log.Infof("Build time: %s", time.Now().String())
	log.Infof("GO OS/Arch: %s/%s", runtime.GOOS, runtime.GOARCH)
	log.Infof("CPU num: %s", runtime.NumCPU())
	hostname, err := os.Hostname()
	if err != nil {
		log.Fatalf("Can't get hostname: %s", hostname)
	}
	log.Infof("Host name: %s", hostname)
}
