package pl.greencode.application.onlinegame.service;

import org.junit.jupiter.api.Test;
import pl.greencode.application.onlinegame.pojo.common.Clan;
import pl.greencode.application.onlinegame.pojo.request.Players;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OnlineGameServiceTest {
    private final OnlineGameService onlineGameService = new OnlineGameService();

    @Test
    public void shouldCreateGroupsInProperOrder() {
        // given
        Players players = providePlayersRequest();
        List<List<Clan>> expectedGroups = provideExpectedGroups();

        // when
        List<List<Clan>> orderedGroups = onlineGameService.calculateEntryOrder(players);

        // then
        assertEquals(expectedGroups, orderedGroups);
    }

    private List<List<Clan>> provideExpectedGroups() {
        return List.of(
                List.of(
                        Clan.builder()
                                .numberOfPlayers(2)
                                .points(70)
                                .build(),
                        Clan.builder()
                                .numberOfPlayers(4)
                                .points(50)
                                .build()
                ),
                List.of(
                        Clan.builder()
                                .numberOfPlayers(6)
                                .points(60)
                                .build()
                ),
                List.of(
                        Clan.builder()
                                .numberOfPlayers(3)
                                .points(45)
                                .build(),
                        Clan.builder()
                                .numberOfPlayers(1)
                                .points(15)
                                .build(),
                        Clan.builder()
                                .numberOfPlayers(1)
                                .points(12)
                                .build()
                ),
                List.of(
                        Clan.builder()
                                .numberOfPlayers(4)
                                .points(40)
                                .build()
                ),
                List.of(
                        Clan.builder()
                                .numberOfPlayers(5)
                                .points(40)
                                .build()
                )
        );
    }

    private Players providePlayersRequest() {
        return Players.builder()
                .groupCount(6)
                .clans(List.of(
                        Clan.builder()
                                .numberOfPlayers(4)
                                .points(50)
                                .build(),
                        Clan.builder()
                                .numberOfPlayers(2)
                                .points(70)
                                .build(),
                        Clan.builder()
                                .numberOfPlayers(6)
                                .points(60)
                                .build(),
                        Clan.builder()
                                .numberOfPlayers(1)
                                .points(15)
                                .build(),
                        Clan.builder()
                                .numberOfPlayers(5)
                                .points(40)
                                .build(),
                        Clan.builder()
                                .numberOfPlayers(3)
                                .points(45)
                                .build(),
                        Clan.builder()
                                .numberOfPlayers(1)
                                .points(12)
                                .build(),
                        Clan.builder()
                                .numberOfPlayers(4)
                                .points(40)
                                .build()
                ))
                .build();
    }
}