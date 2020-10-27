package ru.otus.repository;


import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.otus.domain.User;
import ru.otus.exeptions.UserDaoWebMvcException;
import ru.otus.sessionmanager.DatabaseSession;
import ru.otus.sessionmanager.SessionManager;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoHibernate implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoHibernate.class);

    private final SessionManager sessionManager;

    public UserDaoHibernate(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public Optional<User> findById(long id) {
        DatabaseSession currentSession = sessionManager.getCurrentSession();
        try {
            return Optional.ofNullable(currentSession.getHibernateSession().find(User.class, id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        User user = sessionManager.getCurrentSession().getHibernateSession().createQuery(
                "select u from User u where u.login = :login", User.class)
                .setParameter("login", login)
                .getSingleResult();
        return Optional.ofNullable(user);
    }

    @Override
    public long insertUser(User user) {
        DatabaseSession currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            hibernateSession.persist(user);
            hibernateSession.flush();
            return user.getId();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new UserDaoWebMvcException(e);
        }
    }

    @Override
    public void updateUser(User user) {
        DatabaseSession currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            hibernateSession.merge(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new UserDaoWebMvcException(e);
        }
    }

    @Override
    public void insertOrUpdate(User user) {
        DatabaseSession currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            if (user.getId() > 0) {
                hibernateSession.merge(user);
            } else {
                hibernateSession.persist(user);
                hibernateSession.flush();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new UserDaoWebMvcException(e);
        }
    }

    @Override
    public List<User> findAll() {
        Session session = sessionManager.getCurrentSession().getHibernateSession();
        return session.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
}
