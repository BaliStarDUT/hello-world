import heapq
from typing import List, Tuple

def assignTasks(servers: List[int], tasks: List[int]) -> List[int]:
    """
    使用服务器处理任务 - Python实现
    
    参数:
    servers: List[int] - 服务器权重列表
    tasks: List[int] - 任务处理时间列表
    
    返回:
    List[int] - 每个任务分配的服务器索引
    """
    result = []
    n = len(servers)
    
    # 等待队列中的服务器: (权重, 服务器ID)
    wait_heap = []
    for i in range(n):
        heapq.heappush(wait_heap, (servers[i], i))
    
    # 运行中的服务器: (结束时间, 权重, 服务器ID)
    run_heap = []
    
    current_time = 0
    
    for i in range(len(tasks)):
        current_time = max(current_time, i)
        
        # 如果没有可用服务器，时间移动到最早完成的服务器时间
        if not wait_heap:
            end_time = run_heap[0][0]
            while run_heap and run_heap[0][0] == end_time:
                end_time, weight, server_id = heapq.heappop(run_heap)
                heapq.heappush(wait_heap, (weight, server_id))
            current_time = max(current_time, end_time)
        else:
            # 处理已完成的服务器
            while run_heap and run_heap[0][0] <= current_time:
                end_time, weight, server_id = heapq.heappop(run_heap)
                heapq.heappush(wait_heap, (weight, server_id))
        
        # 分配服务器给当前任务
        if wait_heap:
            weight, server_id = heapq.heappop(wait_heap)
            result.append(server_id)
            heapq.heappush(run_heap, (current_time + tasks[i], weight, server_id))
    
    return result

def main():
    """测试函数"""
    servers = [3, 3, 2]
    tasks = [1, 2, 3, 2, 1, 2]
    res = assignTasks(servers, tasks)
    print(res)  # 输出: [2, 2, 0, 2, 1, 2]

if __name__ == "__main__":
    main()