package io.github.bloeckchengrafik;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;

public class ReproCommandArgument {
    private final MinecraftServer minestom = MinecraftServer.init();

    public void addCommand() {
        MinecraftServer.getCommandManager().register(new TestCommand());
    }

    public void addWorld() {
        Instance instance = MinecraftServer.getInstanceManager().createInstanceContainer();
        instance.setGenerator(unit -> unit.modifier()
                .fillHeight(0, 64, Block.WHITE_STAINED_GLASS));

        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            event.setSpawningInstance(instance);
            event.getPlayer().setRespawnPoint(new Pos(0, 65, 0));
        });
    }

    public void bind() {
        minestom.start("127.0.0.1", 25565);
    }

    public static void main(String[] args) {
        var server = new ReproCommandArgument();
        server.addCommand();
        server.addWorld();
        server.bind();
    }
}
