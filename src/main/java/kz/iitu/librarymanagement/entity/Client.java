package kz.iitu.librarymanagement.entity;

import kz.iitu.librarymanagement.enums.UserType;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String phoneno;

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

    public Long getId() {
        return id;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNo() {
        return phoneno;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneno = phoneNo;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNo='" + phoneno + '\'' +
                ", userType=" + userType +
                ", clientBookList=" + clientBookList +
                '}';
    }
}
