package main

import (
	"os"
	"strings"
)

func main() {
	var builder1 strings.Builder
	consts := make(map[string]int)
	files := os.Args[1:]
	if len(files) == 0 {
		count
	}
}
