package com.capstone.liveAloneCommunity.dto.member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchMemberDto {

    private int page;
    private int size;
    private String text;
    private MemberSearchType memberSearchType;
}
