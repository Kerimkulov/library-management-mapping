package kz.iitu.librarymanagement.dao;

import kz.iitu.librarymanagement.entity.Book;
import kz.iitu.librarymanagement.entity.Client;
import kz.iitu.librarymanagement.entity.ClientBook;
import kz.iitu.librarymanagement.enums.UserType;
import kz.iitu.librarymanagement.repository.ClientRepository;
import kz.iitu.librarymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientDao {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello world!";
    }

    @GetMapping("")
    public List<Client> clientList(){
        return userService.getAllClients();
    }


    @GetMapping("/{id}")
    public Client getById(@PathVariable("id") Long id)
    {
        return userService.findClientById(id);
    }

    @PostMapping("/signUp")
    public void newClient(@RequestBody Client client){
        System.out.println("user = " + client);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication.getName() = " + authentication.getName());
        userService.creatClient(client);
        System.out.println("Client was added");
    }



    @PatchMapping("/{id}")
    public void updateClientType(@PathVariable("id") Long id,
                                   @RequestParam UserType userType){
        userService.updateClientType(userType,id);
    }

    @DeleteMapping("/{id}")
    public void removeClient(@PathVariable Long id){
        userService.deleteClient(id);
    }
}
