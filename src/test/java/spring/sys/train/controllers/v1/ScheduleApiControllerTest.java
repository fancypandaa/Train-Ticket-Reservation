package spring.sys.train.controllers.v1;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spring.sys.train.api.v1.modelsDTO.ScheduleDTO;
import spring.sys.train.api.v1.modelsDTO.TrainDTO;
import spring.sys.train.models.Schedule;
import spring.sys.train.models.Train;
import spring.sys.train.services.apiServices.ScheduleDTOService;

import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ScheduleApiControllerTest {
    @Mock
    ScheduleDTOService scheduleDTOService;
    @InjectMocks
    ScheduleApiController scheduleApiController;
    MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleApiController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
    }

    @Test
    void getScheduleList() throws Exception {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(1L);
        ScheduleDTO scheduleXDTO = new ScheduleDTO();
        scheduleXDTO.setId(2L);
        Set<ScheduleDTO> scheduleDTOS = Sets.set(scheduleDTO,scheduleXDTO);
        when(scheduleDTOService.getSchedules()).thenReturn(scheduleDTOS);
        mockMvc.perform(get(scheduleApiController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleDTOS",hasSize(2)));
    }

    @Test
    void getScheduleById() throws Exception {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(1L);
        when(scheduleDTOService.findScheduleById(anyLong())).thenReturn(scheduleDTO);
        //when
        mockMvc.perform(get(scheduleApiController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));

    }

    @Test
    void getScheduleByTrainId() throws Exception {
        Train train = new Train();
        train.setId(1L);
        Schedule schedule = new Schedule();
        schedule.setId(2L);
        train.addSchedule(schedule);
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(2L);
        Set<ScheduleDTO> scheduleDTOS = Sets.set(scheduleDTO);
        when(scheduleDTOService.findSchedulesByTrainId(anyLong())).thenReturn(scheduleDTOS);
        //when
        mockMvc.perform(get(scheduleApiController.BASE_URL + "/trainNo/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleDTOS", hasSize(1)));
    }
}