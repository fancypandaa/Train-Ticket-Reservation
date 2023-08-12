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
import spring.sys.train.api.v1.modelsDTO.TrainDTO;
import spring.sys.train.api.v1.modelsDTO.TrainListDTO;
import spring.sys.train.services.apiServices.TrainDTOService;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.*;
class TrainApiControllerTest {
    private final String ARRIVALSTATION="Union Station";
    private final String DEPARTALSTATIONx="Penn Station";

    @Mock
    TrainDTOService trainDTOService;
    @InjectMocks
    TrainApiController trainApiController;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trainApiController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getListOfTrains() throws Exception {
        TrainDTO trainDTO = new TrainDTO();
        trainDTO.setId(1L);
        TrainDTO trainIDTO = new TrainDTO();
        trainIDTO.setId(2L);
        Set<TrainDTO> trainDTOSet = Sets.set(trainIDTO,trainDTO);
        when(trainDTOService.getAllTrains()).thenReturn(trainDTOSet);
        mockMvc.perform(get(trainApiController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainDTOSet",hasSize(2)));

    }

    @Test
    void getTrainById() throws Exception {
        TrainDTO trainDTO = new TrainDTO();
        trainDTO.setId(1L);
        trainDTO.setCapacity(200L);
        when(trainDTOService.getTrainById(anyLong())).thenReturn(trainDTO);

        //when
        mockMvc.perform(get(trainApiController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    void getListOfTrainsByArrivalStation() throws Exception {
        TrainDTO trainDTO = new TrainDTO();
        trainDTO.setId(1L);
        trainDTO.setArriveStation(ARRIVALSTATION);
        TrainDTO trainDTOx = new TrainDTO();
        trainDTOx.setId(2L);
        trainDTOx.setArriveStation(ARRIVALSTATION);
        Set<TrainDTO> trainDTOSet = Sets.set(trainDTO,trainDTOx);
        when(trainDTOService.findTrainByArrivalStation(ARRIVALSTATION)).thenReturn(trainDTOSet);
        mockMvc.perform(get(trainApiController.BASE_URL+"/arrivalStation")
                        .param("arrivalStation",ARRIVALSTATION)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainDTOSet",hasSize(2)));

    }

    @Test
    void getListOfTrainsByDepartStation() throws Exception {
        TrainDTO trainDTO = new TrainDTO();
        trainDTO.setId(1L);
        trainDTO.setArriveStation(DEPARTALSTATIONx);
        TrainDTO trainDTOx = new TrainDTO();
        trainDTOx.setId(2L);
        trainDTOx.setArriveStation(DEPARTALSTATIONx);
        Set<TrainDTO> trainDTOSet = Sets.set(trainDTO,trainDTOx);
        when(trainDTOService.findTrainByDepartStation(DEPARTALSTATIONx)).thenReturn(trainDTOSet);
        mockMvc.perform(get(trainApiController.BASE_URL+"/departStation")
                        .param("departStation",DEPARTALSTATIONx)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainDTOSet",hasSize(2)));
    }
}