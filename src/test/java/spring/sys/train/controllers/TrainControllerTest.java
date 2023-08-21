package spring.sys.train.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import spring.sys.train.models.Schedule;
import spring.sys.train.models.Train;
import spring.sys.train.services.ScheduleService;
import spring.sys.train.services.TrainService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TrainControllerTest {

    @Mock
    TrainService trainService;
    TrainController trainController;
    MockMvc mockMvc;
    @Mock
    Model model;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        trainController = new TrainController(trainService);
        mockMvc = MockMvcBuilders.standaloneSetup(trainController).build();
    }
    @Test
    void getTrainById() throws Exception {
        Train train = new Train();

        //when
        when(trainService.findTrainById(anyLong())).thenReturn(train);

        //then
        mockMvc.perform(get("/train/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("showTrain"))
                .andExpect(model().attributeExists("train"));

    }
}