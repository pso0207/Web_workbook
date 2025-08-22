package com.zerock.b01.repository;

import com.zerock.b01.domain.Board;
import com.zerock.b01.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

    @Query(value = "select now()", nativeQuery = true) // 오타 수정: noew() → now()
    String getTime();
}
