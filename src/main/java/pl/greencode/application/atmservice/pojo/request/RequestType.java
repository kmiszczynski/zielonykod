package pl.greencode.application.atmservice.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RequestType {
    FAILURE_RESTART(1),
    PRIORITY(2),
    SIGNAL_LOW(3),
    STANDARD(4);

    private final int order;

}
