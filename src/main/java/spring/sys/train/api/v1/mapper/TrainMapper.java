package spring.sys.train.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import spring.sys.train.api.v1.modelsDTO.TrainDTO;
import spring.sys.train.models.Train;

@Mapper
public interface TrainMapper {
    TrainMapper INSTANCE= Mappers.getMapper(TrainMapper.class);
    TrainDTO trainToTrainDTO(Train train);
}
