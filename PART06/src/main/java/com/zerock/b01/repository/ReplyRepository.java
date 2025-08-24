package com.zerock.b01.repository;

import com.zerock.b01.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select r from Reply r where r.board.bno = :bno") // :파마리터는 바인딩 변수, 실행시에 실제 값으로 치환됨
    Page<Reply> listOfBoard(@Param("bno") Long bno, Pageable pageable); // @Param 자바는 파라미터 이름을 컴파일 후 까먹기 때문에
    //@Param을 통해 쿼리 안의 '이름'과 '메서드 인자'를 연결해줌
}
