[1]. Overview
- Project name : 隣の先生(이웃집 선생님)
- Number of developers : 4
- Development period : 22.02.03 ~ 22.03.02
- Brief introduction : 가까운 이웃과 자유롭게 배우고 가르치며 활발한 자기 계발을 위한 웹페이지
- Development Language: Java 1.8
- Development environment : STS
- Database : Oracle

[2]. 요구사항 분석(My part)

1. 회원가입
- 비동기식 중복검사 / 비밀번호 암호화 저장
 · DataBase에 존재하는 이메일(ID)을 입력 시 자동으로 "사용 불가능한 이메일 주소입니다" 출력 및 커서 이동

 · DataBase에 존재하는 닉네임을 입력 시 자동으로 "사용 불가능한 닉네임입니다" 출력 및 커서 이동

 · 비밀번호 정보와 비밀번호 확인 정보 미일치 시 "비밀번호가 일치하지 않습니다" 내용의 모달창 출력 및 커서 이동

 · 비밀번호 저장 시 Database에 암호화 저장

 · 정보 미기입 시 해당 정보 입력 필요하다는 내용의 모달창 출력 및 커서 이동

- 주소 입력
 · 실제 주소 검색하여 행정구역 단위로 정보를 자동으로 나누어 DB에 저장(KakaoTalk API 활용)

1.1 로그인
- 비로그인 시 이용 가능한 Page
 · 회원가입 / 로그인 / 강사 검색 및 정보 / 강사 세부 정보

 · 외 다른 페이지 이용 시 로그인 화면으로 이동

1.2 회원정보 수정 Page
- 정보수정
 · 닉네임, 비밀번호, 전화번호, 주소 수정만 가능

 · 외 타 기능은 회원가입 Page와 동일
2. My page : 1. 수강신청
- 'My page - 수강 희망 강사 목록'에서 수강신청
 · 여러 강사 중 일부만 체크하여 수강신청 가능 / 한 명도 체크하지 않을 시 "희망강사 선택하여 주십시오" 모달창 출력

 · 수강 희망 개월 수를 기입한 후 체크하면 자동으로 개인 수업료 및 선택 인원, 총 수업료 계산 / 체크 해제 시 초기화

 · 체크하여 결제 시 QR 코드 출력 / 생년월일 및 번호 입력 후 카카오 페이 결제 진행(KakaoTalk API 활용)

 · 결제 완료 시 'My page - 나의 선생님'에서 수강 내역 확인 가능
2. My page : 2. 강사등록
- 'My page - 강사등록'에서 강사등록
 · 강사 활동 기간 내 이용비 계산 / 월 단위 / 결제 방식 : 카카오 페이 결제 진행

 · 이용비 결제 완료 시 강사 정보 입력 및 등록 요청

 · 강사 등록 완료 시 본인의 활동 기간 및 본인을 수강한 학생 조회 가능
