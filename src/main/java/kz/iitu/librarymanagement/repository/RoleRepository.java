package kz.iitu.librarymanagement.repository;


import kz.iitu.librarymanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
