package pl.greencode.application.atmservice.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.greencode.application.atmservice.pojo.request.Task;
import pl.greencode.application.atmservice.pojo.response.ATM;
import pl.greencode.application.atmservice.service.AtmService;

import java.util.List;

@RestController
@AllArgsConstructor
public class AtmServiceController {
    private final AtmService atmService;

    @PostMapping("/atms/calculateOrder")
    public List<ATM> calculateOrder(@Valid @RequestBody List<Task> tasks) {
        return atmService.calculateOrder(tasks);
    }
}
