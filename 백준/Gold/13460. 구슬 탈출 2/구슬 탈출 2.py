from collections import deque
#     위       왼      아래    오른
dr = ((-1,0), (0,-1), (1,0), (0,1))

def bfs(ri, rj, bi, bj):
    q = deque()
    q.append((ri, rj, bi, bj))
    visited = set()
    visited.add((ri, rj, bi, bj))
    cnt = 0
    while q:
        for _ in range(len(q)):         # R-B 동시 이동 -> 한 층씩 진행해야 함
            cri, crj, cbi, cbj = q.popleft()
            if cnt > 10:                # 10이 넘어가면 -1
                return -1
            if arr[cri][crj] == "O":    # 0에 도착하면 종료
                return cnt
            for di, dj in dr:           # 네 방향 확인
                nri, nrj = cri, crj     # 빨간 공
                while True:             # 벽 또는 0에 도착할 때까지 반복
                    nri, nrj = nri + di, nrj + dj
                    if arr[nri][nrj] == "#":
                        nri, nrj = nri - di, nrj - dj
                        break
                    if arr[nri][nrj] == "O":
                        break
                nbi, nbj = cbi, cbj     # 파란 공
                while True:             # 벽 또는 0에 도착할 때까지 반복
                    nbi, nbj = nbi + di, nbj + dj
                    if arr[nbi][nbj] == "#":
                        nbi, nbj = nbi - di, nbj - dj
                        break
                    if arr[nbi][nbj] == "O":
                        break
                if arr[nbi][nbj] =="O": # 파란 공이 0에 도착하는 방향으로 기울이면 continue
                    continue
                if nri == nbi and nrj == nbj:    # R / B 위치가 같으면
                    if abs(nri - cri) + abs(nrj - crj) > abs(nbi - cbi) + abs(nbj - cbj):   # 더 많이 움직인 쪽이 -1
                        nri, nrj = nri - di, nrj - dj
                    else:
                        nbi, nbj = nbi - di, nbj - dj
                if (nri, nrj, nbi, nbj) not in visited: # 최종 도착지가 방문한 곳 아니었으면 q / visited에 추가
                    q.append((nri, nrj, nbi, nbj))
                    visited.add((nri, nrj, nbi, nbj))
        cnt += 1
    return -1

n,m = map(int, input().split())

arr = []
for _ in range(n):
    arr.append(list(input()))

for i in range(n):
    for j in range(m):
        if arr[i][j] == "R":
            ri, rj = i, j
        if arr[i][j] == "B":
            bi, bj = i, j

print(bfs(ri, rj, bi, bj))