package ru.otus.sessionmanager;

import org.hibernate.Session;
import org.hibernate.Transaction;

public interface DatabaseSession {
    Session getHibernateSession();
    Transaction getTransaction();
}
