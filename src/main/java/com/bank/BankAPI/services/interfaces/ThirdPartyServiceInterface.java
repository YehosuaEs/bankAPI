package com.bank.BankAPI.services.interfaces;

import com.bank.BankAPI.models.DTO.ThirdPartyDTO;

public interface ThirdPartyServiceInterface {

    void makeThirdPartyTransferToAccounts(String hashedKey, ThirdPartyDTO thirdPartyDTO);
}
