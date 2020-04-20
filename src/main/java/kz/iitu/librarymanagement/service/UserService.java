package kz.iitu.librarymanagement.service;

import kz.iitu.librarymanagement.entity.Client;
import kz.iitu.librarymanagement.enums.UserType;

import java.util.List;

public interface UserService {

    List<Client> getAllClients();
    void creatClient(Client client);
    void updateClient(Long id, Client client);
    void deleteClient(Long id);
    Client findClientById(Long id);
    void updateClientType(UserType userType, Long id);
}
