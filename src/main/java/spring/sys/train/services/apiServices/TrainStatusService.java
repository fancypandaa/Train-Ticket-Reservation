package spring.sys.train.services.apiServices;

import spring.sys.train.api.v1.modelsDTO.TrainStatusDTO;

import java.util.*;
public interface TrainStatusService {
    List<TrainStatusDTO> getTrainStatusList();
}
