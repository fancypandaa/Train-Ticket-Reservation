package spring.sys.train.services.apiServices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import spring.sys.train.api.v1.mapper.ScheduleMapper;
import spring.sys.train.api.v1.modelsDTO.ScheduleDTO;
import spring.sys.train.models.Train;
import spring.sys.train.repositories.ScheduleRepository;
import spring.sys.train.repositories.TrainRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@Service
@Slf4j
public class ScheduleDTOServiceImp implements ScheduleDTOService{
    private final ScheduleMapper scheduleMapper;
    private final ScheduleRepository scheduleRepository;
    private final TrainRepository trainRepository;

    public ScheduleDTOServiceImp(ScheduleMapper scheduleMapper, ScheduleRepository scheduleRepository,
                                 TrainRepository trainRepository) {
        this.scheduleMapper = scheduleMapper;
        this.scheduleRepository = scheduleRepository;
        this.trainRepository = trainRepository;
    }

    @Override
    public Set<ScheduleDTO> getSchedules() {
        return scheduleRepository.findAll().stream()
                .map(schedule ->
                        {
                            ScheduleDTO scheduleDTO=scheduleMapper.scheduleToScheduleDTO(schedule);
                            scheduleDTO.setTrainId(schedule.getTrain().getId());
                            return scheduleDTO;
                        }
                )
                .collect(Collectors.toSet());
    }

    @Override
    public ScheduleDTO findScheduleById(Long Id) throws Exception {
        return scheduleRepository.findById(Id)
                .map(schedule -> {
                    ScheduleDTO scheduleDTO=scheduleMapper.scheduleToScheduleDTO(schedule);
                    scheduleDTO.setTrainId(schedule.getTrain().getId());
                    return scheduleDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }



    @Override
    public Set<ScheduleDTO> findSchedulesByTrainId(Long trainId) throws Exception {
        Optional<Train> trainOptional = trainRepository.findById(trainId);
        if(!trainOptional.isPresent()){
            log.error("Train id not found"+ trainId);
        }
        Train train =trainOptional.get();
        Set<ScheduleDTO> scheduleDTOS=train.getSchedules().stream().map(schedule -> {
                    ScheduleDTO scheduleDTO=scheduleMapper.scheduleToScheduleDTO(schedule);
                    scheduleDTO.setTrainId(schedule.getTrain().getId());
                    return scheduleDTO;
                })
                .collect(Collectors.toSet());
        return scheduleDTOS;
    }
}
