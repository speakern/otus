package ru.otus.dto;

import ru.otus.domain.AddressDataSet;
import ru.otus.domain.PhoneDataSet;
import ru.otus.domain.User;

import java.util.ArrayList;

public class UserForm {
    private String name;
    private String login;
    private String password;
    private String phone1;
    private String phone2;
    private String address;

    public UserForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        User user = new User(0, getName(), getLogin(), getPassword());

        if (getAddress().length() > 3) {
            user.setAddressDataSet(new AddressDataSet(getAddress(), user));
        }

        var listPhone = new ArrayList<PhoneDataSet>();
        if (getPhone1().length() > 3) listPhone.add(new PhoneDataSet(getPhone1(), user));
        if (getPhone2().length() > 3) listPhone.add(new PhoneDataSet(getPhone2(), user));
        if (listPhone.size() > 0) user.setPhoneDataSets(listPhone);

        return user;
    }
}
