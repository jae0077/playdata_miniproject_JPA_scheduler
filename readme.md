## 소개

일정을 등록하여 참여자와 공유 하는 일정 관리 프로그램

## 기여자

- [이상민](https://github.com/minstitia)
- [조하운](https://github.com/henrynoowah)
- [차재훈](https://github.com/jae0077)

## 1. 기능정의

- 기능
    1. 일정
        1. 일정 등록
            1. 카테고리(회의, 휴일 등)
            2. 일정 진행 기간
            3. 일정이름
            4. 일정설명
            5. 참여자
            6. 작성자
            7. 작성일자
        2. 일정 수정
        3. 일정 삭제
        4. 일정 조회
            1. 전체조회
            2. 특정 조건 조회
                1. 카테고리로 조회 
                2. 일정 시작일로 조회
                3. 일정 제목으로 조회
                4. 참여자로 조회
    2. 회원
        1. 회원가입
        2. 로그인

## 2. Modeling

![modeling](https://user-images.githubusercontent.com/44844956/130908288-c580ba9e-6e34-4487-802d-4a3fdf0866fd.png)

기획단계

![model_diagram](https://user-images.githubusercontent.com/44844956/130908354-5d212d47-64a8-4444-95ac-c711a2f98e35.png)

최종

## 3. Class Diagram

![ClassDiagram](https://user-images.githubusercontent.com/44844956/130908404-5e234911-adad-4668-a5c8-6a49c53a334c.png)

## 4. 아쉬운점

- Scheduler Table의 author 컬럼을 Member Table과 FK 설정을 했어야 하나 설계 미스로 너무 늦게 발견하여 처리하지 못함
- Date 타입 사용이 미숙하여 일정 등록시 시간단위 등록을 해결하지 못함
