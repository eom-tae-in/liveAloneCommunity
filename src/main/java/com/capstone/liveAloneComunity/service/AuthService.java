package com.capstone.liveAloneComunity.service;
import com.capstone.liveAloneComunity.config.jwt.TokenProvider;
import com.capstone.liveAloneComunity.dto.auth.*;
import com.capstone.liveAloneComunity.dto.token.*;
import com.capstone.liveAloneComunity.entity.Member;
import com.capstone.liveAloneComunity.entity.RefreshToken;
import com.capstone.liveAloneComunity.repository.MemberRepository;
import com.capstone.liveAloneComunity.repository.RefreshTokenRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RefreshTokenRepository refreshTokenRepository;
    private MemberValidator memberValidator;

    @PostConstruct
    private void initiateValidator(){
        memberValidator = new MemberValidator(memberRepository, passwordEncoder);
    }

    public void register(RegisterRequestDto registerRequestDto){
        memberValidator.validateRegister(registerRequestDto);
        memberRepository.save(new Member(registerRequestDto, passwordEncoder));
    }

    public TokenResponseDto logIn(LogInRequestDto logInRequestDto){
        memberValidator.validateLogIn(logInRequestDto);
        return createTokenDtoByAuthentication(getAuthenticationToLogIn(logInRequestDto));
    }

    private Authentication getAuthenticationToLogIn(LogInRequestDto logInRequestDto){
        UsernamePasswordAuthenticationToken authenticationToken = logInRequestDto.toAuthentication();
        return authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    }

    private TokenResponseDto createTokenDtoByAuthentication(Authentication authentication){
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();
        refreshTokenRepository.save(refreshToken);
        return new TokenResponseDto(tokenDto);
    }
}
