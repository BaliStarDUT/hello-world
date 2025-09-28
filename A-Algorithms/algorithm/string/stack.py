class Stack:
    stack=[]
    def __init__(self,s):
        for i in s:
            self.stack.append(i)
        print(self.stack)

    def print(self):
        print("stack:-------")
        for i in self.stack:
            print(i)

    def pop(self):
        if len(self.stack) > 1:
            i = self.stack[len(self.stack)-1]
            print(i)
            self.stack = self.stack[0:len(self.stack)-2]
        else:
            # i = self.stack[0]
            self.stack = []
    def get(self):
        if len(self.stack) >= 1:
            return self.stack[len(self.stack)-1]
        else:
            return ""
    def push(self,c):
        self.stack.append(c)
    def lenth(self):
        return len(self.stack)


class Solution:
    def isValid(self, s: str) -> bool:
        stack = Stack("")
        for i in s:
            if stack.lenth == 0:
                stack.push(i)
                print("push:"+i)
                stack.print()
                continue
            j = stack.get()
            print("stack.get():{}".format(j))
            stack.print()

            if i == ")" and stack.get() == "(":
                print("pop:{}".format(j))
                stack.pop()
            elif i == "]" and stack.get() == "[":
                print("pop:{}".format(j))
                stack.pop()
            elif i == "}" and stack.get() == "{":
                stack.pop()
                stack.print()
            else:
                stack.push(i)
                print("push:"+i)
                stack.print()

        print("stack.get()-------:{}".format(stack.get()))
        stack.print()
        print(stack.lenth())
        if stack.lenth() ==0:
            return True
        else:
            return False

a = Solution().isValid("([])")
print(a)    