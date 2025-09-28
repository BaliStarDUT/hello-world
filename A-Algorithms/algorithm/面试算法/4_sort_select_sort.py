def sort(num_list):
    # 选择排序
    for i in range(len(num_list)):
        min_index = i
        for j in range(i + 1, len(num_list)):
            if num_list[j] < num_list[min_index]:
                min_index = j
        num_list[i], num_list[min_index] = num_list[min_index], num_list[i]
    return num_list

if __name__ == "__main__":
    test_list = [64, 25, 12, 22, 11]
    print("排序前:{}".format(test_list))

    sorted_list = sort(test_list)
    print("排序后:", sorted_list)