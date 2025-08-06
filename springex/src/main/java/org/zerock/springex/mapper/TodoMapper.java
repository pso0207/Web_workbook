package org.zerock.springex.mapper;

import org.zerock.springex.domain.TodoVO;

import java.util.List;

public interface TodoMapper {

    String getTime(); // DB 연결됐는지 확인하는 코드

    void insert(TodoVO todoVO);

    List<TodoVO> selectAll();
}
