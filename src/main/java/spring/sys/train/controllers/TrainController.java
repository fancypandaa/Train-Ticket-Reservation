package spring.sys.train.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import spring.sys.train.services.TrainService;

@Slf4j
@Controller
public class TrainController {
    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }
    @GetMapping("/train/{id}/show")
    public String getTrainById(@PathVariable String id, Model model) throws Exception {
        model.addAttribute("train",trainService.findTrainById(new Long(id)));
        return "showTrain";
    }
}
