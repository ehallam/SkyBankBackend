package com.sky.SkyBankBackend.services;


import com.sky.SkyBankBackend.DTO.PayeeDTO;
import com.sky.SkyBankBackend.entities.Payee;
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
    public PayeeDTO remove(int id) {
        Payee found = this.repo.findById(id).orElseThrow(PayeeNotFoundException::new);
        this.repo.deleteById(id);
        return new PayeeDTO(found);
    }
}
