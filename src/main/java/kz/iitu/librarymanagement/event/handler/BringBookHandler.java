package kz.iitu.librarymanagement.event.handler;

import kz.iitu.librarymanagement.entity.Client;
import kz.iitu.librarymanagement.event.BringBookDateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

public class BringBookHandler implements ApplicationListener<BringBookDateEvent> {

    @Override
    public void onApplicationEvent(BringBookDateEvent bringBookDateEvent) {
        System.out.println("You must return this book to this time: "
                + bringBookDateEvent.getClientBook().getBringDate()+". Taken book is "
                + bringBookDateEvent.getClientBook());
    }
}
