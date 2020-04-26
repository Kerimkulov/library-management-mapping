package kz.iitu.librarymanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String genre_name;

    @ManyToMany(mappedBy = "genreList", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Book> bookList;

}
