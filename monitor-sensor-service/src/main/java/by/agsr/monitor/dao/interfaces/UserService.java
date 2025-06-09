package by.agsr.monitor.dao.interfaces;

import by.agsr.monitor.dao.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByNickname(String nickname);

    User createUser(String nickname, String password);
}
