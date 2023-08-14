package spring.sys.train.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import spring.sys.train.api.v1.modelsDTO.TicketDTO;
import spring.sys.train.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
