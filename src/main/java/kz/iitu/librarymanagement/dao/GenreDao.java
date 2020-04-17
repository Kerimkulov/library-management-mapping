package kz.iitu.librarymanagement.dao;

import kz.iitu.librarymanagement.entity.Book;
import kz.iitu.librarymanagement.entity.Client;
import kz.iitu.librarymanagement.entity.Genre;
import kz.iitu.librarymanagement.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreDao {

    @Autowired
    private GenreRepository genreRepository;


    public List<Genre> genreList(){
        return genreRepository.findAll();
    }
    public Genre getById(Long id){
        if(genreRepository.existsById(id)) {
            return genreRepository.findById(id).get();
        }
        else {
            return null;
        }
    }
    public void newGenre(Genre genre){
        genreRepository.save(genre);
    }
    public void removeGenre(Genre genre){
        genreRepository.delete(genre);
    }
}
