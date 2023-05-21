package com.capstone.liveAloneCommunity.controller.message;

import com.capstone.liveAloneCommunity.dto.auth.LogInRequestDto;
import com.capstone.liveAloneCommunity.entity.member.Member;
import com.capstone.liveAloneCommunity.repository.member.MemberRepository;
import com.capstone.liveAloneCommunity.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ReadSingleMessageTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private AuthService authService;
    @Autowired
    private MemberRepository memberRepository;

    private String getAccessTokenAfterLogIn(String text){
        return authService.logIn(LogInRequestDto.builder().username(text).password(text).build()).getAccessToken();
    }

    private Member getMember(String nickname) {
        return memberRepository.findByNickname_Nickname(nickname).orElseThrow(MemberNotFoundException::new);
    }
}
