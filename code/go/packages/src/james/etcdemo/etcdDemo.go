package etcddemo

import (
	"ac/registry/etcdclient/etcd"
	"os"
  "flag"
  "net/url"
  "net"
	"log"
  // "encoding/json"
  "ac/registry"
)

func Runetcd() {
	machines := []string{"http://172.19.128.134:8012"}
	client := etcd.NewClient(machines)
  client_true :=client
  success :=client_true.SyncCluster()
  if !success {
    log.Fatal("cannot sync machines")
  }
  for index,machine := range client_true.GetCluster(){
    log.Printf("machine[%d]: %s",index,machine)
    u,err := url.Parse(machine)
    if err!=nil{
      log.Fatal("Parse macine error: ",err)
    }
    host,_,err := net.SplitHostPort(u.Host)
    if err!=nil{
      log.Fatal("net.SplitHostPort: ",err)
    }
    log.Println(string(host))
  }
  client = etcd.NewClient(machines)
	// fmt.Println("-------------", client)
	result, err := client.Set("/foo", "bar", 0)
	if err != nil {
		log.Fatal(err)
	}
	// log.Printf("result.Action:[%s],EtcdIndex:[%d],Expiration:[%s],Nodes.Len():[%d],key:value=[%s]:[%s]",result.Action,result.EtcdIndex,result.Node.Expiration.String(),result.Node.Nodes.Len(),result.Node.Key,result.Node.Value)
  log.Printf("result.Action:[%s],EtcdIndex:[%d],key:value=[%s]:[%s]",result.Action,result.EtcdIndex,result.Node.Key,result.Node.Value,)
	result, err = client.Get("/foo", false, false)
	if err != nil {
		log.Fatal(err)
	}
  log.Printf("result.Action:[%s],EtcdIndex:[%d],key:value=[%s]:[%s]",result.Action,result.EtcdIndex,result.Node.Key,result.Node.Value,)

  badMachines := []string{"abc", "edef"}
	success = client.SetCluster(badMachines)
  log.Println("client.SetCluster(badMachines):",success)
	if success {
		log.Fatal("should not sync on bad machines")
	}
  goodMachines := []string{"http://172.19.128.134:8013"}
	success = client.SetCluster(goodMachines)
  log.Println("client.SetCluster(badMachines):",success)
	if !success {
		log.Fatal("should not sync on bad machines")
	}
  for index,machine := range client.GetCluster(){
    log.Printf("machine[%d]: %s",index,machine)
    u,err := url.Parse(machine)
    if err!=nil{
      log.Fatal("Parse macine error: ",err)
    }
    host,_,err := net.SplitHostPort(u.Host)
    if err!=nil{
      log.Fatal("net.SplitHostPort: ",err)
    }
    log.Println(string(host))
  }

}
func EtcdWatch(){
  machines := []string{"http://172.19.128.134:8012"}
	client := etcd.NewClient(machines)
  client.Watch(prefix, waitIndex, recursive, receiver, stop)
}
func EtcdRegistry(){
  os.Args[0] = "-logtostderr=true"
	flag.Parse()
  machines := []string{"http://172.19.128.134:8012"}
  register := registry.NewEtcdRegistry(machines)
  log.Print(register)
  err := register.Start()
  if err != nil {
		log.Fatal(err)
	}
  // register.
}
