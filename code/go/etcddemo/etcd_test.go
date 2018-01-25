package etcddemo

import (
	"ac/registry/etcdclient/etcd"
	"fmt"
	"testing"
)

func Testetcd(t *testing.T) {
	machines := []string{"http://172.19.128.134:8011"}
	client := etcd.NewClient(machines)
	runetcd(*client)
}

func TestWatch(t *testing.T) {
	machines := []string{"http://172.19.128.134:8011"}
	client := etcd.NewClient(machines)
	fmt.Println("-------------", client)
	watch(*client)
}
