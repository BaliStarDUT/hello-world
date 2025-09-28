package main

//使用单线程 CPU 处理任务
//1. 使用两个堆，分别存储正在运行的任务、等待运行的任务。任务内包含任务时长、任务ID
//2. 遍历任务列表，将任务放入等待运行的任务堆中
//3. 从等待运行任务堆中获取运行时间最短的任务，放入正在运行的任务堆中
//4. 检查正在运行的任务堆中，是否有任务完成。若有重复3-4
//5. 若等待运行任务堆为空，且正在运行任务堆不为空，则将时间前进到正在运行任务堆中最早完成的任务

import (
	"container/heap"
	"fmt"
)

func assignTasks(tasks [][]int) []int {
	res := make([]int, 0)

	waitHeap := make(WaitHeap, 0)
	heap.Init(&waitHeap)

	for i := 0; i < len(tasks); i++ {
		heap.Push(&waitHeap, CPU{
			Order:   tasks[i][0],
			EndTime: tasks[i][1],
		})
		curTime := 0
		for waitHeap.Len() > 0 && waitHeap[0].EndTime <= curTime {
			node := heap.Pop(&waitHeap).(CPU)
			heap.Push(&waitHeap, CPU{
				Order:   node.Order,
				EndTime: curTime + node.EndTime,
			})
		}
	}

	for i := 0; i < len(tasks); i++ {
		curTime = max(curTime, i)
		node := waitHeap[0]
		res = append(res, node.Order)
		heap.Pop(&waitHeap)

		curTime += node.EndTime
	}

	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

type CPU struct {
	Order   int // 权重
	EndTime int // 结束时间
}

// 等待堆
type WaitHeap []CPU

func (h WaitHeap) Len() int {
	return len(h)
}

// 小根堆<,大根堆变换方向>
func (h WaitHeap) Less(i, j int) bool {
	if h[i].EndTime == h[j].EndTime {
		return h[i].Order < h[j].Order
	}
	return h[i].EndTime < h[j].EndTime
}

func (h WaitHeap) Swap(i, j int) {
	h[i], h[j] = h[j], h[i]
}

func (h *WaitHeap) Push(x interface{}) {
	*h = append(*h, x.(CPU))
}

func (h *WaitHeap) Pop() interface{} {
	value := (*h)[len(*h)-1]
	*h = (*h)[:len(*h)-1]
	return value
}
func main() {
	tasks := [][]int{{1, 2}, {2, 4}, {3, 2}, {4, 1}}

	res := assignTasks(tasks)
	fmt.Println(res)
}

// 输入：tasks = [[1,2],[2,4],[3,2],[4,1]]
// 输出：[0,2,3,1]

// class Solution:
//     def getOrder(self, tasks: List[List[int]]) -> List[int]:
//         n = len(tasks)
//         indices = list(range(n))
//         indices.sort(key=lambda x: tasks[x][0])

//         ans = list()
//         # 优先队列
//         q = list()
//         # 时间戳
//         timestamp = 0
//         # 数组上遍历的指针
//         ptr = 0

//         for i in range(n):
//             # 如果没有可以执行的任务，直接快进
//             if not q:
//                 timestamp = max(timestamp, tasks[indices[ptr]][0])
//             # 将所有小于等于时间戳的任务放入优先队列
//             while ptr < n and tasks[indices[ptr]][0] <= timestamp:
//                 heapq.heappush(q, (tasks[indices[ptr]][1], indices[ptr]))
//                 ptr += 1
//             # 选择处理时间最小的任务
//             process, index = heapq.heappop(q)
//             timestamp += process
//             ans.append(index)

//         return ans
