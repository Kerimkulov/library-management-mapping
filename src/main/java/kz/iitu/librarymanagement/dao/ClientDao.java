package kz.iitu.librarymanagement.dao;

import kz.iitu.librarymanagement.entity.Book;
import kz.iitu.librarymanagement.entity.Client;
import kz.iitu.librarymanagement.entity.ClientBook;
import kz.iitu.librarymanagement.enums.UserType;
import kz.iitu.librarymanagement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/users")
public class ClientDao {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("")
    public List<Client> clientList(){
        return clientRepository.findAll();
    }
    @GetMapping("/{id}")
    public Client getById(@PathVariable("id") Long id)
    {
        return clientRepository.findById(id).get();
    }
    @PostMapping("")
    public void newClient(@RequestBody Client client){
        clientRepository.save(client);
        System.out.println("Client was added");
    }

    @PatchMapping("/{id}")
    public Client updateClientType(@PathVariable("id") Long id,
                                   @RequestParam UserType userType){
        Client client = clientRepository.findById(id).get();
        client.setUserType(userType);
        return clientRepository.save(client);
    }

    @DeleteMapping("/{id}")
    public void removeClient(@PathVariable Long id){
        Client client = this.getById(id);
        clientRepository.delete(client);
    }
}
