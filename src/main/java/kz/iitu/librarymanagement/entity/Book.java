package kz.iitu.librarymanagement.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String book_title;
    private String book_author;
    private String published_year;
    private int book_quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id")
    private Library library;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "book_genres",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "id")}
    )
    private List<Genre> genreList;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<ClientBook> clientBookList;

    public void increaseBookQuantity(){
        this.setBook_quantity(this.getBook_quantity()+1);
    }
    public void decreaseBookQuantity(){
        this.setBook_quantity(this.getBook_quantity()-1);
    }

}
