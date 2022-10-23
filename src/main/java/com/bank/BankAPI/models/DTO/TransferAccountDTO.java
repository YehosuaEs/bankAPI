package com.bank.BankAPI.models.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class TransferAccountDTO {
    @NotNull
    private Long senderAccountId;
    @NotNull
    private String transferAmount;
    @NotNull
    private Long receivingAccountId;
    @NotNull
    private String receivingFullName;

    public TransferAccountDTO(Long senderAccountId, String transferAmount, Long receivingAccountId, String receivingFullName) {
        this.senderAccountId = senderAccountId;
        this.transferAmount = transferAmount;
        this.receivingAccountId = receivingAccountId;
        this.receivingFullName = receivingFullName;
    }
}
