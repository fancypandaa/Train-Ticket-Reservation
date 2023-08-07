package spring.sys.train.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import spring.sys.train.commands.ScheduleCommand;
import spring.sys.train.models.Schedule;
import spring.sys.train.repositories.ScheduleRepository;
import spring.sys.train.services.ScheduleService;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
class SchedulesControllerTest {
    @Mock
    ScheduleService scheduleService;
    SchedulesController schedulesController;
    MockMvc mockMvc;
    @Mock
    Model model;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        schedulesController = new SchedulesController(scheduleService);
        mockMvc = MockMvcBuilders.standaloneSetup(schedulesController).build();
    }

    @Test
    void getSchedules() {
        Set<Schedule> schedules =new HashSet<>();
        schedules.add(new Schedule());
        Schedule schedule= new Schedule();
        schedule.setId(1L);
        schedules.add(new Schedule());

        when(scheduleService.getSchedules()).thenReturn(schedules);
        ArgumentCaptor<Set<Schedule>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
        String viewName= schedulesController.getSchedules(model);
        assertEquals("schedules",viewName);
        verify(scheduleService,times(1)).getSchedules();
        verify(model,times(1)).addAttribute(eq("schedules"),argumentCaptor.capture());
        Set<Schedule> scheduleSet = argumentCaptor.getValue();
        assertEquals(2,scheduleSet.size());

    }

    @Test
    void getScheduleById() throws Exception{
        Schedule schedule = new Schedule();

        //when
        when(scheduleService.findScheduleById(anyLong())).thenReturn(schedule);

        //then
        mockMvc.perform(get("/schedule/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("showSchedule"))
                .andExpect(model().attributeExists("schedule"));
    }
}