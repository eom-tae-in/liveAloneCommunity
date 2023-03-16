package com.capstone.liveAloneComunity.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    @Column(name = "PASSWORD")
    private String password;

    public String getPassword() {
        return password;
    }

    public boolean equals(String password){
        return this.password.equals(password);
    }
}
