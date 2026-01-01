# 从输入中读取多行数据，每行数据包括id、type和score三部分，计算每个id和type组合的总分数，计算type的最高总分数。
import sys

class Solution:

    def get_max_type_score(self,mapa):
        max_score = {}
        # 对map的value进行排序
        sorted_map = sorted(mapa.items(), key=lambda x: x[1], reverse=True)
        for key,value in sorted_map.entries():
            tp = key[1:]
            print(f"type:{tp},score:{value}")
            if tp not in max_score:
                max_score[tp] = value
        return max_score    

        return
    def get_id_type_score(self):
        score_set = {}
        lines = """
c z 7
b z 2
c x 1
a z 3
a x 1
b y 5
a x 4
a y 1
b x 3
c y 1
b z 3
b y 2
c x 8
c x 2
b x 1
c y 2
a y 2
a z 7
a x 1
b x 1
c z 1
"""
        for line in lines.splitlines.split("\n"):
            if not line.strip():
                break
            a = line.split()
            id = a[0]
            tp = a[1]
            score = a[2]
            print((a[0]) + (a[1]))
            # for key,value in score_set:
            #     if 
            key = id+tp
            if key in score_set:
                score_set[key] = score_set[key] + int(score)
            else:
                score_set[key] = int(score)
        return score_set

# if __name__ == "__main__":
#     solution = Solution()
#     score_map = solution.get_id_type_score()
#     max_map = solution.get_max_type_score(score_map)
#     print(max_map)

def print_output():
    max_map ={'cx': 11, 'az': 10, 'by': 7}
    # sorted_map = sorted(max_map.items(), key=lambda x: x[1], reverse=True)
    print(max_map)
    for key,key2 in max_map.items():
        print(key[0] + " " + key[1] +" "+ str(key2))

if __name__ == "__main__":
    print_output()