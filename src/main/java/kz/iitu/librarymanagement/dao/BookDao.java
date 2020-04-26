package kz.iitu.librarymanagement.dao;

import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/api//books")
public class BookDao {

    @Autowired
    private BookRepository bookRepository;

    @ApiOperation(value = "LIst of all books")
    @GetMapping("")
    public List<Book> bookList(){
        return bookRepository.findAll();
    }

    @ApiOperation(value = "LIst of all available books")
    @GetMapping("/availableBooks")
    public List<Book> availableBookList()
    {
        return bookRepository.availableBookList();
    }

    @ApiOperation(value = "Get book by ID")
    @GetMapping("/{id}")
    public Book getById(@PathVariable("id") Long id){
        return bookRepository.findById(id).get();
    }

    @ApiOperation(value = "Add new book")
    @PostMapping("")
    public Book newBook(@RequestBody Book book){
        bookRepository.save(book);
        return book;
    }

    @ApiOperation(value = "Update book by ID")
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable("id") Long id,
                           @RequestBody Book book){

        bookRepository.save(book);
        return book;
    }

    @ApiOperation(value = "Delete book by ID")
    @DeleteMapping("/{id}")
    public Book removeBook(@PathVariable("id") Long id){
        Book book = this.getById(id);
        bookRepository.delete(book);
        return book;
    }

}
