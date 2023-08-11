package spring.sys.train.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.sys.train.api.v1.modelsDTO.TrainStatusListDTO;
import spring.sys.train.services.apiServices.TrainStatusService;

@RestController
@RequestMapping(TrainStatusApiController.BASE_URL)
public class TrainStatusApiController {
    public static final String BASE_URL = "/api/v1/trainStatus";
    private final TrainStatusService trainStatusService;

    public TrainStatusApiController(TrainStatusService trainStatusService) {
        this.trainStatusService = trainStatusService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TrainStatusListDTO getallTrainStatus(){
        return new TrainStatusListDTO(trainStatusService.getTrainStatusList());
    }
}
