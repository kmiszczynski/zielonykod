package pl.greencode.application.atmservice.pojo.response;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
public class ATM {
    private final Integer region;
    private final Integer atmId;
}
