package main

import (
	"bufio"
	"fmt"
	"os"
)

func main() {
	var s, sep string
	for i := 1; i < len(os.Args); i++ {
		s += sep + os.Args[i]
		sep = " "
	}
	fmt.Println("hello,world")
	fmt.Println("hello,", s)
	for i, key := range os.Args[0:] {
		fmt.Println(i)
		s += sep + key
		sep = " "
	}
	fmt.Println(s)
	counts := make(map[string]int)
	input := bufio.NewScanner(os.Stdin)

	for i := 0; input.Scan() && i <= 4; i++ {
		counts[input.Text()]++
	}
	for line, n := range counts {
		if n > 0 {
			fmt.Printf("%d\t%s\n", n, line)
		}
	}
}
