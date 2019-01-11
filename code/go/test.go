package main

import (
	"fmt"
)

type Books struct {
	title   string
	author  string
	subject string
	book_id int
}

func printBook(book Books) {
	fmt.Printf("Book title : %s\n", book.title)
	fmt.Printf("Book author : %s\n", book.author)
	// timeInterval := time.Duration(60) * time.Second
	// timeSpan := time.Since(startAt)
	// time.Sleep(timeInterval)
	fmt.Printf("Book subject : %s\n", book.subject)
	fmt.Printf("Book book_id : %d\n", book.book_id)
}

func test() (int, int) {
	return 1, 2
}
func add(x, y int) int {
	return x + y
}
func sum(n ...int) int {
	var x int
	for _, i := range n {
		x += i
	}
	return x
}
func main3() {
	println(add(test()))
	println(sum(test()))
}

func main2() {
	// s := make([]int, 2)
	// s = test() // Error: multiple-value test() in single-value context

	x, _ := test()
	println(x)
}

func main() {
	var Book1 Books
	var Book2 Books

	Book1.title = "Go 语言"
	Book1.author = "www.runoob.com"
	Book1.subject = "Go 语言教程"
	Book1.book_id = 6495407

	Book2.title = "Python 教程"
	Book2.author = "www.runoob.com"
	Book2.subject = "Python 语言教程"
	Book2.book_id = 6495700

	/* 打印 Book1 信息 */
	printBook(Book1)

	/* 打印 Book2 信息 */
	printBook(Book2)
	fmt.Printf("address:%x\n", &Book1)
	fmt.Printf("address:%x\n", &Book2)
	fmt.Println("hello world!")
}
