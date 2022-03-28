import sys 
input = sys.stdin.readline

dx = [0,-1,-1,-1,0,1,1,1]
dy = [-1,-1,0,1,1,1,0,-1] #대각선 1,3,5,7

n,m = map(int,input().split())
arr = []
for i in range(n):
    arr.append(list(map(int,input().split())))
cloud = [[n-1,0],[n-1,1],[n-2,0],[n-2,1]] #초기 구름 위치

for _ in range(m): #m번 이동
    d,s = map(int,input().split())
    d -= 1

    for i in range(len(cloud)): #1.구름 이동
        cloud[i][0] = (cloud[i][0] + dx[d] * s) % n
        cloud[i][1] = (cloud[i][1] + dy[d] * s) % n

    visit = [[False]*n for _ in range(n)] #구름 잇던 자리 기억
    for x,y in cloud:
        visit[x][y] = True
    
    for x,y in cloud: #2. 각 구름이 있는 칸 + 1
        arr[x][y] += 1

    tmp = []
    for x,y in cloud: #4. 대각선에 물있는 바구니수만큼 +
        cnt = 0
        for d in range(1,8,2):
            nx = x + dx[d]
            ny = y + dy[d]
            if 0 <= nx < n and 0 <= ny < n:
                if arr[nx][ny] != 0:
                    cnt += 1
        tmp.append([x,y,cnt])
    for x,y,cnt in tmp:
        arr[x][y] += cnt

    cloud = []
    for i in range(n): #5. 2이상 모든 칸 구름생성, 원래구름있던 자리 제외
        for j in range(n):
            if arr[i][j] >= 2 and not visit[i][j]:
                cloud.append([i,j])
                arr[i][j] -= 2

total = 0
for i in range(n):
    for j in range(n):
        total += arr[i][j]

print(total)