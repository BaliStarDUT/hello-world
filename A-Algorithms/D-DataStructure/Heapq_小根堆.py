import heapq

class MinHeap:
    def __init__(self):
        """初始化一个空的小根堆"""
        self.heap = []
    
    def push(self, val):
        """向堆中插入元素"""
        heapq.heappush(self.heap, val)
    
    def pop(self):
        """弹出并返回堆中最小的元素"""
        if self.is_empty():
            raise IndexError("堆为空，无法弹出元素")
        return heapq.heappop(self.heap)
    
    def peek(self):
        """查看堆中最小的元素（不弹出）"""
        if self.is_empty():
            raise IndexError("堆为空，无法查看元素")
        return self.heap[0]
    
    def is_empty(self):
        """判断堆是否为空"""
        return len(self.heap) == 0
    
    def size(self):
        """返回堆中元素的数量"""
        return len(self.heap)
    
    def __str__(self):
        """返回堆的字符串表示"""
        return str(self.heap)


# 测试小根堆
if __name__ == "__main__":
    heap = MinHeap()
    
    # 插入元素
    heap.push(5)
    heap.push(3)
    heap.push(8)
    heap.push(1)
    heap.push(2)
    print("插入元素后的堆:", heap)  # 内部存储结构可能不是完全有序的，但保证堆顶是最小的
    
    # 查看堆顶元素
    print("堆顶元素:", heap.peek())  # 应该输出 1
    
    # 弹出元素
    print("弹出的元素:", heap.pop())  # 弹出 1
    print("弹出元素后的堆:", heap)
    print("新的堆顶元素:", heap.peek())  # 现在堆顶是 2
    
    # 测试堆的大小和空状态
    print("堆的大小:", heap.size())  # 4
    print("堆是否为空:", heap.is_empty())  # False
    
    # 弹出所有元素（会按从小到大的顺序）
    print("弹出所有元素:")
    while not heap.is_empty():
        print(heap.pop(), end=" ")  # 2 3 5 8
