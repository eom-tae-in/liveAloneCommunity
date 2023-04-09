package com.capstone.liveAloneCommunity.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class WriteCommentRequestDto {

    private long postId;

    @NotBlank(message = "댓글을 입력해주세요")
    private String content;
}
