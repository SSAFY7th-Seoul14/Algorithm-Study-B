#include <iostream>
#include <algorithm>
#include <math.h>
#include <vector>
#include <utility>
#include <queue>
using namespace std;
int n, k, ans , m;
// 각 단어에서 어떤 문자가 나왔는지 int 형태로 저장
vector <int> arr;
bool isExist[26];
bool select_arr[26];
/*
반례 
2 15
antabtica
antatica
답 : 2

k< 5이면 어떤 단어도 학습 불가 (a,n,t,i,c는 필수)
많이 나오는 글자 위주로 가르쳐도 소용 없는 경우 -> anta zzzzzz tica 같이 한 단어에 너무 많은 글자가 들어갈 수도
51퍼에서 틀림 -> k가 실제 나온 글자보다 클때 백트래킹 조건문에 걸리지 않아서 정답을 체크 못함
실제 나온 글자 수와 k 중에서 더 작은 개수만큼만 고르게 하니까 맞음

*/

void teach(int idx, int cnt)
{
	// 이미 나온 글자 수 or 배울 수 있는 최대 글자수 k중 더 적은 개수만큼 
	if (cnt == m)
	{
		int num = 0, temp, result = 0;
		for (int i = 0; i < 26; i++)
		{
			temp = 1;
			if (select_arr[i])
			{
				temp = temp << (26 - i);
				num = num | temp;
			}
		}
		for (int i = 0; i < arr.size(); i++)
		{
			if ((num & arr[i]) == arr[i])
				result++;
		}
		ans = max(result, ans);
		return;
	}
	else if (idx >= 26)
		return;
	if (isExist[idx])
	{
		// 골라도 되고 안골라도 되는 글자인 경우 
		if (!select_arr[idx])
		{
			select_arr[idx] = true;
			teach(idx + 1, cnt + 1);
			select_arr[idx] = false;
			teach(idx + 1, cnt);
		}
		// 무조건 뽑아야 하는 글자들인 경우 a,c,t,i,n
		else
			teach(idx + 1, cnt);
	}
	else
		teach(idx + 1, cnt);
	return;
}
int main(void)
{
	ios_base::sync_with_stdio(false);  cin.tie(NULL); cout.tie(NULL);
	cin >> n >> k;
	if (k < 5)
	{
		cout << 0 << endl;
		return 0;
	}
	string s;
	char c;
	int idx, temp = 0, num;
	for (int i = 0; i < n; i++)
	{
		cin >> s;
		num = 0;
		for (int j = 0; j < s.size(); j++)
		{
			temp = 1;
			c = s[j] - 97;
			isExist[c] = true;
			idx = 26 - c;
			temp = temp << idx;
			num = num | temp;
		}
		arr.push_back(num);
	}
	int cnt = 0;
	for (int i = 0; i < 26; i++)
	{
		if (isExist[i])
			cnt++;
	}
	// -5 해준건 a,n,t,i,c는 필수로 이미 뽑았다고 가정했기 때문 
	m = min(cnt - 5, k - 5);
	select_arr[0] = select_arr[2] = select_arr[8] = select_arr[13] = select_arr[19] = true;
	teach(0, 0);
	cout << ans << endl;
	return 0;
}