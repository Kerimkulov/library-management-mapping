package kz.iitu.librarymanagement.dao;

import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/api/clients")
public class ClientDao {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Hello message")
    @GetMapping("/hello")
    public String hello(){
        return "Hello world!";
    }

    @ApiOperation(value = "LIst of all clients")
    @GetMapping("")
    public List<Client> clientList(){
        return userService.getAllClients();
    }

    @ApiOperation(value = "Get client by ID")
    @GetMapping("/{id}")
    public Client getById(@PathVariable("id") Long id)
    {
        return userService.findClientById(id);
    }

    @ApiOperation(value = "Client registration")
    @PostMapping("/signUp")
    public void newClient(@RequestBody Client client){
        System.out.println("user = " + client);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication.getName() = " + authentication.getName());
        userService.creatClient(client);
        System.out.println("Client was added");
    }


    @ApiOperation(value = "Update client by ID")
    @PatchMapping("/patch/{id}")
    public void updateClientType(@PathVariable("id") Long id,
                                   @RequestParam UserType userType){
        userService.updateClientType(userType,id);
    }

    @ApiOperation(value = "Delete client by ID")
    @DeleteMapping("/{id}")
    public void removeClient(@PathVariable Long id){
        userService.deleteClient(id);
    }
}
