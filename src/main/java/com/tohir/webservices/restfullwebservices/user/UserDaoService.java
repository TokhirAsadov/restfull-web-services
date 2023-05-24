package com.tohir.webservices.restfullwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "John", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Jane", LocalDate.now().minusYears(24)));
        users.add(new User(++usersCount, "Mike", LocalDate.now().minusYears(18)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(Integer id) {
        // new method for me. used predicate........
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().get();
    }

    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }
}
