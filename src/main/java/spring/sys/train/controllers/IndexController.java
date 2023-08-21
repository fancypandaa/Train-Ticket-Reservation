package spring.sys.train.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import spring.sys.train.services.TrainService;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class IndexController {
    private final TrainService trainService;

    public IndexController(TrainService trainService) {
        this.trainService = trainService;
    }

    @RequestMapping({"","/","/home"})
    public String getIndexPage(Model model, HttpSession session){
        log.debug("Getting Home page");
        model.addAttribute("trains",trainService.listTrains());
        String sessionId = session.getId();
        System.out.println("[session-id]: " + sessionId);
        return "home";
    }
}
