package com.zerock.b01.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.zerock.b01.domain.Board;
import com.zerock.b01.domain.QBoard;
import com.zerock.b01.domain.QReply;
import com.zerock.b01.dto.BoardListReplyCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);
        query.where(board.title.contains("1"));

        getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();
        long count = query.fetch().size(); // Querydsl 5: fetchCount 제거 → 대안

        return new PageImpl<>(list, pageable, count);
    }

    /*
    게시글(Board) 목록을 가져오는데, 각 게시글마다 댓글 수(replyCount)까지 같이 묶어서 DTO로 반환하는 기능이에요.
     즉, “게시글 + 댓글 개수” 리스트를 페이징(Page) 처리해서 가져오는 것.
     */

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if ((types != null && types.length > 0) && keyword != null && !keyword.isEmpty()) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        query.where(board.bno.gt(0L));

        getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();
        long count = query.fetch().size(); // 간단 대안

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        JPQLQuery<Board> query = from(board);
        query.leftJoin(reply).on(reply.board.eq(board));
        // 레프트 조인 : 왼쪽 테이블은 무조건 다 나오고, 오른쪽은 조건에 맞는 것만 보여줌
        query.groupBy(board);

        if( (types != null && types.length > 0) && keyword != null ){

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types){

                switch (type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }//end for
            query.where(booleanBuilder);
        }

        //bno > 0
        query.where(board.bno.gt(0L));

        JPQLQuery<BoardListReplyCountDTO> dtoQuery = query.select(Projections.bean(BoardListReplyCountDTO.class,
                board.bno,
                board.title,
                board.writer,
                board.regDate,
                reply.count().as("replyCount")
        )); // 프로젝션 : JPQL의 결과를 바로 DTO로 처리하는 기능을 제공함.

        this.getQuerydsl().applyPagination(pageable,dtoQuery);

        List<BoardListReplyCountDTO> dtoList = dtoQuery.fetch();

        long count = dtoQuery.fetchCount();

        return new PageImpl<>(dtoList, pageable, count);
    }
}
