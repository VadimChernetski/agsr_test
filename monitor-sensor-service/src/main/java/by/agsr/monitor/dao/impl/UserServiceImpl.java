package by.agsr.monitor.dao.impl;

import by.agsr.monitor.dao.entity.Role;
import by.agsr.monitor.dao.entity.User;
import by.agsr.monitor.dao.interfaces.RoleService;
import by.agsr.monitor.dao.interfaces.UserService;
import by.agsr.monitor.dao.repository.UserRepository;
import by.agsr.monitor.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    @Transactional
    public User createUser(String nickname, String password) {
        Optional<User> userByNickname = findUserByNickname(nickname);
        if (userByNickname.isPresent()) {
            throw new BadRequestException("User already exists");
        }
//      We assume that admin should be inserted to db in another way
        Role viewerRole = roleService.getViewerRole();

        User user = User.builder()
          .nickname(nickname)
          .password(passwordEncoder.encode(password))
          .role(viewerRole)
          .build();
        return userRepository.save(user);
    }
}
