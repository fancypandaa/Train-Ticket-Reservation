package spring.sys.train.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import spring.sys.train.api.v1.modelsDTO.ScheduleDTO;
import spring.sys.train.models.Schedule;

@Mapper
public interface ScheduleMapper {
    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);
    ScheduleDTO scheduleToScheduleDTO(Schedule schedule);
}
