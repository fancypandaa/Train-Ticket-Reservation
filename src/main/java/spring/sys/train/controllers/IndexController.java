package spring.sys.train.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import spring.sys.train.services.TrainService;

@Slf4j
@Controller
public class IndexController {
    private final TrainService trainService;

    public IndexController(TrainService trainService) {
        this.trainService = trainService;
    }

    @RequestMapping({"","/","/home"})
    public String getIndexPage(Model model){
        log.debug("Getting Home page");
        model.addAttribute("trains",trainService.listTrains());
        return "home";
    }
}
