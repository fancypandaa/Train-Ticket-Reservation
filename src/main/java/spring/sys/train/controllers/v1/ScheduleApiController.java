package spring.sys.train.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.sys.train.api.v1.modelsDTO.ScheduleDTO;
import spring.sys.train.api.v1.modelsDTO.ScheduleListDTO;
import spring.sys.train.services.ScheduleService;
import spring.sys.train.services.apiServices.ScheduleDTOService;

@RestController
@RequestMapping(ScheduleApiController.BASE_URL)
public class ScheduleApiController {
    public static final String BASE_URL= "/api/v1/schedules";
    public final ScheduleDTOService scheduleDTOService;

    public ScheduleApiController(ScheduleDTOService scheduleDTOService) {
        this.scheduleDTOService = scheduleDTOService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ScheduleListDTO getScheduleList(){
        return new ScheduleListDTO(scheduleDTOService.getSchedules());
    }

    @GetMapping({"/{Id}"})
    @ResponseStatus(HttpStatus.OK)
    public ScheduleDTO getScheduleById(@PathVariable Long Id) throws Exception {
        return scheduleDTOService.findScheduleById(Id);
    }
    @GetMapping({"trainNo/{trainId}"})
    @ResponseStatus(HttpStatus.OK)
    public ScheduleListDTO getScheduleByTrainId(@PathVariable Long trainId) throws Exception {
        return new ScheduleListDTO(scheduleDTOService.findSchedulesByTrainId(trainId));
    }
}
