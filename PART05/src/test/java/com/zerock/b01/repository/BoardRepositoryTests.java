package com.zerock.b01.repository;


import com.zerock.b01.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;
// 수정 삭제시에 select 문이 먼저 실행되는 이유
// JPA를 이용하는 것은 엄밀하게 말하면 영속 컨텍스트와 데이터베이스를 동기화해서 관리한다는 의미
// 그래서 특정한 엔티티 객체가 추가되면 영속 컨텍스트에 추가하고, 데이터베이스와 동기화가 돼야함.
// 그리고 수정이나 삭제를 한다면 영속 컨텍스트에 해당하는 엔티티 객체가 존재해야만 하므로
// 먼저 select로 엔티티 객체를 영속 컨텍스트에 저장해서 이를 삭제후 delete가 이뤄짐
@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1,100).forEach(i -> {
            Board board = Board.builder()
                    .title("title..." + i)
                    .content("content..." + i)
                    .writer("user" + (i%10))
                    .build();

            Board result = boardRepository.save(board);
            log.info("BNO : " + result.getBno());
        });
    }

    @Test
    public void testSelect() {
        Long bno = 100L;

        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        log.info(board);
    }
    @Test
    public void testUpdate() {
        Long bno = 100L;
        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        board.change("update....title100", "update...content100");
        boardRepository.save(board);
    }
    @Test
    public void testDelete() {
        Long bno = 1L;

        boardRepository.deleteById(bno);
    }
    @Test
    public void testPaging(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.findAll(pageable);
    }
    @Test
    public void testSearch1(){

        Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());

        boardRepository.search1(pageable);
    }
    @Test
    public void testSearchAll() {

        String[] types = {"t", "c", "w"};

        String keyword = "1";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);
    }
    @Test
    public void testSearchAll2() {
        String[] types = {"t", "c", "w"};

        String keyword = "1";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        //total pages
        log.info(result.getTotalPages());

        //pag size
        log.info(result.getSize());

        //page number
        log.info(result.getNumber());

        //prev next
        log.info(result.hasPrevious() + ": " + result.hasNext());

        result.getContent().forEach(board -> log.info(board));
    }
}
