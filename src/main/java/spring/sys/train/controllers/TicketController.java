package spring.sys.train.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.sys.train.services.TicketService;
import spring.sys.train.services.UserService;

@Slf4j
@Controller
public class TicketController {
    private final TicketService ticketService;
    private final UserService userService;
    public TicketController(TicketService ticketService,UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }
    @GetMapping("/ticket/list")
    public String getUserTickets(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        model.addAttribute("user",username);
        model.addAttribute("tickets",userService.findUserByEmail(username).getTickets());
        return "ticketList";
    }
}
