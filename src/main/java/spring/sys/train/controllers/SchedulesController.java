package spring.sys.train.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.sys.train.services.ScheduleService;

@Slf4j
@Controller
public class SchedulesController {
    private final ScheduleService scheduleService;

    public SchedulesController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    @RequestMapping({"/schedules"})
    public String getSchedules(Model model){
        model.addAttribute("schedules",scheduleService.getSchedules());
        return "schedules";
    }
    @GetMapping("/schedule/{id}/show")
    public String getScheduleById(@PathVariable String id,Model model) throws Exception {
        model.addAttribute("schedule",scheduleService.findScheduleById(new Long(id)));
        return "showSchedule";
    }

}
