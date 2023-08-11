package spring.sys.train.api.v1.modelsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.*;
@Data
@AllArgsConstructor
public class TrainListDTO {
    Set<TrainDTO> trainDTOSet;
}
