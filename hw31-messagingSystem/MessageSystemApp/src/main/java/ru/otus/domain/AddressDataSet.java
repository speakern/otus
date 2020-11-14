package ru.otus.domain;

import ru.otus.messagesystem.client.ResultDataType;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address")
public class AddressDataSet extends ResultDataType{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "street", nullable = false)
    private String street;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false)
    private User user;

    public AddressDataSet() {
    }

    public AddressDataSet(String street, User user) {
        this.street = street;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AddressDataSet{" +
                "id=" + id +
                ", street='" + street + '\'' +
         //       ", userId=" + user.getId() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDataSet that = (AddressDataSet) o;
        return Objects.equals(street, that.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street);
    }
}
