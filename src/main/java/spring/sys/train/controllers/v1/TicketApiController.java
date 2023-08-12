package spring.sys.train.controllers.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TicketApiController.BASE_URL)
public class TicketApiController {
    public static final String BASE_URL="/api/v1/tickets";


}
