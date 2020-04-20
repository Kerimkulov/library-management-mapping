package kz.iitu.librarymanagement.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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


    public int getBook_quantity() {
        return book_quantity;
    }

    public void setBook_quantity(int book_quantity) {
        this.book_quantity = book_quantity;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getPublished_year() {
        return published_year;
    }

    public void setPublished_year(String published_year) {
        this.published_year = published_year;
    }



    public void increaseBookQuantity(){
        this.setBook_quantity(this.getBook_quantity()+1);
    }
    public void decreaseBookQuantity(){
        this.setBook_quantity(this.getBook_quantity()-1);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", book_title='" + book_title + '\'' +
                ", book_author='" + book_author + '\'' +
                ", published_year=" + published_year +
                ", book_quantity=" + book_quantity +
                ", genreList=" + genreList +
                '}';
    }
}
