package spring.sys.train.services.apiServices;

import org.springframework.stereotype.Service;
import spring.sys.train.api.v1.mapper.TrainStatusMapper;
import spring.sys.train.api.v1.modelsDTO.TrainStatusDTO;
import spring.sys.train.repositories.TrainStatusRepository;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class TrainStatusServiceImp implements TrainStatusService{
    private final TrainStatusRepository trainStatusRepository;
    private final TrainStatusMapper trainStatusMapper;

    public TrainStatusServiceImp(TrainStatusRepository trainStatusRepository, TrainStatusMapper trainStatusMapper) {
        this.trainStatusRepository = trainStatusRepository;
        this.trainStatusMapper = trainStatusMapper;
    }

    @Override
    public List<TrainStatusDTO> getTrainStatusList() {
        return trainStatusRepository.findAll().stream().map(trainStatusMapper::trainStatusToTrainStatusCommand)
                .collect(Collectors.toList());
    }
}
