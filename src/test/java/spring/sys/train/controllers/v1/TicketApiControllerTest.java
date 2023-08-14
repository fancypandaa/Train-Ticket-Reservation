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
import spring.sys.train.api.v1.modelsDTO.TicketDTO;
import spring.sys.train.services.apiServices.TicketDTOService;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static spring.sys.train.controllers.v1.AbstractRestControllerTest.asJsonString;

class TicketApiControllerTest {
    @Mock
    TicketDTOService ticketDTOService;
    @InjectMocks
    TicketApiController ticketApiController;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ticketApiController).build();
    }

    @Test
    void getTicketList() throws Exception {
        TicketDTO ticketDTO =new TicketDTO();
        ticketDTO.setId(1L);
        TicketDTO ticketDTO1 = new TicketDTO();
        ticketDTO1.setId(2L);
        Set<TicketDTO> ticketDTOS = Sets.set(ticketDTO,ticketDTO1);

        when(ticketDTOService.listTickets()).thenReturn(ticketDTOS);
        mockMvc.perform(get("/api/v1/tickets")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$.ticketDTOS", hasSize(2)));
    }

    @Test
    void getTicketById() throws Exception {
        TicketDTO ticketDTO =new TicketDTO();
        ticketDTO.setStatus("pre");
        ticketDTO.setPrice(20L);

        when(ticketDTOService.findTicketById(anyLong())).thenReturn(ticketDTO);
        mockMvc.perform(get("/api/v1/tickets/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$.price", equalTo(20)));

    }

    @Test
    void createNewTicket() throws Exception {
        TicketDTO ticketDTO =new TicketDTO();
        ticketDTO.setStatus("pre");
        ticketDTO.setPrice(20L);
        TicketDTO returnTicket= new TicketDTO();
        returnTicket.setPrice(ticketDTO.getPrice());
        returnTicket.setStatus(ticketDTO.getStatus());
        when(ticketDTOService.createTicket(ticketDTO)).thenReturn(returnTicket);
        mockMvc.perform(post("/api/v1/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ticketDTO))
        ).andExpect(status().isCreated()).
                andExpect(jsonPath("$.price", equalTo(20)))
                .andExpect(jsonPath("$.status", equalTo("pre")));
    }

    @Test
    void updateExistTicket() throws Exception {
        TicketDTO ticketDTO =new TicketDTO();
        ticketDTO.setStatus("pre");
        ticketDTO.setPrice(20L);
        TicketDTO returnTicket= new TicketDTO();
        returnTicket.setPrice(ticketDTO.getPrice());
        returnTicket.setStatus(ticketDTO.getStatus());
        when(ticketDTOService.saveTicketByDTO(anyLong(),any(TicketDTO.class))).thenReturn(returnTicket);
        mockMvc.perform(put("/api/v1/tickets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ticketDTO))
                ).andExpect(status().isOk()).
                andExpect(jsonPath("$.price", equalTo(20)))
                .andExpect(jsonPath("$.status", equalTo("pre")));
    }

    @Test
    void patchExistTicket() throws Exception {
        TicketDTO ticketDTO =new TicketDTO();
        ticketDTO.setStatus("pre");
        ticketDTO.setPrice(20L);
        TicketDTO returnTicket= new TicketDTO();
        returnTicket.setPrice(40L);
        when(ticketDTOService.patchTicketByDTO(anyLong(),any(TicketDTO.class))).thenReturn(returnTicket);
        mockMvc.perform(patch("/api/v1/tickets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ticketDTO))
                ).andExpect(status().isOk()).
                andExpect(jsonPath("$.price", equalTo(40)));

    }

    @Test
    void deleteTicket() throws Exception {
        mockMvc.perform(delete("/api/v1/tickets/1")
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk());
        verify(ticketDTOService).cancelTicket(anyLong());

    }
}