package pl.greencode.application.onlinegame.service;

import org.springframework.stereotype.Service;
import pl.greencode.application.onlinegame.pojo.common.Clan;
import pl.greencode.application.onlinegame.pojo.request.Players;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OnlineGameService {
    public List<List<Clan>> calculateEntryOrder(Players players) {
        LinkedList<Clan> clansSortedByEntryPriority = players.getClans()
                .stream()
                .sorted()
                .collect(Collectors.toCollection(LinkedList::new));

        List<List<Clan>> entryGroups = new ArrayList<>();
        while (!clansSortedByEntryPriority.isEmpty()) {
            entryGroups.add(prepareNextGroup(clansSortedByEntryPriority, players.getGroupCount()));
        }

        return entryGroups;
    }

    private List<Clan> prepareNextGroup(LinkedList<Clan> clansSorted, int maxGroupSize) {
        List<Clan> entryGroup = new ArrayList<>(maxGroupSize);
        Optional<Clan> nextFittingClan = findNextClanFittingIntoGroup(clansSorted, maxGroupSize);
        int currentGroupSize = 0;

        while (!clansSorted.isEmpty() && nextFittingClan.isPresent()) {
            entryGroup.add(nextFittingClan.get());
            clansSorted.remove(nextFittingClan.get());
            currentGroupSize += nextFittingClan.get().getNumberOfPlayers();
            nextFittingClan = findNextClanFittingIntoGroup(clansSorted, maxGroupSize - currentGroupSize);
        }
        return entryGroup;
    }

    private Optional<Clan> findNextClanFittingIntoGroup(LinkedList<Clan> clansSorted, int availableSpace) {
        if (availableSpace <= 0) {
            return Optional.empty();
        }

        return clansSorted.stream()
                .filter(clan -> clan.getNumberOfPlayers() <= availableSpace)
                .findFirst();
    }
}
