package com.bank.BankAPI.models.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ThirdPartyDTO {

    private String userName;
    private String hashedKey;

    public ThirdPartyDTO(String userName, String hashedKey) {
        this.userName = userName;
        this.hashedKey = hashedKey;
    }


}

