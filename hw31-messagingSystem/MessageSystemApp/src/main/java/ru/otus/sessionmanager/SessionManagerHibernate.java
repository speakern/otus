package ru.otus.sessionmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import ru.otus.exeptions.SessionManagerWebMvcException;

@Component
public class SessionManagerHibernate implements SessionManager {

    private DatabaseSessionHibernate databaseSession;
    private final SessionFactory sessionFactory;

    public SessionManagerHibernate(SessionFactory sessionFactory) {
        if (sessionFactory == null) {
            throw new SessionManagerWebMvcException("SessionFactory is null");
        }
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void beginSession() {
        try {
            databaseSession = new DatabaseSessionHibernate(sessionFactory.openSession());
        } catch (Exception e) {
            throw new SessionManagerWebMvcException(e);
        }
    }

    @Override
    public void commitSession() {
        checkSessionAndTransaction();
        try {
            databaseSession.getTransaction().commit();
            databaseSession.getHibernateSession().close();
        } catch (Exception e) {
            throw new SessionManagerWebMvcException(e);
        }
    }

    @Override
    public void rollbackSession() {
        checkSessionAndTransaction();
        try {
            databaseSession.getTransaction().rollback();
            databaseSession.getHibernateSession().close();
        } catch (Exception e) {
            throw new SessionManagerWebMvcException(e);
        }
    }

    @Override
    public void close() {
        if (databaseSession == null) {
            return;
        }
        Session session = databaseSession.getHibernateSession();
        if (session == null || !session.isConnected()) {
            return;
        }

        Transaction transaction = databaseSession.getTransaction();
        if (transaction == null || !transaction.isActive()) {
            return;
        }

        try {
            databaseSession.close();
            databaseSession = null;
        } catch (Exception e) {
            throw new SessionManagerWebMvcException(e);
        }
    }

    @Override
    public DatabaseSessionHibernate getCurrentSession() {
        checkSessionAndTransaction();
        return databaseSession;
    }

    private void checkSessionAndTransaction() {
        if (databaseSession == null) {
            throw new SessionManagerWebMvcException("DatabaseSession not opened ");
        }
        Session session = databaseSession.getHibernateSession();
        if (session == null || !session.isConnected()) {
            throw new SessionManagerWebMvcException("Session not opened ");
        }

        Transaction transaction = databaseSession.getTransaction();
        if (transaction == null || !transaction.isActive()) {
            throw new SessionManagerWebMvcException("Transaction not opened ");
        }
    }
}
