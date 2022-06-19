# FileExtensionTask
파일 확장자 과제

## 개발 환경
- Java 8
- Maven 3.8.4
- Spring Boot 2.7.0
  - Spring Web
  - Spring JPA
- H2 DB
- Lombok
- Swagger 2.9.2
- Mapstruct 1.4.2
- Bootstrap 5.0.2
- Fontawesome 5.8.2

## 로컬 실행 방법
1. 프로젝트 임포트 후 maven clean 및 compile
2. 프로젝트 하위의 target/generated-sources/annotations 폴더를 build path의 source 폴더로 추가
3. edu.jong.server 패키지 상의 FileExtTaskApplication.java 실행

## 로컬 접속 URL
- Swagger : http://localhost:8000/swagger-ui.html
- 화면 소스 : http://localhost:8000/static/index.html

## 서버 접속 URL
- Swagger : http://ec2-44-202-85-4.compute-1.amazonaws.com:8000/swagger-ui.html
- 화면 소스 : http://ec2-44-202-85-4.compute-1.amazonaws.com
