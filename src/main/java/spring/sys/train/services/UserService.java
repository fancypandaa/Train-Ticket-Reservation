package spring.sys.train.services;

import spring.sys.train.commands.UserCommand;

public interface UserService {
    
    UserCommand findUserByEmailAndPassword(String email,String password);
    UserCommand saveNewUser(UserCommand userCommand);

}
