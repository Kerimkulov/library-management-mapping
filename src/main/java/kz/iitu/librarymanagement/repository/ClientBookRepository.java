package kz.iitu.librarymanagement.repository;

import kz.iitu.librarymanagement.entity.ClientBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface ClientBookRepository extends JpaRepository<ClientBook, Long> {
}
