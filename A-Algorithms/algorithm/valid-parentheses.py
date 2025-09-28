def is_valid_parentheses(s: str) -> bool:
    stack = []
    for char in s:
        if char in "({[":
            stack.append(char)
        else:
            if not stack:
                return False
            if char == ")" and stack[-1] != "(":
                return False
            if char == "}" and stack[-1] != "{":
                return False
            if char == "]" and stack[-1] != "[":
                return False
            stack.pop()
    return not stack

class Solution:
    def isValid(self, s: str) -> bool:
        return is_valid_parentheses(s) 
    
if __name__ == "__main__":
    s = "()"
    print(is_valid_parentheses(s))