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

## 구현 기능

- 지원 정책 게시물 조회
- 검색 조건에 따른 게시물 필터링
- 검색 조건 조회

## REST API

### Post

|URL|설명|
|---|---|
|GET /posts?sort={}&offset={}&administrativeDistrict={}&sex={}&benefit={}&job={}&search={}|게시물 필터 조회|

### Condition

|URL|설명|
|---|---|
|GET /conditions|검색 조건 조회|
