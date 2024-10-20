package io.github.bloeckchengrafik.offlineplayer;

import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.stream.Collectors;

public class OfflinePlayerProvider {
    private final static OfflinePlayerProvider instance = new OfflinePlayerProvider();

    private final Set<OfflinePlayer> offlinePlayers = Set.of(
        new OfflinePlayer("FirstPlayer"),
        new OfflinePlayer("SecondPlayer"),
        new OfflinePlayer("ThirdPlayer")
    );

    private OfflinePlayerProvider() {
    }

    public Set<String> getOfflinePlayerNames() {
        return offlinePlayers.stream()
            .map(OfflinePlayer::name)
            .collect(Collectors.toSet());
    }

    public @Nullable OfflinePlayer getOfflinePlayer(String name) {
        return offlinePlayers.stream()
            .filter(player -> player.name().equals(name))
            .findFirst()
            .orElse(null);
    }

    public static OfflinePlayerProvider getInstance() {
        return instance;
    }
}
