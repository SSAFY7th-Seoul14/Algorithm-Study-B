#include <iostream>
#include <vector>
#include <queue>
#include <utility>
using namespace std;
int r, c ;
char arr[50][50];
bool visit[50][50];
int dx[4] = {0,0,-1,1};
int dy[4] = {1,-1,0,0};
int ans = -1;
queue<pair <pair <int, int> , pair <int , int>> > water;

// 되돌아가지 못하도록  visit 설정해야됨   이거 안해서 메모리 초과 뜸 
// 변수 2개를 동시에 bfs하는 문제 
// 반드시 물이 고슴도치보다 먼저 움직어야됨 -> 시작할때 물을 먼저 집어넣고 고슴도치 집어넣으면 해결 


void bfs()
{
	while (!water.empty())
	{
		int x = water.front().first.first;
		int y = water.front().first.second;
		int flag = water.front().second.first;
		int time = water.front().second.second;

		for (int i = 0; i < 4; i++)
		{
			if ((x + dx[i] >= 0 && x + dx[i] <= r - 1) && (y + dy[i] >= 0 && y + dy[i] <= c - 1))
			{
				if (arr[x + dx[i]][y + dy[i]] == '.')
				{
					if (flag == 0)
					{
						arr[x + dx[i]][y + dy[i]] = '*';
						water.push(make_pair(make_pair(x+dx[i], y+dy[i]), make_pair(0, time+1)));
					}
					else if(flag == 1 && visit[x + dx[i]][y + dy[i]] == false)
					{
						visit[x + dx[i]][y + dy[i]] = true;
						water.push(make_pair(make_pair(x + dx[i], y + dy[i]), make_pair(1, time + 1)));
					}
				}
				else if (arr[x + dx[i]][y + dy[i]] == 'D' && flag == 1)
				{
					ans = time + 1; 
					return;
				}
			}
		}
		water.pop();
	}
	return;
}


int main()
{
	cin >> r >> c;
	for (int i = 0; i < r; i++)
	{
		for (int j = 0; j < c; j++)
		{
			cin >> arr[i][j];
			if (arr[i][j] == '*')
				water.push(make_pair(make_pair(i, j), make_pair(0, 0)));
		}
	}
	for (int i = 0; i < r; i++)
	{
		for (int j = 0; j < c; j++)
		{
			visit[i][j] = false;
			if (arr[i][j] == 'S')
			{
				visit[i][j] = true;
				water.push(make_pair(make_pair(i, j), make_pair(1, 0)));
			}
				
		}
	}
	bfs();
	if (ans == -1)
		cout << "KAKTUS" << endl;
	else
		cout << ans << endl;
	return 0;
}