# url反转
# 反转 URL 中的路径部分，保留协议、域名和查询参数
# 示例:
# 输入: https://example.com/path/to/resource?key=value#fragment
# 输出: https://example.com/resource/to/path?key=value#fragment

def reverse_url(url):
    output = ""
    protocol, rest = url.split("//")
    # print("{},{}".format(protocol,rest))

    # output += protocol + "//"
    # print(output)
    
    path = rest.split("/")
    print(path)
    domain = path[0]
    print(domain)

    tail = path[len(path)-1]
    print(tail)

    query = ""
    if "?" in tail:
        query = "?" + tail.split("?")[1]

    print(query)

    for i in range(len(path)-2, 0, -1):
        output += path[i] + "/"

    print(tail.split("?")[0]+"/"+output)
    path2 = tail.split("?")[0]+"/"+output

    output = protocol+"//"+domain+"/"+path2 + query
    return output

print(reverse_url("https://example.com/path/to/resource?key=value#fragment"))   


# url反转
# 反转 URL 中的路径部分，保留协议、域名和查询参数
# 示例:
# 输入: https://example.com/path/to/resource?key=value#fragment
# 输出: https://example.com/resource/to/path?key=value#fragment

def reverse_url2(url):
    # 分割协议和剩余部分
    protocol_part, rest = url.split('//', 1)
    # 分割域名和后续部分
    domain_part, path_part = rest.split('/', 1)

    # 分离路径和查询参数、片段标识符
    path_end = path_part.find('?')
    if path_end == -1:
        path_end = path_part.find('#')
        if path_end == -1:
            path_end = len(path_part)

    path = path_part[:path_end]
    query_fragment = path_part[path_end:]

    # 反转路径部分
    path_segments = path.split('/')
    reversed_path = '/'.join(reversed(path_segments))

    # 组合各部分
    result = f"{protocol_part}//{domain_part}/{reversed_path}{query_fragment}"
    return result

print(reverse_url2("https://example.com/path/to/resource?key=value#fragment"))