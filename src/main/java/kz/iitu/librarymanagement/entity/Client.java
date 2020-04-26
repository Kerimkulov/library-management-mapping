package kz.iitu.librarymanagement.entity;

import kz.iitu.librarymanagement.enums.UserType;
import lombok.Data;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Client implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String name;
    private String surname;
    @Column(unique = true)
    private String phoneno;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<ClientBook> clientBookList;

    public List<ClientBook> getClientBookList() {
        return clientBookList;
    }

    public void setClientBookList(List<ClientBook> clientBookList) {
        this.clientBookList = clientBookList;
    }

    public void bringBook(Book book){
        this.getClientBookList().remove(book);
        setClientBookList(this.getClientBookList());
    }

}
