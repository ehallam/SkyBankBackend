package com.sky.SkyBankBackend.services;


import com.sky.SkyBankBackend.DTO.CustomerDTO;
import com.sky.SkyBankBackend.DTO.LoginRequestDTO;
import com.sky.SkyBankBackend.entities.Customer;
import com.sky.SkyBankBackend.exceptions.CustomerNotFoundException;
import com.sky.SkyBankBackend.repositories.CustomerRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
@Primary // tells spring to use this one
public class CustomerServiceDB implements CustomerService {
    private final CustomerRepo repo;

    public CustomerServiceDB(CustomerRepo repo) {
        this.repo = repo;
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO newCustomer) {
        String hash = sha256(newCustomer.getPassword());
        newCustomer.setPassword(hash);
        Customer toSave = new Customer(newCustomer);
        Customer created = this.repo.save(toSave);
        return new CustomerDTO(created);
    }

    @Override
    public List<CustomerDTO> getAll() {
        return this.repo.findAll().stream().map(CustomerDTO::new).toList();
    }

    @Override
    public CustomerDTO getCustomerByEmail(String email) {
        Customer found = this.repo.findById(email).orElseThrow(CustomerNotFoundException::new);
        return new CustomerDTO(found);
    }

    @Override
    public CustomerDTO remove(String email) {
        Customer found = this.repo.findById(email).orElseThrow(CustomerNotFoundException::new);
        this.repo.deleteById(email);
        return new CustomerDTO(found);
    }

    @Override
    public ResponseEntity<?> login(LoginRequestDTO newLoginRequest) {
        String email = newLoginRequest.getLoginEmail();
        String password = newLoginRequest.getLoginPassword();

        Optional<Customer> found = this.repo.findByEmailIgnoreCase(email);
        if(found.isPresent()) {
            Customer customer = found.get();
                if(customer.getPassword().equals(sha256(password))){
                    return new ResponseEntity<>("Success", HttpStatus.OK);
                }
                else{
                    return new ResponseEntity<>("Invalid Password", HttpStatus.UNAUTHORIZED);
                }

        }
        else{
            return new ResponseEntity<>("Email not found", HttpStatus.NOT_FOUND);
        }


    }
    private String sha256(String input) {
        try {
            // Get an instance of the SHA-256 message digest
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Apply SHA-256 algorithm to the input
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error initializing SHA-256 algorithm", e);
        }
    }
}
