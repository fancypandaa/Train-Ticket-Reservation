package spring.sys.train.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import spring.sys.train.api.v1.modelsDTO.TrainStatusDTO;
import spring.sys.train.models.TrainStatus;

@Mapper
public interface TrainStatusMapper {
    TrainStatusMapper INSTANCE= Mappers.getMapper(TrainStatusMapper.class);
    TrainStatusDTO trainStatusToTrainStatusCommand(TrainStatus trainStatus);
}
