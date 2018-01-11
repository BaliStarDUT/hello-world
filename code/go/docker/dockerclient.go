package main

import (
	"fmt"
	"log"
	"os"

	"github.com/fsouza/go-dockerclient"
)

func main() {
	UseTLS()
}

func UseTLS() {
	endpoint := "tcp://172.19.128.134:8014"
	os.Setenv("DOCKER_CERT_PATH", ".")
	path := os.Getenv("DOCKER_CERT_PATH")
	ca := fmt.Sprintf("%s/ca.pem", path)
	cert := fmt.Sprintf("%s/cert.pem", path)
	key := fmt.Sprintf("%s/key.pem", path)
	client, err := docker.NewTLSClient(endpoint, cert, key, ca)
	if err != nil {
		log.Fatal(err)
	}
	imgs, err := client.ListImages(docker.ListImagesOptions{All: true})
	if err != nil {
		panic(err)
	}
	for _, img := range imgs {
		fmt.Println("", img.Size)
		fmt.Println("ID: ", img.ID)
		fmt.Println("RepoTags: ", img.RepoTags)
		fmt.Println("Created: ", img.Created)
		fmt.Println("Size: ", img.Size)
		fmt.Println("VirtualSize: ", img.VirtualSize)
		fmt.Println("ParentId: ", img.ParentID)
	}
}

func UseTLS_re() {
	endpoint := "tcp://192.168.99.100:2376"
	os.Setenv("DOCKER_CERT_PATH", "/home/james/.docker/machine/certs")
	path := os.Getenv("DOCKER_CERT_PATH")
	ca := fmt.Sprintf("%s/ca.pem", path)
	cert := fmt.Sprintf("%s/cert.pem", path)
	key := fmt.Sprintf("%s/key.pem", path)
	client, err := docker.NewTLSClient(endpoint, cert, key, ca)
	if err != nil {
		log.Fatal(err)
	}
	imgs, err := client.ListImages(docker.ListImagesOptions{All: true})
	if err != nil {
		panic(err)
	}
	for _, img := range imgs {
		fmt.Println("", img.Size)
		fmt.Println("ID: ", img.ID)
		fmt.Println("RepoTags: ", img.RepoTags)
		fmt.Println("Created: ", img.Created)
		fmt.Println("Size: ", img.Size)
		fmt.Println("VirtualSize: ", img.VirtualSize)
		fmt.Println("ParentId: ", img.ParentID)
	}
}

func UseSocket() {
	endpoint := "unix:///var/run/docker.sock"
	client, err := docker.NewClient(endpoint)
	if err != nil {
		panic(err)
	}
	imgs, err := client.ListImages(docker.ListImagesOptions{All: true})
	if err != nil {
		panic(err)
	}
	for _, img := range imgs {
		fmt.Println("", img.Size)
		fmt.Println("ID: ", img.ID)
		fmt.Println("RepoTags: ", img.RepoTags)
		fmt.Println("Created: ", img.Created)
		fmt.Println("Size: ", img.Size)
		fmt.Println("VirtualSize: ", img.VirtualSize)
		fmt.Println("ParentId: ", img.ParentID)
	}
}
