package com.zerock.b01.repository;
//원래라면, 데이터베이스 테이블을 다루려면 이런 게 필요함
// 1. DAO 클래스 만들기
// 2. CRUD 메서드 직접 구현 (insert(), update(), delete(), selectById() 등)
// 3. 페이징 쿼리 작성 (LIMIT, OFFSET 직접 코딩)
//JpaRepository = CRUD + 페이징/정렬 기능을 기본 제공하는 인터페이스. 그래서 인터페이스 선언만으로 해결됨


import com.zerock.b01.domain.Board;
import com.zerock.b01.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch { // 엔티티 타입 : Board, @ID타입 : Long

    @Query(value = "select noew()", nativeQuery = true)
    String getTime();

}
