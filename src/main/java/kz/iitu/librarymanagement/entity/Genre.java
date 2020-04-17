package kz.iitu.librarymanagement.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String genre_name;

    @ManyToMany(mappedBy = "genreList", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Book> bookList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }

//    public List<Book> getBookList() {
//        return bookList;
//    }

//    public void setBookList(List<Book> bookList) {
//        this.bookList = bookList;
//    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genre_name='" + genre_name + '\'' +
//                ", bookList=" + bookList +
                '}';
    }
}
