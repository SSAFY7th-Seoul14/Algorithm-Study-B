#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <utility>
#include <stack>
using namespace std;
stack <char> s;
stack <char> temp;
int cnt[40];
/*
* 백준 문자열 폭발
* CC44 같은 중첩된 경우가 처리 안됨 -> 안쪽은 지워지는데 바깥이 그대로 남는다
* 반례 : baba  ab
* 세그폴트 났던 이유는 폭탄 문자의 길이가 더 긴 경우가 있어서
* 반례2: 123 13
* 2퍼에서 계속 틀린 이유는 temp가 비었는데 reverse하고 집어넣어서?

* 푼 방식 :앞에서부터 문자 하나씩 읽으면서 만약 문자가 폭탄 끝 문자랑 같으면 폭탄 길이만큼 스택에서 빼서 검사
  검사해서 폭탄 문자열이면 제외하고 아니면 다시 스택에 넣고 진행
*/

int main(void)
{
	string str, bomb;
	vector <char> ans;
	cin >> str;
	cin >> bomb;
	int idx = 0;
	char c;
	string temp = "";
	int b_size = bomb.size();
	if (b_size > str.size())
	{
		cout << str << "\n";
		return 0;
	}
	for (int i = 0; i < str.size(); i++)
	{
		c = str[i];
		s.push(c);
		if (c == bomb[b_size - 1] && s.size() >= b_size)
		{
			temp = "";
			for (int j = 0; j < b_size; j++)
			{
				if (!s.empty())
				{
					temp.append(1, s.top());
					s.pop();
				}
			}
			reverse(temp.begin(), temp.end());
			if (temp == bomb)
				continue;
			else
			{
				if (!temp.empty())
				{
					for (int j = 0; j < b_size; j++)
						s.push(temp[j]);
				}
			}
		}
	}
	if (s.empty())
		cout << "FRULA\n";
	else
	{	
		while (!s.empty())
		{
			c = s.top();
			ans.push_back(c);
			s.pop();
		}
		reverse(ans.begin(), ans.end());
		for (int i = 0; i < ans.size(); i++)
			cout << ans[i];
		cout << "\n";
	}
	return 0;
}