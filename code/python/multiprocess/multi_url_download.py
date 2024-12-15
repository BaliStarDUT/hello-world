import concurrent.futures
import requests 
import time 

def download_one(site):
    resp = requests.get(site)
    print('Read {} from {}'.format(len(resp), site))

def download_many(sites):
    with concurrent.futures.ThreadPoolExecutor(max_workers=4,thread_name_prefix='yz') as executor:
        executor.map(download_one, sites)

def main():
    sites = [
        'https://www.baidu.com',
        'https://www.zhihu.com',
        'https://www.taobao.com',
        'https://www.douban.com',
        'https://www.jianshu.com',
        'https://account.geekbang.org',
        'https://leetcode-cn.com/',
        'https://www.github.com',
        'https://open.163.com/',
        'https://www.rainymood.com/',
        'https://www.bilibili.com/',
    ]
    start = time.time()
    download_many(sites)
    end = time.time()
    print('Download {} sites by {}s'.format(len(sites), end-start))

if __name__ == "__main__":
    main()
