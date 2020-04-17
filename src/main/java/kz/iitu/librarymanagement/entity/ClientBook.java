package kz.iitu.librarymanagement.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class ClientBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date takeDate;
    private LocalDate bringDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    public LocalDate getBringDate() {
        return bringDate;
    }

    public void setBringDate(LocalDate bringDate) {
        this.bringDate = bringDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(Date takeDate) {
        this.takeDate = takeDate;
    }

    public Book getBook() {
        return book;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "ClientBook{" +
                "id=" + id +
                ", takeDate=" + takeDate +
                ", bringDate=" + bringDate +
                ", book=" + book +
                '}';
    }
}
