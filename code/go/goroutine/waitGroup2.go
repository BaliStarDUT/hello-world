package main

import (
	"io/ioutil"
	"log"
	"net/http"
	"sync"
)

func main() {
	urls := []string{
		"http://www.reddit.com/r/aww.json",
		"http://www.reddit.com/r/funny.json",
		"http://www.reddit.com/r/programming.json",
	}
	jsonResponse := make(chan string)
	var wg sync.WaitGroup
	log.Println(len(urls))
	wg.Add(len(urls))
	for _, url := range urls {
		go func(url string) {
			defer wg.Done()
			res, err := http.Get(url)
			if err != nil {
				log.Fatalf("1", err)
				return
			}
			defer res.Body.Close()
			body, err := ioutil.ReadAll(res.Body)
			if err != nil {
				log.Fatalf("2", err)
				return
			}
			jsonResponse <- string(body)
		}(url)
	}
	go func() {
		log.Println(len(jsonResponse))
		for response := range jsonResponse {
			log.Println(response)
		}
	}()
	wg.Wait()
}
