package spring.sys.train.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.sys.train.api.v1.modelsDTO.TicketDTO;
import spring.sys.train.api.v1.modelsDTO.TicketListDTO;
import spring.sys.train.services.apiServices.TicketDTOService;

@Controller
@RequestMapping(TicketApiController.BASE_URL)
public class TicketApiController {
    public static final String BASE_URL="/api/v1/tickets";
    public final TicketDTOService ticketDTOService;

    public TicketApiController(TicketDTOService ticketDTOService) {
        this.ticketDTOService = ticketDTOService;
    }

    @GetMapping
    public ResponseEntity<TicketListDTO> getTicketList(){
        return new ResponseEntity<TicketListDTO>(new TicketListDTO(ticketDTOService.listTickets()),HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id){
        return new ResponseEntity<TicketDTO>(ticketDTOService.findTicketById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<TicketDTO> createNewTicket(@RequestBody TicketDTO ticketDTO) throws Exception {
        return new ResponseEntity<TicketDTO>(ticketDTOService.createTicket(ticketDTO),HttpStatus.CREATED);
    }
    @PutMapping({"/{id}"})
    public ResponseEntity<TicketDTO> updateExistTicket(@PathVariable Long id,@RequestBody TicketDTO ticketDTO) throws Exception {
        return new ResponseEntity<TicketDTO>(ticketDTOService.saveTicketByDTO(id,ticketDTO),HttpStatus.OK);
    }
    @PatchMapping({"/{id}"})
    public ResponseEntity<TicketDTO> patchExistTicket(@PathVariable Long id,@RequestBody TicketDTO ticketDTO) throws Exception {
        return new ResponseEntity<TicketDTO>(ticketDTOService.patchTicketByDTO(id,ticketDTO),HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id){
        System.out.println(id);
        ticketDTOService.cancelTicket(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
