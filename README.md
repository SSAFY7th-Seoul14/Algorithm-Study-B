# SSAFY 7th Algorithm Study

## 서울 14반 JAVA 알고리즘 스터디 B조

- 고태희, 김채연, 안호진, 우정무, 이태선, 이희진

### 🔥 목표

- **2월 동안의 알고리즘 진도 놓치지 않고 완료**
- **22년 2월 SW 역량 테스트 IM 등급 취득**
- **1학기 내 SW 역량 테스트 A등급 취득**

## 📌 기본 정보

- 매주 **월, 화, 목 20시~22시 웹엑스로 진행**, 그 외에는 자율적으로 모각코
- **평일 모각코는 20-21:30**
- **주말은 시간 구애 없는 자율적으로 문제 풀기**
  - **주말간** ~~돌아오는 주에 맞는 주제의~~ 문제를 **1~2개씩** 선정해오기(가져오는 유형 범위는 누적 -> 복습 차원)
  - 팀원들이 골라온 문제들을 주중에 최대한 풀기 🤙
  - [백준 그룹](https://www.acmicpc.net/group/13731) 문제집으로 발행, 실시간으로 체크 가능

<br />

### ⏱ Timetable

<details>
  <summary>
1주차: 2022-02-07 ~ 2022-02-13 Stack, Queue, Linked List, Tree  
  </summary>
  
  [발행 이슈](https://github.com/SSAFY7th-Seoul14/Algorithm-Study-B/issues/7)

| 문제출처 | 문제번호                                       | 문제이름    | 문제유형 | 난이도 |
| -------- | ---------------------------------------------- | ----------- | -------- | ------ |
| BOJ      | [1966](https://www.acmicpc.net/problem/1966)   | 프린터큐    | Queue    | s3     |
| BOJ      | [13335](https://www.acmicpc.net/problem/13335) | 트럭        | Queue    | s1     |
| BOJ      | [5430](https://www.acmicpc.net/problem/5430)   | AC          | Deque    | g5     |
| BOJ      | [1406](https://www.acmicpc.net/problem/1406)   | 에디터      | Stack    | s3     |
| BOJ      | [1021](https://www.acmicpc.net/problem/1021)   | 회전하는 큐 | Queue    | s4     |
| BOJ      | [1991](https://www.acmicpc.net/problem/1991)   | 트리 순회   | Tree     | s1     |

</details>
<details>
  <summary>
2주차: 2022-02-14 ~ 2022-02-20 완전탐색, 탐욕알고리즘, 분할정복, 백트래킹, 그래프  
  </summary>  
</details>
<details>
  <summary>
3주차: 2022-02-21 ~ 2022-02-27 그래프, 문자열
  </summary>  
</details>

<br />

---

## 📌 규칙

### ✅ 진행 방법

1. 저장소 `fork` 후
2. 각자의 원격 저장소(origin repo)에서 개인 이름으로 branch 나누기
3. 해당 branch에 `개인 이름`으로 폴더를 생성
4. 생성된 폴더에 자신의 소스코드를 업로드(폴더명에 콜론":" 안됩니다!)
5. 이때 `commit 규칙`을 지키도록한다!
6. 원본 저장소로 `Pull Request` (매주 3문제 이상, 매주 **토요일**에 Merge 진행)
7. 다른 사람들의 PR을 보고 자유롭게 코드리뷰
8. **_주의!! 기존 폴더를 지우면 안됩니다!!_** 개인 `Repository`에서 개인 폴더 내부만 수정해주세요 :)
   <br />

- **모각코** : 정해진 시간에 각자 캠키고 공부 - 질문은 채팅 혹은 마이크 켜서 나누기
- **스터디원**
  - 과제 및 주어진 관련 문제 2~3문제 풀기
  - 개별 알고리즘 목표를 정해 문제 풀고 커밋, PR하기 (개인)

### ✅ commit 규칙

- commit 메세지: `[문제 출처(플랫폼)] 문제이름 / 난이도 / 걸린시간`
- description: 문제 주소 (option)
- 터미널에서 작성법:

```
git commit -m "[BOJ] Hello World / 브론즈5 / 1분" -m "https://www.acmicpc.net/problem/2557"
```

- 플랫폼 작성법 통일:
  - [BOJ] - 백준
  - [PGS] - 프로그래머스
  - [LTC] - 리트코드
  - [CFS] - 코드포스
  - [SEA] - 삼성SW Expert Academy
  - [ETC] - 그외

### ✅ PR 규칙

- PR 제목: `이름 / 주차 / 몇 문제`

```
안호진 / 2월 2주차 / 4문제
```

- PR comment 양식 :
  - makrdown `Raw Version` 이대로 적어주시면 됩니다.
  - comment 작성시 # 넣으면 연결할 수 있는 issue, pr등을 보여줍니다. 이때 해당 주차 issue 잘 선택하시면 연결 됩니다.
  - 체크 박스 만들기는 드래그 후에 comment에 있는 checkbox 만들기로 만들기
  - 문제 링크, 커밋 링크는 해당 텍스트 드래그하셔서 붙여넣기하면 자동으로 텍스트와 링크가 적힙니다.

`View Version`

![image](https://user-images.githubusercontent.com/47904304/153404989-98a8e48f-a106-4885-946f-a38bbd160768.png)

`Raw Version`

```
## 🔗 linked issue #7

✔️ 완료 여부 check하기

- [x] [A - 프린터 큐](https://www.acmicpc.net/problem/1966)
- [x] [B - 트럭](https://www.acmicpc.net/problem/13335)
- [ ] [C - AC](https://www.acmicpc.net/problem/5430)
- [ ] [D - 에디터](https://www.acmicpc.net/problem/1406)
- [ ] [E - 회전하는 큐](https://www.acmicpc.net/problem/1021)
- [ ] [F - 트리 순회](https://www.acmicpc.net/problem/1991)

## 🚨 질문&리뷰 요청
[`[BOJ] 트럭 / 실버 1 / 2시간+`](https://github.com/SSAFY7th-Seoul14/Algorithm-Study-B/pull/6/commits/31cd76f9470801521fefd30eaf4e172bd20ae4d7) - 질문은 아니고 일단 예시입니다.
```

- 댓글로 이번주에 풀었던 문제의 알고리즘 분류가 어떻게 되는지, <br> 어떤 문제가 어려웠는지 회고를 작성한다면 개인에게도 도움되고 다른 사람이 참고하기 좋을 것 같습니다 :)
- PR comment의 기준은 Issue 발행 문제랑 질문하고 싶은 문제
- PR Merge 후에는 Delete Branch(origin/name 삭제해도 local에 있기 때문에 무방)



`🚨 PR 생성이 안보여요!`  
1. 본인 repo 상단 메뉴에서 Pull requests로 들어가셔서

  ![image](https://user-images.githubusercontent.com/47904304/153858403-f285c43b-8662-4eef-85b3-ec7731126e12.png)

2. `New Pull Request` 선택 후

  ![image](https://user-images.githubusercontent.com/47904304/153858708-b12b94b0-2eaf-4074-87d5-a7d8e21bfa6e.png)

3. head repository가 되는 본인의 repo에서 ~~main branch~~가 아닌 개인 이름 branch로 Pull Request를 생성해주시면 됩니다

  ![image](https://user-images.githubusercontent.com/47904304/153859313-c40518d3-33b4-40e3-8b7f-8eafd5ac6122.png)



### ✅ Issue 양식

- Issue 제목: `n월 n주차 스터디 ✏️`


#### *<--------------여기부터-------------->*
## 🏷️ 학습 주제
댓글로 문제를 달아주세요

댓글 양식 : `[출처] 번호 이름 / 유형 / 난이도 / 링크`

```
[BOJ] 1991 트리 순회 / 트리 / s1 /https://www.acmicpc.net/problem/1991
```
본인 문제 유형에 해당하는 label을 직접 설정해주세요!
  
n월 n주차 주제 :

#### *<---------여기까지 복사해서 사용--------->*

<br />

### 🎁 (제안사항) 폴더 구조

`개인이름/출처/유형/문제번호_문제이름`

```
hojin/boj/recursion/17478_재귀함수가 뭔가요.java
```

---

## 📌 기타 정보

- java는 소스만 복사해서 폴더 안에 .java 파일에 삽입
- BOJ 난이도 `b : bronze / s : silver / g : gold / p : platinum / d : diamond / r : ruby`
- 개인이 날린 PR은 본인이 merge하지 않고 reviewer가 검토 하고 merge하기  
  (리뷰 코멘트 남기면 좋음 ex. `O(N*long N)의 시간 복잡도로 줄일 수 있지 않을까요?`)
- 백준 기준 실버 3 이상의 문제 골라오기
- 백준 티어 보는 법 -> [백준 온라인 저지(BOJ)에서 문제 난이도 보는 방법 (+ solved.ac)](https://ndb796.tistory.com/466)
- 입출력 시간 차이 정리 -> [출력 속도 비교 [출처]: 백준](https://www.acmicpc.net/blog/view/57)

---

## 📌 참고 링크

- [💯 알고리즘 및 코딩 테스트 문제 풀이 챌린지 100 📝](https://github.com/ellynhan/challenge100-codingtest-study)
