package spring.sys.train.repository;

import org.springframework.data.repository.CrudRepository;
import spring.sys.train.model.Ticket;

public interface TicketRepository extends CrudRepository<Ticket,Long> {

}
