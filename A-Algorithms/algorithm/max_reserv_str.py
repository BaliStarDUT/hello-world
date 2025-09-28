# longest-palindromic-substring
def find_longest_palindromic_substring(string1):
    str_list =[]
    # for i in range(len(string1)-1):
    #     current = string1[i]
    #     for j in range
    #     str_list.append(current)




def find_longest_palindromic_substring(string1):
    str_list =[]
    for i in range(len(string1)-1):
        current = string1[i]
        for j in range(i+1,len(string1)):
            current += string1[j]
            str_list.append(current)
    max_len = 0
    max_str = ""
    for i in str_list:
        if i == i[::-1]:
            if len(i) > max_len:
                max_len = len(i)
                max_str = i
    return max_str


if __name__ == '__main__':
    print(find_longest_palindromic_substring("abcddccba"))


def find_longest_palindromic_substring_optimized(s):
    if not s:
        return ""
    
    start = 0
    max_len = 1
    
    def expand_around_center(left, right):
        while left >= 0 and right < len(s) and s[left] == s[right]:
            left -= 1
            right += 1
        return s[left+1:right]
    
    for i in range(len(s)):
        # 奇数长度
        odd = expand_around_center(i, i)
        if len(odd) > max_len:
            max_len = len(odd)
            start = i - (len(odd)-1)//2
        
        # 偶数长度
        even = expand_around_center(i, i+1)
        if len(even) > max_len:
            max_len = len(even)
            start = i - (len(even)-2)//2
    
    return s[start:start+max_len]