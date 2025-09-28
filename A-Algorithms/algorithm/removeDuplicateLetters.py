def removeDuplicateLetters(str):
    stack = []
    # seen  = set()
    for char in str:
        if char not in stack:
            stack.append(char)
            # while stack and char < stack[-1] and str.count(stack[-1]) > 0:
            #     seen.remove(stack.pop())
            # stack.append(char)
    return ''.join(stack)

if __name__ == "__main__":
    print(removeDuplicateLetters("cbacdcbc"))  # Output: "acdb"
    print(removeDuplicateLetters("bcabc"))     # Output: "abc"
    print(removeDuplicateLetters("abacb"))     # Output: "abc"
    print(removeDuplicateLetters("bbcaac"))    # Output: "bac"