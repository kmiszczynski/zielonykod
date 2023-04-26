package pl.greencode.application.onlinegame.pojo.common;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
public class Clan implements Comparable<Clan> {
    @Range(min = 1, max = 1000)
    private final Integer numberOfPlayers;
    @Range(min = 1, max = 1000000)
    private final Integer points;

    @Override
    public int compareTo(Clan other) {
        int pointsPriority = other.points - this.points;
        if (pointsPriority != 0) {
            return pointsPriority;
        }
        return this.numberOfPlayers - other.numberOfPlayers;
    }
}
