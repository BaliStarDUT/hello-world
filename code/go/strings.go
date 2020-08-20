package main

import (
	"log"
	"strings"
)

func main() {
	services := "yztest_15_2;yztest_15_2"
	servicesArray := strings.Split(services, ";")
	log.Printf("array:[%v]", servicesArray)
	for _, serviceTomv := range servicesArray {
		log.Printf(serviceTomv + "=")
	}
	// spath := path.Join("/Users/air/", ".ignore")
	// info, err := os.Stat("/var/lib/docker/volumes/log-yztest2-15_18-9-12.1524049310/_data/.acLoggerAgentIgnore")
	// if err != nil {
	// 	log.Fatalf("err:%v", err.Error())
	// }
	// if err == nil {
	// 	log.Print("info:%v", info)
	// }
	targetPath := "/home/service/0_log_1-0-3/log"
	targetPath2 := "/home/service/0_log_1-0-3"
	log.Printf("targetPath[%v],Contains(log)?:%v", targetPath, strings.Contains(targetPath, "log"))
	log.Printf("targetPath2[%v],Contains(log)?:%v", targetPath2, strings.Contains(targetPath2, "log"))
	// targetPath := "/home/service/0_log_1-0-3/log"
	// targetPath2 := "/home/service/0_log_1-0-3"
	log.Printf("targetPath[%v],HasSuffix(log)?:%v", targetPath, strings.HasSuffix(targetPath, "log"))
	log.Printf("targetPath2[%v],HasSuffix(log)?:%v", targetPath2, strings.HasPrefix(targetPath2, "log"))
	percent := uint64(50) / uint64(100)
	percent2 := float32(uint64(50)) / float32(uint64(100))
	percent3 := float32(uint64(50) / uint64(100))
	log.Printf("%v", percent)
	log.Printf("%v", percent2)

	log.Printf("%v", percent3)
}
