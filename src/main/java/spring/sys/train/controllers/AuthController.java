package spring.sys.train.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import spring.sys.train.commands.UserCommand;
import spring.sys.train.models.User;
import spring.sys.train.services.UserService;

@Slf4j
@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterationForm(Model model){
        UserCommand user = new UserCommand();
        model.addAttribute("user",user);
        return "register";
    }
    @PostMapping("/register/save")
    public String registration(@ModelAttribute("userCommand") UserCommand userCommand, BindingResult result,Model model){
        UserCommand user= userService.findUserByEmailAndPassword(userCommand.getEmail(), userCommand.getPassword());
        if(user != null &&user.getEmail() != null && user.getPassword()!=null){
            result.rejectValue("email",null,"There is already an account registered with the same email");
        }
        if(result.hasErrors()){
            model.addAttribute("user",userCommand);
            return "/register";
        }
        userService.saveNewUser(userCommand);
        return "redirect:/register?success";
    }
}
