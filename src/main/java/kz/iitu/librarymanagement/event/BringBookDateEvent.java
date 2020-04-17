package kz.iitu.librarymanagement.event;

import kz.iitu.librarymanagement.entity.Client;
import kz.iitu.librarymanagement.entity.ClientBook;
import org.springframework.context.ApplicationEvent;

public class BringBookDateEvent extends ApplicationEvent {
    private ClientBook clientBook;

    public BringBookDateEvent(Object source, ClientBook clientBook) {
        super(source);
        this.clientBook = clientBook;
    }
    public ClientBook getClientBook(){
        return this.clientBook;
    }
}
