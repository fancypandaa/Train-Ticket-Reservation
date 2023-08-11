package spring.sys.train.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.sys.train.api.v1.modelsDTO.TrainDTO;
import spring.sys.train.api.v1.modelsDTO.TrainListDTO;
import spring.sys.train.services.apiServices.TrainDTOService;

@RestController
@RequestMapping(TrainApiController.BASE_URL)
public class TrainApiController {
    public static final String BASE_URL="/api/v1/trains";
    public final TrainDTOService trainDTOService;

    public TrainApiController(TrainDTOService trainDTOService) {
        this.trainDTOService = trainDTOService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TrainListDTO getListOfTrains(){
        return new TrainListDTO(trainDTOService.getAllTrains());
    }
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public TrainDTO getTrainById(@PathVariable Long id){
        return trainDTOService.getTrainById(id);
    }
    @GetMapping({"/cities"})
    @ResponseStatus(HttpStatus.OK)
    public TrainListDTO getListOfTrainsByCities(@RequestParam String currentCity,@RequestParam String destCity){
        if(currentCity != null && destCity != null) {
            return new TrainListDTO(trainDTOService.findTrainByCurrentCityAndDestinationCity(currentCity, destCity));
        }
        else {
            return null;
        }
    }
    @GetMapping({"/arrivalStation"})
    @ResponseStatus(HttpStatus.OK)
    public TrainListDTO getListOfTrainsByArrivalStation(@RequestParam String arrivalStation){
        return new TrainListDTO(trainDTOService.findTrainByArrivalStation(arrivalStation));
    }

    @GetMapping({"/departStation"})
    @ResponseStatus(HttpStatus.OK)
    public TrainListDTO getListOfTrainsByDepartStation(@RequestParam String departStation){
        return new TrainListDTO(trainDTOService.findTrainByDepartStation(departStation));
    }
}
