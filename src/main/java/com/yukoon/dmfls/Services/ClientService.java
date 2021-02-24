package com.yukoon.dmfls.Services;

import com.yukoon.dmfls.Entities.Client;
import com.yukoon.dmfls.repositories.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;

    public List<Client> findAllByEmail(String email) {
        return clientRepo.findAllByEmail(email);
    }
    public List<Client> findAllByPhone(String phone) {
        return clientRepo.findAllByPhone(phone);
    }
    public List<Client> findAllByName(String name) {
        return clientRepo.findAllByName(name);
    }
    public List<Client> findAllByEmailAndPhone(String email,String phone) {
        return clientRepo.findAllByPhoneAndEmail(email,phone);
    }

    public Client findByEmailAndPhoneAndName(String email,String phone,String name) {
        List<Client> clients = clientRepo.findAllByEmailAndPhoneAndName(email,phone,name);
        if (clients.size() == 0) {
            return null;
        }
        return clients.get(0);
    }

    public Client save(Client client) {
        client = clientRepo.save(client);
        return client;
    }

}
