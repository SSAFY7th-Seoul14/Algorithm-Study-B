#include <iostream>
#include <vector>
#include <list>
#include <queue>
#include <algorithm>
#include <math.h>
#include <set>
using namespace std;
#define INF 210000000000
typedef long long ll;
int n, m, k;
ll ans;
vector<ll> arr;
ll num[3];
int main(void)
{
	ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	cin >> n;
	ll temp, a, b, c;
	ans = INF;
	for (int i = 0; i < n; i++)
	{
		cin >> temp;
		arr.push_back(temp);
	}
	sort(arr.begin(), arr.end());
	n = arr.size();
	int l, r;
	// 인덱스 중복이 안되도록 주의하자 
	for (int i = 0; i < n - 2; i++)
	{
		l = i + 1;
		r = n - 1;
		// 인덱스 겹치면 안됨 
		while (l < r)
		{
			temp = arr[i] + arr[l] + arr[r];
			if (abs(temp) < ans)
			{
				ans = abs(temp);
				num[0] = arr[i];
				num[1] = arr[l];
				num[2] = arr[r];
			}
			// 양수면 오른쪽 포인터 감소 
			// 음수면 왼쪽 포인터 증가
			if (temp > 0)
				r--;
			else
				l++;
		}
	}
	
	sort(num, num + 3);
	for (int i = 0; i < 3; i++)
		cout << num[i] << " ";
		
	return 0;
}