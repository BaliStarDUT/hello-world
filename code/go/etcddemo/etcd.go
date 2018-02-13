package etcddemo

import (
	"ac/registry/etcdclient/etcd"
	"log"
)

func runetcd(client etcd.Client) {
	result, err := client.Set("/foo", "bar", 0)
	if err != nil {
		log.Fatalf("set err:%v", err)
	}
	result, err = client.Get("/foo", false, false)
	if err != nil {
		log.Fatal(err)
	}
	log.Println(result.Node.Value)
	// result, err = client.Get("/registry/services", false, false)
	// if err != nil {
	// 	log.Fatal(err)
	// }
	// log.Println(result)
}
func watch(client etcd.Client) {
	receiver := make(chan *etcd.Response)
	response, err := client.Watch("/foo-service/container2", 0, true, receiver, nil)
	_, err = client.Set("/foo-service/container2", "bar", 0)
	if err != nil {
		log.Fatalln(err.Error())
	}
	log.Printf("Get response:%s,%s,%s,%s,%s,%s", response.Action, response.EtcdIndex, response.PrevNode, response.Node, response.RaftIndex, response.RaftTerm)

	for resp := range receiver {
		if resp == nil {
			log.Fatalln("receive nil need exit")
			break
		}
		if resp.Node == nil {
			log.Fatalln("receive null etcd node")
			continue
		}
		log.Printf("Get response:%s,%s,%s,%s,%s,%s", resp.Action, resp.EtcdIndex, resp.PrevNode, resp.Node, resp.RaftIndex, resp.RaftTerm)
		// atomic.StoreUint64(, resp.EtcdIndex+1)
		if resp.Node.Dir {
			continue
		}
	}

}
