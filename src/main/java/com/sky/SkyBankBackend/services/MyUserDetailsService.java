package com.sky.SkyBankBackend.services;

import com.sky.SkyBankBackend.entities.Customer;
import com.sky.SkyBankBackend.repositories.CustomerRepo;
import com.sky.SkyBankBackend.domain.MyUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepo repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer found = this.repo.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user exists with name: " + email));
        return new MyUserDetails(found);

    }

}

