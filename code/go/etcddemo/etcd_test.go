package etcddemo

import (
	"ac/registry/etcdclient/etcd"
	"log"
	"testing"
)

//
// func TestMain(m *testing.M) {
// 	flag.Set("alsologtostderr", "true")
// 	flag.Set("log_dir", "./log")
// 	flag.Set("v", "3")
// 	flag.Parse()
//
// 	ret := m.Run()
// 	os.Exit(ret)
// }
func TestEtcd(t *testing.T) {
	machines := []string{"http://172.19.128.151:2379"}
	client := etcd.NewClient(machines)

	sync := client.SyncCluster()
	log.Println(sync)
	client.SetCluster(machines)
	log.Println(client.GetCluster())
	runetcd(*client)
}

// func TestWatch(t *testing.T) {
// 	machines := []string{"http://172.19.128.151:8011"}
// 	client := etcd.NewClient(machines)
// 	log.Println("-------------", client)
// 	// watch(*client)
// 	// resp, err := client.Get("/registry/services", true, true)
// 	// if err != nil {
// 	// 	log.Fatal(err)
// 	// }
// 	// log.Println(resp)
// }
