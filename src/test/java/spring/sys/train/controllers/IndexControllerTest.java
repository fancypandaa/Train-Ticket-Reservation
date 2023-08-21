package spring.sys.train.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import spring.sys.train.models.Train;
import spring.sys.train.services.TrainService;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.util.*;

class IndexControllerTest {
    @Mock
    TrainService trainService;
    @Mock
    Model model;

    @Mock
    HttpSession httpSession;
    IndexController indexController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(trainService);
    }
    @Test
    void testMockMvc()throws Exception{
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    void getIndexPage() {
        Set<Train> trains =new HashSet<>();
        trains.add(new Train());
        Train train=new Train();
        train.setId(1L);
        trains.add(train);
        when(trainService.listTrains()).thenReturn(trains);
        ArgumentCaptor<Set<Train>> argumentCaptor=ArgumentCaptor.forClass(Set.class);
        String viewname= indexController.getIndexPage(model,httpSession);
        assertEquals("home",viewname);
        verify(trainService,times(1)).listTrains();
        verify(model,times(1)).addAttribute(eq("trains"),argumentCaptor.capture());
    }
}