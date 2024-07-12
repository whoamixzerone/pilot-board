## 게시판

개인 github에 업로드
branch 자유

### 필수 기술
- Java 17
- Spring  Boot
- Git & Github
- Thymeleaf
- MySQL
- JavaScript
- Spring Data JPA(Hibernate)
- Gradle
- Spring Security

### 요구사항

- 로그인
- 회원가입
- 게시글 목록
- 게시글 등록
	- 이미지 등록
- 게시글 상세보기
- 댓글 목록
- 댓글 추가(대댓글)
	- 99Depth까지 가능하도록
	- 대댓글 표시 css
- js, css 분리
- 쿼리 튜닝
	- 댓글이 많아도 속도 저하를 최소화 할 수 있는 방법
	- AOP 시간 측정
- 기본적인 스크립트 공격 방어
	- SQL 인잭션 등

### URL 설계
| Method |      URL      |            Comment            |
| :----: | :-----------: | :---------------------------: |
|  GET   |       /       |       redirect:/boards        |
|  GET   | /users/signUp |           회원가입 페이지            |
|  POST  |    /users     | 회원 가입(redirect:/users/signIn) |
|  GET   | /users/signIn |            로그인 페이지            |
|  POST  |  /users/auth  |     로그인(redirect:/boards)     |
|  GET   |    /boards    |          게시글 목록 페이지           |
|  GET   |  /boards/new  |          게시글 등록 페이지           |
|  POST  |    /boards    |            게시글 등록             |
|  GET   | /boards/{id}  |          게시글 상세 페이지           |
|  POST  |    /replys    |             댓글 등록             |
|  POST  | /replys/{id}  |            대댓글 등록             |
