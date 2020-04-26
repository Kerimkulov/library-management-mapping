package kz.iitu.librarymanagement.service.impl;

import kz.iitu.librarymanagement.entity.Client;
import kz.iitu.librarymanagement.enums.UserType;
import kz.iitu.librarymanagement.repository.ClientRepository;
import kz.iitu.librarymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    @Override
    public void creatClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void updateClient(Long id, Client client) {
        Client clientDb = clientRepository.findById(id).orElse(null);

        if(clientDb != null){

            clientRepository.save(clientDb);
        }
    }

    @Override
    public void deleteClient(Long id){
        clientRepository.delete(clientRepository.findById(id).get());

    }

    @Override
    public Client findClientById(Long id) {
        return clientRepository.findById(id).get();
    }

    @Override
    public void updateClientType(UserType userType, Long id) {
        Client client = clientRepository.findById(id).get();
        clientRepository.save(client);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Client client =  clientRepository.findByUsername(s);
        if (client == null){
            throw new UsernameNotFoundException("Client " + client + " not found");
        }
        return client;
    }
}
