
class Solution(object):
    def assignTasks(self, servers, tasks):
        """
        :type servers: List[int]
        :type tasks: List[int]
        :rtype: List[int]
        """

class Server(object):
    def __init__(self,weight,task):
        self.weight=weight
        self.task=task
        self.available=True
    def __comp__(self,other):
        return self.weight-other.weight
    

def solution(servers,tasks):
    servers=[Server(weight,0) for weight in servers]
    sorted_servers = sorted(servers, key=lambda x: (x.weight, servers.index(x)))
    for i,value in enumerate(tasks):
        server=sorted_servers.pop(0)
        server.task=value
        server.available=False
        sorted_servers.append(server)
        sorted_servers = sorted(sorted_servers, key=lambda x: (x.weight, servers.index(x)))
        print(i,value)
        print([server.weight for server in sorted_servers])


if __name__ == "__main__":
    servers = [3,3,2]
    tasks = [1,2,3,2,1,2]
    solution(servers,tasks)