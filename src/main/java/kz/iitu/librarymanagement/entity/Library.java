package kz.iitu.librarymanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String library_name;
    private String address;

    @OneToMany(mappedBy = "library", fetch = FetchType.LAZY)
    private List<Book> bookList;


}
