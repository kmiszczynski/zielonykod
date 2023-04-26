package pl.greencode.application.atmservice.service;

import org.junit.jupiter.api.Test;
import pl.greencode.application.atmservice.pojo.request.Task;
import pl.greencode.application.atmservice.pojo.response.ATM;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static pl.greencode.application.atmservice.pojo.request.RequestType.*;

public class AtmServiceTest {
    private final AtmService atmService = new AtmService();

    @Test
    public void shouldCalculateProperOrderForATMs() {
        // given
        List<Task> tasks = provideTasks();
        List<ATM> expectedOrderedATMs = provideExpectedResult();

        // when
        List<ATM> orderedATMs = atmService.calculateOrder(tasks);

        // then
        assertEquals(expectedOrderedATMs, orderedATMs);
    }

    private List<ATM> provideExpectedResult() {
        return List.of(
                ATM.builder()
                        .region(1)
                        .atmId(1)
                        .build(),
                ATM.builder()
                        .region(2)
                        .atmId(1)
                        .build(),
                ATM.builder()
                        .region(3)
                        .atmId(2)
                        .build(),
                ATM.builder()
                        .region(3)
                        .atmId(1)
                        .build(),
                ATM.builder()
                        .region(4)
                        .atmId(1)
                        .build(),
                ATM.builder()
                        .region(5)
                        .atmId(1)
                        .build(),
                ATM.builder()
                        .region(5)
                        .atmId(2)
                        .build()
        );
    }

    private List<Task> provideTasks() {
        return List.of(
                Task.builder()
                        .region(4)
                        .requestType(STANDARD)
                        .atmId(1)
                        .build(),
                Task.builder()
                        .region(1)
                        .requestType(STANDARD)
                        .atmId(1)
                        .build(),
                Task.builder()
                        .region(2)
                        .requestType(STANDARD)
                        .atmId(1)
                        .build(),
                Task.builder()
                        .region(3)
                        .requestType(PRIORITY)
                        .atmId(2)
                        .build(),
                Task.builder()
                        .region(3)
                        .requestType(STANDARD)
                        .atmId(1)
                        .build(),
                Task.builder()
                        .region(2)
                        .requestType(SIGNAL_LOW)
                        .atmId(1)
                        .build(),
                Task.builder()
                        .region(5)
                        .requestType(STANDARD)
                        .atmId(2)
                        .build(),
                Task.builder()
                        .region(5)
                        .requestType(FAILURE_RESTART)
                        .atmId(1)
                        .build()
        );
    }
}