# design-circular-queue https://leetcode.cn/problems/design-circular-queue/description/
class MyCircularQueue:
    def __init__(self, k: int):
        self.front = self.rear = 0
        self.elements = [0] * (k + 1)

    def enQueue(self, value: int) -> bool:
        if self.isFull():
            return False
        self.elements[self.rear] = value
        self.rear = (self.rear + 1) % len(self.elements)
        return True

    def deQueue(self) -> bool:
        if self.isEmpty():
            return False
        self.front = (self.front + 1) % len(self.elements)
        return True

    def Front(self) -> int:
        return -1 if self.isEmpty() else self.elements[self.front]

    def Rear(self) -> int:
        return -1 if self.isEmpty() else self.elements[(self.rear - 1) % len(self.elements)]

    def isEmpty(self) -> bool:
        return self.rear == self.front

    def isFull(self) -> bool:
        return (self.rear + 1) % len(self.elements) == self.front

# 作者：力扣官方题解
# 链接：https://leetcode.cn/problems/design-circular-queue/solutions/1713181/she-ji-xun-huan-dui-lie-by-leetcode-solu-1w0a/
# 来源：力扣（LeetCode）
# 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


# Your MyCircularQueue object will be instantiated and called as such:
# obj = MyCircularQueue(k)
# param_1 = obj.enQueue(value)
# param_2 = obj.deQueue()
# param_3 = obj.Front()
# param_4 = obj.Rear()
# param_5 = obj.isEmpty()
# param_6 = obj.isFull()
