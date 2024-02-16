## Calla

> 해당 프로젝트는 spring으로 구현한 쇼핑몰사이트 입니다.
> <br>
> 쇼핑몰 + 중고거래 + 커뮤니티를 결합한 사이트를 구현하였습니다.

## 개발환경
  - OS : windows 10 64 bit
  - JAVA : Java JDK 11.0.2
  - DBMS : Oracle 11g
  - DB Client Tool : Oracle SQL Developer
  - WAS : Apache Tomcat 9.0v
  - IDE : Eclipse
  - Spring : STS 3.9.18
  - Build : Maven 3.8.1

## 개발환경 세팅
<details>
<summary>JAVA</summary>
1. <a href="https://jdk.java.net/archive/">JDK 11.0.2</a> 설치 및 압축 풀기<br/>
2. 환경 변수 설정 [내 pc] -> [속성] -> [고급 시스템 설정] -> [고급] -> [환경 변수] 탭 <br/> 
&nbsp;&nbsp;[사용자 변수] -> [새로 만들기] (변수 명: JAVA_HOME / 변수 값 : jdk-11.0.2 폴더가 존재하는 경로 ex) C:\Program Files\jdk-11.0.2) <br/>
&nbsp;&nbsp;[시스템 변수] -> [Path] -> [편집] -> [새로 만들기] -> [%JAVA_HOME%\bin\] -> [확인] <br/>
  * 설정확인 : [cmd](관리자 권한) -> java -version
</details>

<details>
<summary>DB</summary>
1. <a href="https://www.oracle.com/database/technologies/xe-prior-release-downloads.html">Oracle Database 11g Express Edition Release 11.2.0.2.0</a> 설치 및 exe 파일 실행 <br/>
2. 윈도우 시작창에서 Run SQL Command Line 선택 <br/>
&nbsp;&nbsp;● conn /as sysdba로 접속 <br/>
&nbsp;&nbsp;● CREATE scott IDENTIFIED BY tiger <br/>
&nbsp;&nbsp;● GRANT CONNECT, DBA, RESOURCE TO scott <br/>
3. <a href="https://www.oracle.com/tools/downloads/sqldev-downloads.html">SQL developer Windows 32-bit/64-bit</a> 설치 (자바 8이상 설치되어 있는 경우) 및 실행 <br/>
4. 호스트, 포트, SID를 다음과 같이 입력하고 접속 클릭 <br/>
   <img loading="lazy" src="https://github.com/superDDangKong/Calla/assets/140034486/61b774d8-123b-45fa-a35d-0d25efdd2645">
</details>

<details>
<summary>WAS</summary>
1. <a href="https://tomcat.apache.org/download-90.cgi">Apache Tomcat 9.0</a> 설치
</details>

<details>
<summary>eclipse</summary>
1. <a href="https://www.eclipse.org/downloads/">Eclipse IDE for Enterprise Java and Web Developers</a> 설치 및 압축 해제 <br/>
2. eclipse.isi 파일 열기 <br/>
&nbsp;&nbsp;● -vm [설치된 jdk 경로]\bin 변경<br/>
&nbsp;&nbsp;● -Dosgi.requiredJavaVersion = 11 변경<br/>
</details>

<details>
<summary>STS</summary>
1. <a href="https://github.com/spring-attic/toolsuite-distribution/wiki/Spring-Tool-Suite-3">STS 3.9.18 full distribution on Eclipse 4.21.zip</a> 설치 및 압축 해제 <br/>
2. sts.isi 파일 열기 <br/>
&nbsp;&nbsp;● -vm [설치된 jdk 경로]/bin/javaw.exe 변경<br/>
&nbsp;&nbsp;● -Dosgi.requiredJavaVersion = 11 변경<br/>
</details>

<details>
<summary>maven</summary>
1.<a href="https://maven.apache.org/download.cgi"> apache-maven-x.x.x.bin.zip</a> 설치 및 압축 해제 <br/>
2. apache-maven-x.x.x 폴더를 C:\Study 폴더로 복사(폴더 생성) <br/>
3. 환경 변수 설정 [내 pc] -> [속성] -> [고급 시스템 설정] -> [고급] -> [환경 변수] -> [시스템 변수] -> [path] -> <br/>
  ‘C:\Study\apache-maven-x.x.x\bin 경로 추가 <br/>
4. 오라클 설치된 경로 확인 ex) C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib\ojdbc6.jar <br/>
5. 아래 코드를 cmd 화면에서 실행하여 Oracle JDBC driver를 로컬 메이븐 저장소에 추가 <br/>
  
```
mvn install:install-file
-Dfile=C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib\ojdbc6.jar
-DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0
-Dpackaging=jar 
```
BUILD SUCCESS가 뜨면 설치 성공
</details>

<details>
<summary>Spring project 설정</summary>
1. Project → Properties → Project Facets → Dynamic Web Module Version 4.0 & Java Version 11로 변경 <br/>
2. Project → Properties → Server → 서버 선택 <br/>
&nbsp;&nbsp;Project → Properties → Targeted Runtimes → Apache Tomcat v9.0 선택<br/>
3. xml 설정 (현 프로젝트 코드 참고) <br/>
4. (Maven Update 수행)프로젝트 마우스 오른쪽 클릭 -> Maven -> Update Project <br/>

</details>



## 사용 기술

| 분류                 | Badge                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| -------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **Front - end**      | <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&amp;logo=html5&amp;logoColor=white"> <img src="https://img.shields.io/badge/css3-1572B6?style=flat-square&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=flat-square&logo=javascript&logoColor=white"> <img src="https://img.shields.io/badge/jQuery-0769AD?style=flat-square&amp;logo=jQuery&amp;logoColor=white"> <img src="https://img.shields.io/badge/bootstrap-7952B3?style=flat-square&logo=bootstrap&logoColor=white"> |
| **Back - end**       | <img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&amp;logo=Spring&amp;logoColor=white">                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| **Version Control**  | <img src="https://img.shields.io/badge/git-F05032?style=flat-square&logo=git&logoColor=white">                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| **DB**               | <img src="https://img.shields.io/badge/ORACLE-F80000?style=flat-square&logo=oracle&logoColor=white">                                                                                                                                                                                                                                                                                                                                                                                                                                             |

## ERD - Diagram

![calla DB 모델링](https://github.com/superDDangKong/Calla/assets/140034486/f616b63f-72bd-493e-adb0-2a521e5c6aab)

## 제작인원 및 기간

- **총 제작인원:** <a href="https://github.com/superDDangKong">박진성</a>, <a href="https://github.com/cocobono1">강정묵</a> <a href="https://github.com/prodo813">김홍석</a> <a href="https://github.com/DongGun01">이동건</a> | 해당 링크를 누르면 깃허브 페이지로 이동합니다.
- **제작 기간:** 2023/10/20 ~ 2023/12/15
