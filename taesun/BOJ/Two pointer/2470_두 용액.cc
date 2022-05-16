#include <iostream> 
#include <queue>
#include <utility>
#include <vector>
#include <algorithm>
using namespace std;
int n, ans1, ans2;
int main(void)
{
	ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	cin >> n;
	vector <int> arr;
	int temp, start = 0, end = n - 1, x;
	for (int i = 0; i < n; i++)
	{
		cin >> temp;
		arr.push_back(temp);
	}
	sort(arr.begin(), arr.end());
	int m = 2100000000;
	while (start < n && start < end)
	{
		temp = arr[start] + arr[end];
		if (m > abs(temp))
		{
			m = abs(temp);
			ans1 = arr[start];
			ans2 = arr[end];
		}
		if (temp == 0)
		{
			ans1 = arr[start];
			ans2 = arr[end];
            break;
		}
		else if (temp > 0) // 합이 양수라면 절댓값이 작아지는 방향으로
		{
			end--;
			continue;
		}
		start++;   // 합이 음수라면 절댓값이 커지는 방향으로 
	}
	cout << min(ans1, ans2) << " " << max(ans1, ans2) << "\n";
	return 0;
}
