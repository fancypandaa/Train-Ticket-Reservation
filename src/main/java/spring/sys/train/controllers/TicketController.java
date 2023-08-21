package spring.sys.train.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import spring.sys.train.commands.TicketCommand;
import spring.sys.train.commands.UserCommand;
import spring.sys.train.models.User;
import spring.sys.train.services.ScheduleService;
import spring.sys.train.services.TicketService;
import spring.sys.train.services.TrainService;
import spring.sys.train.services.UserService;

import java.util.Date;

@Slf4j
@Controller
public class TicketController {
    private final TicketService ticketService;
    private final ScheduleService scheduleService;
    private final UserService userService;
    public TicketController(TicketService ticketService,UserService userService,ScheduleService scheduleService) {
        this.ticketService = ticketService;
        this.userService = userService;
        this.scheduleService= scheduleService;
    }
    @GetMapping("/ticket/list")
    public String getUserTickets(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        model.addAttribute("user",username);
        model.addAttribute("tickets",userService.findUserByEmail(username).getTickets());
        return "ticketList";
    }
    @GetMapping("/ticket/{scheduleId}/{trainId}/{username}/book")
    public String showTicketForm(@PathVariable String scheduleId
            ,Model model) throws Exception {
        model.addAttribute("ticket",new TicketCommand());
        model.addAttribute("schedule",scheduleService.findScheduleById(new Long(scheduleId)));
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        model.addAttribute("user",username);
        return "ticket/booking";
    }
    public Long CalcPrice(TicketCommand ticketCommand){
        Long price = 150L;
        if(ticketCommand.getType() == "super"){
            price+=50;
        }
        else if( ticketCommand.getType() == "business"){
            price +=30;
        }
        else{
            price +=10;
        }
        if(ticketCommand.getStatus() == "pre"){
            price -=5;
        }
        return  price;
    }
    @PostMapping("ticket/{trainId}/{userName}/book")
    public String showTicketInformation(@PathVariable String trainId,@PathVariable String userName,
                                        @ModelAttribute TicketCommand ticketCommand){
        UserCommand user= userService.findUserByEmail(userName);
        if(user.getId() == null) {
            log.error("user id not found:" + userName);

            return null;
        }
        ticketCommand.setTrainId(new Long(trainId));
        ticketCommand.setUserId(new Long(user.getId()));
        ticketCommand.setPrice(this.CalcPrice(ticketCommand));
        ticketCommand.setDate(new Date());
        TicketCommand savedTicket= ticketService.createTicket(ticketCommand);
        log.debug("saved Ticket id:" + savedTicket.getTrainId());
        log.debug("saved UserId id:" + savedTicket.getUserId());
        return "redirect:/ticket/list";
    }
}
