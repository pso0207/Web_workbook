package org.zerock.springex.dto;


import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
    private Long tno;
    @NotEmpty  // 유효성 검사, 입력값이 올바른지 검사
    private String title;
    @Future
    private LocalDate dueDate;

    private boolean finished;
    @NotEmpty
    private String writer;
}
