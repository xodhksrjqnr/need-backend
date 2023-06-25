# need-backend

## 역할

해당 프로젝트는 Need(지원 정책 조회 웹 서비스)의 Backend-Server를 구현하였으며, API를 호출한 클라이언트에게 지원 정책 관련 정보를 가공하여 전달하는 역할을 수행한다.

## 개발 환경

- Spring Boot 3.0.5
- Java 17
- Spring Data JPA
- Github Actions
- Docker
- AWS EC2

## 프로젝트 소개

- Post 도메인 구현 (JPA를 이용한 Post 필터 조회 기능)
- Condition 도메인 구현 (JPA를 이용한 Benefit, Job 이름 조회 기능)
- CORS 설정
- Post에 대한 필터 조회와 Benefit, Job의 이름 조회 API 설계
- AWS EC2를 이용한 클라우드 서비스 운영
- Docker, Github Actions를 이용한 빌드 및 배포 자동화
- Github Actions, Slack을 이용한 빌드 및 배포 완료 알림
- DB 서버의 MySQL에 대한 외부 접속

## REST API

### Post

|URL|설명|
|---|---|
|GET /posts?sort={}&offset={}&administrativeDistrict={}&sex={}&benefit={}&job={}&search={}|게시물 필터 조회|

### Condition

|URL|설명|
|---|---|
|GET /conditions|검색 조건 조회|
