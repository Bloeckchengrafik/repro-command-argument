package io.github.bloeckchengrafik;

import net.minestom.server.command.builder.Command;

public class TestCommand extends Command {
    public TestCommand() {
        super("test");

        OfflinePlayerArgument argument = new OfflinePlayerArgument("offlinePlayer");

        addSyntax(((sender, context) -> {
            var offlinePlayer = context.get(argument);
            sender.sendMessage("Chose player: " + offlinePlayer.name());
        }), argument);

        setDefaultExecutor(((sender, context) -> {
            sender.sendMessage("Usage: /test <player>");
        }));
    }
}
