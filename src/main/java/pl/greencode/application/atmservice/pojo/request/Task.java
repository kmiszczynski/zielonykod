package pl.greencode.application.atmservice.pojo.request;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
public class Task implements Comparable<Task> {
    @Range(min = 1, max = 9999)
    private final Integer region;
    private final RequestType requestType;
    @Range(min = 1, max = 9999)
    private final Integer atmId;

    @Override
    public int compareTo(Task other) {
        int regionPriority = this.region - other.region;
        if (regionPriority != 0) {
            return regionPriority;
        }
        return this.requestType.getOrder() - other.requestType.getOrder();
    }
}
