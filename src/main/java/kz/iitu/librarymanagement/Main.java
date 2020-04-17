//package kz.iitu.librarymanagement;
//
//import kz.iitu.librarymanagement.config.SpringConfiguration;
//import kz.iitu.librarymanagement.dao.*;
//import kz.iitu.librarymanagement.entity.*;
//import kz.iitu.librarymanagement.enums.UserType;
//import kz.iitu.librarymanagement.repository.*;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import javax.sound.midi.Soundbank;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Scanner;
//import java.util.stream.Stream;
//
//public class Main {
//    static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//    static ClientDao clientDao = context.getBean("clientDao", ClientDao.class);
//    static BookDao bookDao = context.getBean("bookDao", BookDao.class);
//    static ClientBookDao clientBookDao = context.getBean("clientBookDao", ClientBookDao.class);
//    static GenreDao genreDao = context.getBean("genreDao", GenreDao.class);
//    static LibraryDao libraryDao = context.getBean("libraryDao", LibraryDao.class);
//    static Scanner input = new Scanner(System.in);
//    public static void main(String[] args) {
//
//        int exit=0;
//        while (exit == 0){
//            System.out.println("1.Add Client");
//            System.out.println("2.Add Book");
//            System.out.println("4. TakeBook");
//            System.out.println("5. BringBook");
//            System.out.println("6. Clients");
//            System.out.println("7. Books");
//            System.out.println("8. Genres");
//            System.out.println("0. Exit");
//            int operation = input.nextInt();
//            switch (operation){
//                case 1:
//                    Client client = new Client();
//                    System.out.print("Name: ");
//                        String name = input.next();System.out.println("");
//                    System.out.print("Surname: ");
//                        String surname = input.next();System.out.println("");
//                    System.out.print("PhoneNo: ");
//                        String phoneNo = input.next();System.out.println("");
//                    System.out.print("Username/id: ");
//                        String username = input.next();System.out.println("");//Username is id like 23917
//                    System.out.println("1. Student");
//                    System.out.println("2. Ordinary");
//                    System.out.print("3. Premium");
//                    int userType = input.nextInt();
//                    if(userType == 1){
//                        client.setUserType(UserType.Student);
//                    }
//                    else if(userType == 2){
//                        client.setUserType(UserType.Ordinary);
//                    }
//                    else if(userType == 3){
//                        client.setUserType(UserType.Premium);
//                    }
//                    client.setName(name);
//                    client.setSurname(surname);
//                    client.setPhoneNo(phoneNo);
//                    client.setUsername(username);
//                    clientDao.newClient(client);
//                    break;
//                case 2:
//                    Book book = new Book();
//
//                    System.out.print("Book title: ");
//                    String book_title = input.next();System.out.println("");
//                    System.out.print("Book author: ");
//                    String book_author = input.next();System.out.println("");
//                    System.out.print("Book Quantity: ");
//                    int book_quantity = input.nextInt();System.out.println("");
//                    System.out.print("Published year: ");
//                    String published_year = input.next();System.out.println("");
//
//                    book.setBook_author(book_author);
//                    book.setBook_quantity(book_quantity);
//                    book.setBook_title(book_title);
//                    book.setPublished_year(published_year);
//
//                    List<Genre> genreList = new ArrayList<>();
//                    System.out.println(genreDao.genreList());
//                    int existFromGenre = 0;
//                    while (existFromGenre == 0 ){
//                        System.out.print("Choose genre of book by id OR exit-> 0: ");
//                        Long genreID = input.nextLong();
//                        System.out.println(genreID);
//                        if(genreID == 0){
//                            if (!genreList.isEmpty()){
//                                book.setGenreList(genreList);
//                                book.setLibrary(libraryDao.getById(1L));
//                                bookDao.newBook( book);
//                                existFromGenre = 1;
//                            }
//                            else {
//                                System.out.println("Can not add without Genre!");
//                            }
//                        }
//                        else{
//                            if(genreDao.getById(genreID) != null) {
//                                if(genreList.contains(genreDao.getById(genreID))){
//                                    System.out.println("Genre is already added");
//                                }
//                                else {
//                                    genreList = addGenreList(genreDao.getById(genreID), genreList);
//                                    System.out.println(genreList);
//                                }
//                            }
//                            else {
//                                System.out.println("Please choose exist Genre!");
//                            }
//                        }
//                    }
//                    break;
//                case 4:
//                    System.out.println("Choose id of CLIENT and id of BOOK");
//                    showClientList(clientDao.clientList());
//                    System.out.print("ClientID: ");
//                    Long clientId = input.nextLong();
//                    showBookList(bookDao.bookList());
//                    System.out.print("BookID: ");
//                        Long bookId = input.nextLong();
//
//
//                    clientBookDao.takeBook(bookDao.getById(bookId), clientDao.getById(clientId));
//                    break;
//                case 5:
//                    showClientList(clientDao.clientList());
//                    System.out.print("Choose client by id: ");
//                        Long clientBookId = input.nextLong();
//                    showClienBooktList(clientDao.getById(clientBookId).getClientBookList());
//                    System.out.print("Choose Client's book by id: ");
//                        Long broughtBook = input.nextLong();
//                        clientBookDao.bringBook(bookDao.getById(broughtBook), clientDao.getById(clientBookId));
//                    break;
//                case 6:
//                    showClientList(clientDao.clientList());
//                    break;
//                case 7:
//                    showBookList(bookDao.bookList());
//                    break;
//                case 8:
//                    showGenreList(genreDao.genreList());
//                    break;
//                case 0:
//                    exit = 1;
//                    break;
//            }
//        }
//    }
//    public static List<Genre> addGenreList(Genre genre, List<Genre> genreList){
//        if(genreList.contains(genre)){
//            System.out.println("This genre is already exist!");
//        }
//        else {
//            genreList.add(genre);
//            System.out.println("Genre is added");
//        }
//        return genreList;
//    }
//    public static void showClientList(List<Client> clients){
//        for (Client client:clients){
//            System.out.println(client);
//        }
//    }
//    public static void showBookList(List<Book> books){
//        for (Book book:books){
//            System.out.println(book);
//        }
//    }
//    public static void showClienBooktList(List<ClientBook> books){
//        for (ClientBook clientBook:books){
//            System.out.println(clientBook);
//        }
//    }
//    public static void showGenreList(List<Genre> genreList){
//        for (Genre genre:genreList){
//            System.out.println(genre);
//        }
//    }
//
//}
