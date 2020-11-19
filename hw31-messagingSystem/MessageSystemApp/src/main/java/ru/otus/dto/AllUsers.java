package ru.otus.dto;

import ru.otus.domain.User;
import ru.otus.messagesystem.client.ResultDataType;

import java.util.List;

public class AllUsers extends ResultDataType {
    private final List<User> list;

    public AllUsers(List<User> list) {
        this.list = list;
    }

    public List<User> getList() {
        return list;
    }
}
