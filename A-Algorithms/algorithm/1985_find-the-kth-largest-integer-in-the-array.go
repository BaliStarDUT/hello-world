// # 1985_find-the-kth-largest-integer-in-the-array

package main

import (
	"container/heap"
	"strconv"
	"fmt"
)

type MinHeap []int

func (h MinHeap) Len() int           { return len(h) }
func (h MinHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h MinHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *MinHeap) Push(x interface{}) {
	*h = append(*h, x.(int))
}

func (h *MinHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}
// KthLargest function to find the kth largest integer in the array
func KthLargest(nums []string, k int) string {
	h := &MinHeap{}
	heap.Init(h)
	anum := 0
	for _, numStr := range nums {
		num, _ := strconv.Atoi(numStr)
		heap.Push(h, num)
		fmt.Println(*h)
	}
	fmt.Println(*h)
	for i := 0; i < len(nums)-k+1; i++ {
		anum = heap.Pop(h).(int)
		fmt.Println(anum)

	}
	fmt.Println(anum)
	return strconv.Itoa(anum)

}

func main() {
	nums := []string{"3", "6", "7", "10", "2", "11", "5", "4", "8", "9"}
	k := 4
	result := KthLargest(nums, k)
	println(result) // Output: "3"
}
