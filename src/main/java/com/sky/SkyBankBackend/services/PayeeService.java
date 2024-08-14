package com.sky.SkyBankBackend.services;

import com.sky.SkyBankBackend.DTO.PayeeDTO;

import java.util.List;

public interface PayeeService {
    PayeeDTO addPayee(PayeeDTO newPayee);

    public PayeeDTO getPayee(int id);

    public List<PayeeDTO> getAll();

    public List<PayeeDTO> getAllByEmail(String customerEmail);

    public PayeeDTO remove(Integer accountNumber, String customerEmail);
}
