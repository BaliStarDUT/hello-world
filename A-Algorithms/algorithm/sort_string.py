def sort_str(string1):
    chars1=""
    dot=""
    for char in string1:
        if char.isalpha():
            chars1+=char
        else:
            dot+=char
    return chars1+dot

def sort_str2(string1):
    l= len(string1)
    for i in range(0,l-1):
        char=string1[i]
        if char.isalpha():
            continue
        else:
            string1[i]=""
            string1+=char

    return string1

a="b43a!2c1d@f#e$"
b="bacdfe43!21@#$"

c=sort_str2(a)
print(c)