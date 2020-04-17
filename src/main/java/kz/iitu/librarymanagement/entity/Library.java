package kz.iitu.librarymanagement.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String library_name;
    private String address;

    @OneToMany(mappedBy = "library", fetch = FetchType.LAZY)
    private List<Book> bookList;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibrary_name() {
        return library_name;
    }

    public void setLibrary_name(String library_name) {
        this.library_name = library_name;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void bringBook(Book book){
        if(bookList.contains(book)){
            for (Book b: bookList){
                b.increaseBookQuantity();
            }
        }
    }

    public void takeBook(Book book){
        if(bookList.contains(book)){
            for (Book b: bookList){
                b.decreaseBookQuantity();
            }
        }
        else {
            System.out.println("There is no such book");
        }
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", library_name='" + library_name + '\'' +
                ", address='" + address + '\'' +
                ", bookList=" + bookList +
                '}';
    }
}
