package spring.sys.train.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.sys.train.commands.TrainCommand;
import spring.sys.train.converters.TrainCommandToTrain;
import spring.sys.train.converters.TrainToTrainCommand;
import spring.sys.train.models.Train;
import spring.sys.train.repositories.TrainRepository;

import java.util.*;

@Service
@Slf4j
public class TrainServiceImp implements TrainService {
    private final TrainCommandToTrain trainCommandToTrain;
    private final TrainToTrainCommand trainToTrainCommand;
    private final TrainRepository trainRepository;

    public TrainServiceImp(TrainCommandToTrain trainCommandToTrain, TrainToTrainCommand trainToTrainCommand, TrainRepository trainRepository) {
        this.trainCommandToTrain = trainCommandToTrain;
        this.trainToTrainCommand = trainToTrainCommand;
        this.trainRepository = trainRepository;
    }

    @Override
    public Set<Train> listTrains() {
        log.debug("I'm in the service");
        Set<Train> trainsSet =new HashSet<>();
        trainRepository.findAll().iterator().forEachRemaining(trainsSet::add);
        return trainsSet;
    }

    @Override
    @Transactional
    public Set<TrainCommand> findTrainByCurrentCityAndDestinationCity(String currentCity, String destinationCity) {
        log.debug("I'm in the service");
        Set<TrainCommand> trainsSet =new HashSet<>();
        trainRepository.findTrainByCurrentCityAndDestinationCity(currentCity,destinationCity).iterator().forEachRemaining(train ->
                trainToTrainCommand.convert(train));
        return trainsSet;
    }

    @Override
    @Transactional
    public Set<TrainCommand> findTrainByDepartStation(String departStation) {
        log.debug("I'm in the service");
        Set<TrainCommand> trainsSet =new HashSet<>();
        trainRepository.findTrainByDepartStation(departStation).iterator().forEachRemaining(train ->
                trainToTrainCommand.convert(train));
        return trainsSet;
    }

    @Override
    @Transactional
    public Set<TrainCommand> findTrainByArrivalStation(String arriveStation) {
        log.debug("I'm in the service");
        Set<TrainCommand> trainsSet =new HashSet<>();
        trainRepository.findTrainByArriveStation(arriveStation).iterator().forEachRemaining(train ->
                trainToTrainCommand.convert(train));
        return trainsSet;
    }

    @Override
    public Train findTrainById(Long Id) {
        Optional<Train> trainOptional = trainRepository.findById(Id);
        if(!trainOptional.isPresent()){
            log.error("Current train with Id not found");
        }
        return trainOptional.get();
    }

    @Override
    @Transactional
    public TrainCommand findTrainByCommandId(Long Id) {
        return trainToTrainCommand.convert(findTrainById(Id));
    }


}
