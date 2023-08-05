package spring.sys.train.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.sys.train.commands.UserCommand;
import spring.sys.train.converters.UserCommandToUser;
import spring.sys.train.converters.UserToUserCommand;
import spring.sys.train.models.Ticket;
import spring.sys.train.models.User;
import spring.sys.train.repositories.UserRepository;

@Service
@Slf4j
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;
    private final UserToUserCommand userToUserCommand;
    private final UserCommandToUser userCommandToUser;


    public UserServiceImp(UserRepository userRepository, UserToUserCommand userToUserCommand, UserCommandToUser userCommandToUser) {
        this.userRepository = userRepository;
        this.userToUserCommand = userToUserCommand;
        this.userCommandToUser = userCommandToUser;
    }

    @Override
    public UserCommand findUserByEmailAndPassword(String email, String password) {
        return null;
    }

    @Override
    @Transactional
    public UserCommand saveNewUser(UserCommand userCommand) {
        User user= userCommandToUser.convert(userCommand);
        User savedUser=userRepository.save(user);
        log.debug("save user",user.getId());
        return  userToUserCommand.convert(savedUser);
    }
}
