package com.yukoon.dmfls.repositories;

import com.yukoon.dmfls.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepo extends JpaRepository<Client,Integer> {
    @Query()
    public List<Client> findAllByEmail(String email);

    @Query()
    public List<Client> findAllByPhone(String phone);

    @Query()
    public List<Client> findAllByPhoneAndEmail(String email,String phone);

    @Query()
    public List<Client> findAllByName(String name);

    @Query()
    public List<Client> findAllByEmailAndPhoneAndName(String email,String phone,String name);
}
