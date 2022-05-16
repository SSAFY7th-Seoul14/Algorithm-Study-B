#include <iostream>
#include <algorithm>
#include <math.h>
#include <vector>
#include <utility>
#include <queue>
#include <iostream>
#include <fstream>
using namespace std;
long long ans , m_index;
vector<vector <pair<int , int>>> v; 
bool visit[100001];
int n;
// 가장 거리가 긴 거리는 트리 중간 지점의 연결로 구성되지 않는다.  
// 왜냐하면 모든 가중치가 양수라서 무조건 맨끝까지 내려간게 더 길수밖에 없음
// -> dfs 사용하는 것이 좋다 (리프 노드 빠르게 찾기)
void dfs(int index , long long sum)
{
	visit[index] = true;
	for (int i = 0; i < v[index].size(); i++)
	{
		int next = v[index][i].first;
		if ((next != -1) && (visit[next] == false))
		{
			long long value = sum + v[index][i].second;
			if (value > ans)
			{
				ans = value;
				m_index = next;
			}
			dfs(next, value);
		}
		else if (next == -1)
			break;
	}
	return;
}
int main(void)
{
	ios_base::sync_with_stdio(false);  cin.tie(NULL); cout.tie(NULL);
	cin >> n;
	int a, b, c;
	for (int i = 0; i <= n; i++)
		v.push_back(vector <pair <int, int>>());
	for (int i = 0; i < n; i++)
	{
		cin >> a;
		while (1)
		{
			cin >> b;
			if (b == -1)
			{
				v[a].push_back(make_pair(-1, 0));
				break;
			}
			else 
			{
				cin >> c;
				v[a].push_back(make_pair(b, c));
			}
		} 
	}
	
	dfs(1 , 0);
	for (int i = 1; i <= n; i++)
		visit[i] = false;
	ans = 0;
	dfs(m_index, 0);
	cout << ans << endl;
	return 0;
}