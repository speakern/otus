package ru.otus.jdbc.dao;

import ru.otus.core.dao.AccountDao;
import ru.otus.core.model.Account;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.mapper.JdbcMapper;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import java.util.Optional;

public class AccountDaoJdbcMapper implements AccountDao {
    private final JdbcMapper<Account> jdbcMapper;
    private final SessionManagerJdbc sessionManager;

    public AccountDaoJdbcMapper(SessionManagerJdbc sessionManager, JdbcMapper<Account> jdbcMapper) {
        this.jdbcMapper = jdbcMapper;
        this.sessionManager = sessionManager;
    }

    @Override
    public Optional<Account> findById(long id) {
        Account account = jdbcMapper.findById(id, Account.class);
        return Optional.ofNullable(account);
    }

    @Override
    public long insertAccount(Account account) {
        return jdbcMapper.insert(account);
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
