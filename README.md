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
  - **주말간** 돌아오는 주에 맞는 주제의 문제를 **1~2개씩** 선정해오기
  - 팀원들이 선택한 1~2문제를 모아서 주중에 풀기

<br />

### ⏱ Timetable

<details>
  <summary>
1주차: 2022-02-07 ~ 2022-02-13 Stack, Queue, Linked List, Tree  
  </summary>
  
  [발행 이슈](https://github.com/SSAFY7th-Seoul14/Algorithm-Study-B/issues/7)

| 문제출처 | 문제번호                                                                                              | 문제이름    | 문제유형 | 난이도 |
| -------- | ----------------------------------------------------------------------------------------------------- | ----------- | -------- | ------ |
| SEA      | [1218](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14eWb6AAkCFAYD) | 괄호 짝짓기 | Stack    | D4     |
| BOJ      | [1966](https://www.acmicpc.net/problem/1966) | 프린터큐 | Queue    | s3     |
| BOJ      | [13335](https://www.acmicpc.net/problem/13335) | 트럭 | Queue    | s1     |
| BOJ      | [5430](https://www.acmicpc.net/problem/5430) | AC | Deque    | g5     |
| BOJ      | [1406](https://www.acmicpc.net/problem/1406) |  에디터 | Stack | s3    | 
| BOJ      | [1021](https://www.acmicpc.net/problem/1021)  | 회전하는 큐 | Queue | s4 |
| BOJ      | [1991](https://www.acmicpc.net/problem/1991)  | 트리 순회 | Tree | s1 |
  
  

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

- comment는 자유이나 가능하다면, 이번주에 풀었던 문제의 알고리즘 분류가 어떻게 되는지, <br> 어떤 문제가 어려웠는지 회고를 작성한다면 개인에게도 도움되고 다른 사람이 참고하기 좋을 것 같습니다 :)
- PR comment의 기준은 Issue 발행 문제랑 질문하고 싶은 문제
- PR Merge 후에는 Delete Branch(origin/name 삭제해도 local에 있기 때문에 무방)

### ✅ Issue 양식

- Issue 제목: `n월 n주차 스터디 ✏️`

🏷️ 학습 주제
댓글로 문제를 달아주세요

댓글 양식 : `[출처] 번호 이름 / 유형 / 난이도 / 링크`

```
[BOJ] 1991 트리 순회 / 트리 / s1 /https://www.acmicpc.net/problem/1991
```

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

---

## 📌 참고 링크

- [💯 알고리즘 및 코딩 테스트 문제 풀이 챌린지 100 📝](https://github.com/ellynhan/challenge100-codingtest-study)
- 백준 티어 보는 법 -> [백준 온라인 저지(BOJ)에서 문제 난이도 보는 방법 (+ solved.ac)](https://ndb796.tistory.com/466)
