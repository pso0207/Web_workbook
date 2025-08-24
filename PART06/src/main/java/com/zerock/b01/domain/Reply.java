package com.zerock.b01.domain;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "Reply", indexes = {
        @Index(name = "idx_reply_board_bno", columnList = "board_bno") // board_bno 컬럼 기준으로 인덱스를 만듦
})
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString //@ToString : 객체를 문자열로 바꿔서 보기 쉽게 보여줌, exclude는 불필요한 쿼리 방지
public class Reply extends BaseEntity { // BaseEntity 값들도 저절로 생성됨
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @ManyToOne(fetch = FetchType.LAZY) // 필요한 순간에만 쿼리를 날려서 가져옴
    private Board board;

    private String replyText;

    private String replyer;
}
