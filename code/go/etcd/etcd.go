package etcddemo

import (
	"ac/registry/etcdclient/etcd"
	"fmt"
	"log"
)

func runetcd() {
	machines := []string{"http://172.19.128.134:8012"}
	client := etcd.NewClient(machines)
	fmt.Println("-------------", client)
	result, err := client.Set("/foo", "bar", 0)
	if err != nil {
		log.Fatal(err)
	}
	log.Println(result)
	result, err = client.Get("/foo", false, false)
	if err != nil {
		log.Fatal(err)
	}
	log.Println(result)
}
