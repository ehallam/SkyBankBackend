package com.sky.SkyBankBackend.services;


import com.sky.SkyBankBackend.DTO.CustomerDTO;
import com.sky.SkyBankBackend.DTO.PayeeDTO;
import com.sky.SkyBankBackend.entities.Customer;
import com.sky.SkyBankBackend.entities.Payee;
import com.sky.SkyBankBackend.exceptions.CustomerNotFoundException;
import com.sky.SkyBankBackend.exceptions.PayeeNotFoundException;
import com.sky.SkyBankBackend.repositories.PayeeRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary // tells spring to use this one
public class PayeeServiceDB implements PayeeService {
    private final PayeeRepo repo;

    public PayeeServiceDB(PayeeRepo repo) {
        this.repo = repo;
    }

    @Override
    public PayeeDTO addPayee(PayeeDTO newPayee) {
        Payee toSave = new Payee(newPayee);
        Payee created = this.repo.save(toSave);
        return new PayeeDTO(created);
    }

    @Override
    public PayeeDTO getPayee(int id) {
        Payee found = this.repo.findById(id).orElseThrow(PayeeNotFoundException::new);
        return new PayeeDTO(found);
    }

    @Override
    public List<PayeeDTO> getAll() {
        return this.repo.findAll().stream().map(PayeeDTO::new).toList();
    }

    @Override
    public List<PayeeDTO> getAllByEmail(String customerEmail) {
        List<Payee> payees = this.repo.findAllByCustomerEmailIgnoreCase(customerEmail).orElseThrow(PayeeNotFoundException::new);
        return payees.stream().map(PayeeDTO::new).toList();
    }

    @Override
    public PayeeDTO remove(Integer accountNumber, String email) {
        Payee found = this.repo.findByAccountNumberAndCustomerEmail(accountNumber, email).orElseThrow(PayeeNotFoundException::new);
        this.repo.deleteById(found.getId());
        return new PayeeDTO(found);
    }
}
