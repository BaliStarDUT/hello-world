import threading  
  
def worker(num):  
    print(f'Worker {num} starting')  
    # 模拟一些工作  
    import time  
    time.sleep(1)  
    print(f'Worker {num} done')  
  
threads = []  
for i in range(5):  
    t = threading.Thread(target=worker, args=(i,))  
    threads.append(t)  
    t.start()  
  
# 等待所有线程完成  
for t in threads:  
    t.join()