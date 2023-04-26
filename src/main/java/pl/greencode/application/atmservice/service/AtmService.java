package pl.greencode.application.atmservice.service;

import org.springframework.stereotype.Service;
import pl.greencode.application.atmservice.pojo.request.Task;
import pl.greencode.application.atmservice.pojo.response.ATM;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AtmService {
    public List<ATM> calculateOrder(List<Task> tasks) {
        List<Task> tasksSortedByRegionAndPriority = tasks.stream()
                .sorted()
                .toList();
        Set<ATM> visitedATMs = new HashSet<>();
        List<ATM> orderedATMs = new ArrayList<>();
        tasksSortedByRegionAndPriority.forEach(task -> addAtmToOrderedListIfNotVisited(task, orderedATMs, visitedATMs));

        return orderedATMs;
    }

    private void addAtmToOrderedListIfNotVisited(Task task, List<ATM> orderedATMs, Set<ATM> visitedATMs) {
        ATM atm = mapTaskToATM(task);
        if (!visitedATMs.contains(atm)) {
            orderedATMs.add(atm);
            visitedATMs.add(atm);
        }
    }

    private ATM mapTaskToATM(Task task) {
        return ATM.builder()
                .atmId(task.getAtmId())
                .region(task.getRegion())
                .build();
    }
}
