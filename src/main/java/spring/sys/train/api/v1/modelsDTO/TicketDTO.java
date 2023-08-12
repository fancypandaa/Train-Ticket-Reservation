package spring.sys.train.api.v1.modelsDTO;

import lombok.Data;

import java.util.Date;
@Data
public class TicketDTO {
    private Long Id;
    private String status;
    private Long price;
    private String Type;
    private Date date;
    private Long userId;
    private Long trainId;
}
