package main

import (
	"fmt"
	"log"
	"os"
	// "paas/ac-agent/agent"

	"github.com/fsouza/go-dockerclient"
)

func main() {
	endpoint := "unix:///var/run/docker.sock"
	client := UseSocket(endpoint)
	// endpoint := "tcp://192.168.99.100:2376"
	// client := UseTLS(endpoint)
	showImages(client)
	showContainers(client)
	buildImage(client)
	startContainer(&client)
	// TestInstanceManager()
}

// func TestInstanceManager() {
// 	config := new(agent.AgentConfig)
// 	config.DockerEndpoint = "tcp://192.168.99.101:2376"
// 	zagent := agent.NewInstanceManager(config)
//
// 	instanceNum, err := zagent.GetAllInstances()
// 	number := zagent.GetContainerManager().GetCurrContainerNum()
// 	log.Println(number)
// 	log.Println(instanceNum, err.Error())
// }
func showImages(client docker.Client) {
	imgs, err := client.ListImages(docker.ListImagesOptions{All: true})
	if err != nil {
		log.Fatalf("endpoint:%s,fail: %s", client.Endpoint(), err.Error())
		panic(err)
	}
	fmt.Println("Images:")
	for _, img := range imgs {
		fmt.Println("ID: ", img.ID)
		fmt.Println("RepoTags: ", img.RepoTags)
		fmt.Println("Created: ", img.Created)
		fmt.Println("Size: ", img.Size)
		fmt.Println("VirtualSize: ", img.VirtualSize)
		fmt.Println("ParentId: ", img.ParentID)
	}
}
func showContainers(client docker.Client) {
	containers, err := client.ListContainers(docker.ListContainersOptions{All: true})
	if err != nil {
		panic(err)
	}
	fmt.Println("Show containers:")
	for _, container := range containers {
		fmt.Println("command", container.Command)
		fmt.Println("created: ", container.Created)
		fmt.Println("id: ", container.ID)
		fmt.Println("image: ", container.Image)
		fmt.Println("names: ", container.Names)
		fmt.Println("networks: ", container.Networks)
		fmt.Println("ports: ", container.Ports)
		fmt.Println("SizeRootFs:", container.SizeRootFs)
		fmt.Println("State:", container.State)
		fmt.Println("Status:", container.Status)
	}
}

func buildImage(client docker.Client) {
	opts := docker.BuildImageOptions{
		Name: "yang_test_Image:20180111",
		// Dockerfile:          "Dockerfile",
		NoCache:             true,
		SuppressOutput:      true,
		RmTmpContainer:      true,
		ForceRmTmpContainer: true,
		ContextDir:          "testing/data",
		Memory:              1024,
		Memswap:             -1,
	}
	client.BuildImage(opts)
	// client.BuildImage(docker.BuildImageOptions{Dockerfile: ""})
}
func startContainer(client *docker.Client) {
	opts := docker.CreateContainerOptions{
		Name: "nginx_james2",
		Config: &docker.Config{
			Image:        "nginx",
			Cmd:          []string{"nginx", "-g", "daemon off;"},
			ExposedPorts: map[docker.Port]struct{}{"91/tcp": {}},
			Hostname:     "james",
		},
		HostConfig: &docker.HostConfig{
			Memory:      int64(104857600),
			MemorySwap:  -1,
			CPUShares:   int64(512),
			NetworkMode: "bridge",
			Privileged:  false,
			// PortBindings
		},
	}
	container, err := client.CreateContainer(opts)
	if err != nil {
		log.Fatal(err)
	}
	// container.HostConfig = &docker.HostConfig{
	// 	Memory:      int64(307200),
	// 	MemorySwap:  -1,
	// 	CPUShares:   int64(512),
	// 	NetworkMode: "bridge",
	// 	Privileged:  false,
	// 	// PortBindings
	// }
	//新版API不支持两个参数了
	err = client.StartContainer(container.ID, nil)
	if err != nil {
		log.Fatal(err)
	}
	container, err = client.InspectContainer(container.ID)
	if err != nil {
		log.Fatal(err)
	}
	// container
	showContainers(*client)
}
func UseTLS(endpoint string) docker.Client {
	// endpoint := "tcp://192.168.99.100:2376"
	os.Setenv("DOCKER_CERT_PATH", ".")
	path := os.Getenv("DOCKER_CERT_PATH")
	ca := fmt.Sprintf("%s/ca.pem", path)
	cert := fmt.Sprintf("%s/cert.pem", path)
	key := fmt.Sprintf("%s/key.pem", path)

	client, err := docker.NewTLSClient(endpoint, cert, key, ca)
	if err != nil {
		log.Fatal(err)
	}
	return *client
}

func UseTLS_re(endpoint string) docker.Client {
	// endpoint := "tcp://192.168.99.100:2376"
	os.Setenv("DOCKER_CERT_PATH", "/home/james/.docker/machine/certs")
	path := os.Getenv("DOCKER_CERT_PATH")
	ca := fmt.Sprintf("%s/ca.pem", path)
	cert := fmt.Sprintf("%s/cert.pem", path)
	key := fmt.Sprintf("%s/key.pem", path)
	client, err := docker.NewTLSClient(endpoint, cert, key, ca)
	if err != nil {
		log.Fatal(err)
	}
	return *client
}

func UseSocket(endpoint string) docker.Client {
	// endpoint := "unix:///var/run/docker.sock"
	client, err := docker.NewClient(endpoint)
	if err != nil {
		panic(err)
	}
	return *client
}
