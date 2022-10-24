package com.bank.BankAPI.models.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ThirdPartyDTO {

    // ---- to create the ThirdParty
    private String userName;
    private String hashedKey;

    // -----To make Transfer
    private Long senderThirdPartyId;
    private Long receivingAccountId;
    private String transferAmount;
    private String secretKey;

    //datl


    public ThirdPartyDTO(String userName, String hashedKey, Long receivingAccountId, String transferAmount, String secretKey) {
        this.userName = userName;
        this.hashedKey = hashedKey;
        this.receivingAccountId = receivingAccountId;
        this.transferAmount = transferAmount;
        this.secretKey = secretKey;
    }

    public ThirdPartyDTO(String userName, String hashedKey) {
        this.userName = userName;
        this.hashedKey = hashedKey;
    }
}

