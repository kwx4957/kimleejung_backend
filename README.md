# 김이정배당지도
## 개요
현재 서비스 중인 배당 전문 웹이 없어 배당주 투자자들에게 배당정보를 시각화하여 제공하는 웹사이트

## 개발 환경
기간 : 2022.10 ~ 2022.11  
인원 : 프론트 3명, 백엔드 2명  
사용 기술 및 환경 : 
- React, Js
- SpringBoot, Java 11, Spring Security, Oauth2
- MariaDB
- AWS ElasticBeanstock

## 담당 업무
- 공공데이터 호출 후 DB저장
- AWS EB 구축
- Kakao, naver, google Oauth2 로그인 구현
- 기업 정보 API 구현

### 사용된 공공데이터
1. https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15094808
2. https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15043284
3. https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15059649

### 배당금 알고리즘
> 배당성장률 평균 * 최근 배당금 ==  3년 평균 배당성장률 * 최근 배당금  ==  
> { (2년전 배당금 - 3년 전 배당금) / 3년 전 배당금 + (1년전 배당금 - 2년 전 배당금) / 2년 전 배당금  / 2 } * 최근 배당금

### 레포지토리
Frontend: https://github.com/odnac/capstone  
Backend: https://github.com/kwx4957/kimleejung_backend

## 흐름도
![](https://user-images.githubusercontent.com/33277725/237926154-1c9afeae-5de9-4bca-96b7-4e3f30fa6fcb.png)

## 프로젝트 ERD
![](https://user-images.githubusercontent.com/33277725/237926135-cb342cb1-cf49-4364-b91d-abeda42646ec.png)

## 화면 설계
![](https://user-images.githubusercontent.com/33277725/237926021-7ddbf7c5-d064-4f30-94da-7c75a291e185.PNG)|![](https://user-images.githubusercontent.com/33277725/237926026-5b6d23d3-ce80-479d-976d-cd82c2bd08d5.PNG)|![](https://user-images.githubusercontent.com/33277725/237926043-168fb07f-d2ac-4553-a3e8-6499319352d9.PNG)|![](https://user-images.githubusercontent.com/33277725/237926033-d5cac010-d450-4fb7-9f04-3ce4fd250b48.PNG)
---|---|---|---|
