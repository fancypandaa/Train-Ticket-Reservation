package spring.sys.train.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.sys.train.models.Ticket;

public interface TicketRepository extends CrudRepository<Ticket,Long> {

}
