#include <iostream> 
#include <queue>
#include <utility>
#include <vector>
#include <algorithm>
#include <math.h>
using namespace std;
int arr[100001][3];
int dp[2][3];
int ans1, ans2, n;
/*
dp 배열 10만개 짜리를 만들면 메모리 오버됨
슬라이딩 윈도우처럼 뒤에껄 앞으로 당겨오고 , 계산하고 뒤에다 저장하는걸 반복해야함
점화식은 간단하지만 메모리 제한때문에 골치아픈 문제
*/
int main(void)
{
	ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	cin >> n; 
	for (int i = 0; i < n; i++)
		cin >> arr[i][0] >> arr[i][1] >> arr[i][2];
	int a, b, c;
	for (int i = 0; i < n; i++)
	{
		dp[0][0] = dp[1][0];
		dp[0][1] = dp[1][1];
		dp[0][2] = dp[1][2];
		dp[1][0] = arr[i][0] + max(dp[0][0], dp[0][1]);
		dp[1][1] = arr[i][1] + max(max(dp[0][0], dp[0][1]), dp[0][2]);
		dp[1][2] = arr[i][2] + max(dp[0][1], dp[0][2]);
	}
	ans1 = max(max(dp[1][0], dp[1][1]), dp[1][2]);
	dp[0][0] = dp[1][0] = 0;
	dp[0][1] = dp[1][1] = 0;
	dp[0][2] = dp[1][2] = 0;
	for (int i = 0; i < n; i++)
	{
		dp[0][0] = dp[1][0];
		dp[0][1] = dp[1][1];
		dp[0][2] = dp[1][2];
		dp[1][0] = arr[i][0] + min(dp[0][0], dp[0][1]);
		dp[1][1] = arr[i][1] + min(min(dp[0][0], dp[0][1]), dp[0][2]);
		dp[1][2] = arr[i][2] + min(dp[0][1], dp[0][2]);
	}
	ans2 = min(min(dp[1][0], dp[1][1]), dp[1][2]);
	cout << ans1 << endl;
	cout << ans2 << endl;
	return 0;
}