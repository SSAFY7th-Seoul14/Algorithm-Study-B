#include <iostream>
#include <vector>
#include <math.h>
#include <algorithm>
#include <queue>
using namespace std;

int a[4003];
int b[4003];
int c[4003];
int d[4003];
long long ans;
int n;
int main(void)
{
	ios_base::sync_with_stdio(false);  cin.tie(NULL); cout.tie(NULL);
	cin >> n;
	for (int i = 0; i < n; i++)
	{
		cin >> a[i];
		cin >> b[i];
		cin >> c[i];
		cin >> d[i];
	}
	vector <int> v1, v2;
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			v1.push_back(a[i] + b[j]);
			v2.push_back(c[i] + d[j]);
		}
	}
	sort(v2.begin(), v2.end());

	int s = v1.size();
	int low, up;
	for (int i = 0; i < s; i++)
	{
		low = lower_bound(v2.begin(), v2.end(), v1[i] * -1) - v2.begin();
		up = upper_bound(v2.begin(), v2.end(), v1[i] * -1) - v2.begin();
		ans += (up - low);
	}
	cout << ans << "\n";
	return 0;
}
