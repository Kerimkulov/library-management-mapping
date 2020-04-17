package kz.iitu.librarymanagement.dao;

import kz.iitu.librarymanagement.entity.Book;
import kz.iitu.librarymanagement.entity.Client;
import kz.iitu.librarymanagement.entity.ClientBook;
import kz.iitu.librarymanagement.event.BringBookDateEvent;
import kz.iitu.librarymanagement.repository.BookRepository;
import kz.iitu.librarymanagement.repository.ClientBookRepository;
import kz.iitu.librarymanagement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/clientBooks")
public class ClientBookDao implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private ClientBookRepository clientBookRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("")
    public List<ClientBook> clientBookList(){
        return clientBookRepository.findAll();
    }
    @GetMapping("/{id}")
    public ClientBook getById(@PathVariable("id") Long id){
        return clientBookRepository.findById(id).get();
    }
    @PostMapping("/{bookId}/{clientId}")
    public ClientBook takeBook(@PathVariable("bookId") Long bookId,
                         @PathVariable("clientId") Long clientId){
        Date date = new Date();
        LocalDate bringDate = LocalDate.now().plusMonths(3);
        Book book = this
                .bookRepository.findById(bookId).get();
        Client client = this.clientRepository.findById(clientId).get();
        if(book.getBook_quantity() == 0 ){
            System.out.println("Quantity = 0");
        }
        else {
            ClientBook clientBook = new ClientBook();
            clientBook.setBook(book);
            clientBook.setClient(client);
            clientBook.setTakeDate(date);
            clientBook.setBringDate(bringDate);
            book.decreaseBookQuantity();
            bookRepository.save(book);
            clientBookRepository.save(clientBook);
            this.eventPublisher.publishEvent(new BringBookDateEvent(this,clientBook));
            return clientBook;
        }
        return null;
    }
    @DeleteMapping("/{bookId}/{clientId}")
    public ClientBook bringBook(@PathVariable("bookId") Long bookId, @PathVariable("clientId") Long clientId){
        Book book = this
                .bookRepository.findById(bookId).get();
        for(Client client:clientRepository.findAll()) {
            if (client.getId() == clientId) {
                for (ClientBook clientBook:client.getClientBookList()) {
                    if (clientBook.getBook().getId() == bookId) {
                        client.bringBook(book);
                        book.increaseBookQuantity();
                        clientRepository.save(client);
                        bookRepository.save(book);
                        clientBookRepository.delete(clientBook);
                        return clientBook;
                    } else {
                        System.out.println("No book");
                    }
                }
            }
            else {
                System.out.println("No client");
            }
        }


    return null;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
