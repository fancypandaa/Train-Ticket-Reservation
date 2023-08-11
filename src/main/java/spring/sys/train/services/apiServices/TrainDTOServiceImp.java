package spring.sys.train.services.apiServices;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import spring.sys.train.api.v1.mapper.TrainMapper;
import spring.sys.train.api.v1.modelsDTO.TrainDTO;
import spring.sys.train.repositories.TrainRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TrainDTOServiceImp implements TrainDTOService{
    private final TrainMapper trainMapper;
    private final TrainRepository trainRepository;

    public TrainDTOServiceImp(TrainMapper trainMapper, TrainRepository trainRepository) {
        this.trainMapper = trainMapper;
        this.trainRepository = trainRepository;
    }


    @Override
    public Set<TrainDTO> getAllTrains() {
        return trainRepository.findAll().stream()
                .map(trainMapper::trainToTrainDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public TrainDTO getTrainById(Long Id) {
        return trainRepository.findById(Id)
                .map(trainMapper::trainToTrainDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Set<TrainDTO> findTrainByCurrentCityAndDestinationCity(String currentCity, String destinationCity) {
        return trainRepository.findTrainByCurrentCityAndDestinationCity(currentCity, destinationCity).stream()
                .map(trainMapper::trainToTrainDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<TrainDTO> findTrainByDepartStation(String departStation) {
        return trainRepository.findTrainByDepartStation(departStation).stream()
                .map(trainMapper::trainToTrainDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<TrainDTO> findTrainByArrivalStation(String arriveStation) {
        return trainRepository.findTrainByArriveStation(arriveStation).stream()
                .map(trainMapper::trainToTrainDTO)
                .collect(Collectors.toSet());
    }

}
