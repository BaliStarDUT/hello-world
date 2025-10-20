# number-of-islands
# https://leetcode.cn/problems/number-of-islands/

class Solution(object):
    def numIslands_dfs(self, grid):
        """
        :type grid: List[List[str]]
        :rtype: int
        """
        m, n = len(grid), len(grid[0])
        def dfs(i, j):
            if i<0 or i>=m or j<0 or j>=n or grid[i][j] == 0:
                return
            grid[i][j] = 0
            dirs = (-1, 0, 1, 0, -1)

            for a, b in zip(dirs, dirs[1:]):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y] == 1:
                    dfs(x, y)

        island = 0        
        for i in range(len(grid)):
            for j in range(len(grid[i])):
                # print("i:",i,"j:",j)
                if int(grid[i][j]) == 1:
                    dfs(i,j)
                    island +=1
        return island

    def numIslands_bfs(self, grid):
        island_count = 0
        m,n = len(grid),len(grid[0])
        def bfs(i,j):
            if i<0 or i>=m or j<0 or j>=n or grid[i][j] == 0:
                return
            queue = [(i,j)]
            while queue:
                i,j = queue.pop(0)
                dirs = (-1, 0, 1, 0, -1)

                for a, b in zip(dirs, dirs[1:]):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and grid[x][y] == 1:
                        queue.append((x,y))
                        grid[x][y] = 0

        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    bfs(i,j)
                    island_count +=1
        return island_count
           
if __name__ == "__main__":
    grid1 = [[1,1,0,0,0],[1,1,0,0,0],[0,0,1,0,0],[0,0,0,1,1]]
    grid = [[1,1,1,1,0],[1,1,0,1,0],[1,1,0,0,0],[0,0,0,0,0]]
    grid2= [[1,1,0,0,0],[1,1,0,0,0],[0,0,1,0,0],[0,0,0,1,1]]
    # print(Solution().numIslands(grid2))
    print(Solution().numIslands_dfs(grid))
