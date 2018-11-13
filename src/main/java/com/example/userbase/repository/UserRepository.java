package com.example.userbase.repository;

import com.example.userbase.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public UserRepository() {
        users.add(new User("Jan", "Kowalski", 25));
        users.add(new User("Monika", "Nowak", 32));
        users.add(new User("Jurek", "Zawada", 28));
    }

    public void add(User user) {
        users.add(user);
    }
}
