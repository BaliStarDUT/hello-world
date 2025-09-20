# input a int list, and a target int, find the index of two elements that sum to the target
#https://leetcode.cn/problems/two-sum/description/
def find_index_for_two_sum(int_list, target):
    index_list = []
    for index,value in enumerate(int_list):
        if value > target:
            continue
        for index2,value2 in enumerate(int_list[index+1:]):
            if value + value2 == target:
                index_list.append(index)
                index_list.append(index+index2+1)
                return index_list
            
    return index_list

def find_index_for_two_sum_dict(int_list,target):
    index_dic ={}
    for index,value in enumerate(int_list):
        if target -value in index_dic:
            return [index_dic[target-value],index]
        else:
            index_dic[value] = index

# test the function
if __name__ == "__main__":
    int_list = [2, 7, 11, 15]
    for i in int_list:
        print(i)
    for i in range(len(int_list)):
        print(i)
        print(int_list[i])
    target = 9
    print(find_index_for_two_sum_dict(int_list, target)) # [0, 1]
    
    int_list = [3, 2, 4]
    target = 6
    print(find_index_for_two_sum_dict(int_list, target)) # [1, 2]
    
    int_list = [3, 3]
    target = 6
    print(find_index_for_two_sum_dict(int_list, target)) # [0, 1]
    
    int_list = [1, 2, 3, 4, 5]
    target = 10
    print(find_index_for_two_sum_dict(int_list, target)) # []