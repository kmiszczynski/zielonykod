package pl.greencode.application.onlinegame.pojo.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import pl.greencode.application.onlinegame.pojo.common.Clan;

import java.util.List;

@Data
@Builder
public class Players {
    @Range(min = 1, max = 1000)
    private final Integer groupCount;
    @NotNull
    @Size(max = 20000)
    private final List<Clan> clans;
}
