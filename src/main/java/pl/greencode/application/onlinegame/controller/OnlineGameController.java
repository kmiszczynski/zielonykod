package pl.greencode.application.onlinegame.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.greencode.application.onlinegame.pojo.common.Clan;
import pl.greencode.application.onlinegame.pojo.request.Players;
import pl.greencode.application.onlinegame.service.OnlineGameService;

import java.util.List;

@RestController
@AllArgsConstructor
public class OnlineGameController {
    private final OnlineGameService onlineGameService;

    @PostMapping("/onlinegame/calculate")
    public List<List<Clan>> calculateEntryOrder(@Valid @RequestBody Players players) {
        return onlineGameService.calculateEntryOrder(players);
    }
}
