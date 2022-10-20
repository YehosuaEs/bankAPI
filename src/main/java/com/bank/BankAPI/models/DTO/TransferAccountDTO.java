package com.bank.BankAPI.models.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransferAccountDTO {
    private Long senderAccountId;
    private String transferAmount;
    private Long receivingAccountId;
    private String receivingFullName;

    public TransferAccountDTO(Long senderAccountId, String transferAmount, Long receivingAccountId, String receivingFullName) {
        this.senderAccountId = senderAccountId;
        this.transferAmount = transferAmount;
        this.receivingAccountId = receivingAccountId;
        this.receivingFullName = receivingFullName;
    }
}
