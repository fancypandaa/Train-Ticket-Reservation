package spring.sys.train.services.apiServices;

import spring.sys.train.api.v1.modelsDTO.ScheduleDTO;
import spring.sys.train.commands.ScheduleCommand;
import spring.sys.train.models.Schedule;

import java.util.Set;

public interface ScheduleDTOService {
    Set<ScheduleDTO> getSchedules();
    ScheduleDTO findScheduleById(Long Id) throws Exception;
    Set<ScheduleDTO> findSchedulesByTrainId(Long trainId) throws Exception;
}
