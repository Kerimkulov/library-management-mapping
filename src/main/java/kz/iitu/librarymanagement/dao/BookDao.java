package kz.iitu.librarymanagement.dao;

import kz.iitu.librarymanagement.entity.Book;
import kz.iitu.librarymanagement.entity.Genre;
import kz.iitu.librarymanagement.entity.Library;
import kz.iitu.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookDao {

    @Autowired
    private BookRepository bookRepository;
    @GetMapping("")
    public List<Book> bookList(){
        return bookRepository.findAll();
    }
    @GetMapping("/availableBooks")
    public List<Book> availableBookList()
    {
        return bookRepository.availableBookList();
    }
    @GetMapping("/{id}")
    public Book getById(@PathVariable("id") Long id){
        return bookRepository.findById(id).get();
    }
    @PostMapping("")
    public Book newBook(@RequestBody Book book){
        bookRepository.save(book);
        return book;
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable("id") Long id,
                           @RequestBody Book book){
        book.setId(id);
        bookRepository.save(book);
        return book;
    }
    @DeleteMapping("/{id}")
    public Book removeBook(@PathVariable("id") Long id){
        Book book = this.getById(id);
        bookRepository.delete(book);
        return book;
    }

}
