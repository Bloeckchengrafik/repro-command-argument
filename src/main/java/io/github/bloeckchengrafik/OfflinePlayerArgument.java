package io.github.bloeckchengrafik;

import io.github.bloeckchengrafik.offlineplayer.OfflinePlayer;
import io.github.bloeckchengrafik.offlineplayer.OfflinePlayerProvider;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.arguments.Argument;
import net.minestom.server.command.builder.exception.ArgumentSyntaxException;
import net.minestom.server.command.builder.suggestion.Suggestion;
import net.minestom.server.command.builder.suggestion.SuggestionCallback;
import net.minestom.server.command.builder.suggestion.SuggestionEntry;
import net.minestom.server.utils.binary.BinaryWriter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OfflinePlayerArgument extends Argument<OfflinePlayer> {

    public OfflinePlayerArgument(@NotNull String id) {
        super(id, false);
        this.setSuggestionCallback(new OfflinePlayerSuggestionCallback());
    }

    @Override
    public @NotNull OfflinePlayer parse(@NotNull CommandSender sender, @NotNull String input) throws ArgumentSyntaxException {
        var offlinePlayer = OfflinePlayerProvider.getInstance().getOfflinePlayer(input);
        if (offlinePlayer == null) {
            throw new ArgumentSyntaxException("Unknown player", input, 0);
        }
        return offlinePlayer;
    }

    @Override
    public String parser() {
        return "brigadier:string";
    }

    @Override
    public byte @Nullable [] nodeProperties() {
        return BinaryWriter.makeArray((writer) -> {
            writer.writeVarInt(1);
        });
    }

    static class OfflinePlayerSuggestionCallback implements SuggestionCallback {
        private final Logger logger = LoggerFactory.getLogger(OfflinePlayerSuggestionCallback.class);

        public OfflinePlayerSuggestionCallback() {
            logger.info("OfflinePlayerSuggestionCallback created");
        }

        @Override
        public void apply(@NotNull CommandSender sender, @NotNull CommandContext context, @NotNull Suggestion suggestion) {
            logger.info("Suggesting offline players [THIS IS NEVER PRINTED]");

            for (String name : OfflinePlayerProvider.getInstance().getOfflinePlayerNames()) {
                suggestion.addEntry(new SuggestionEntry(name));
            }
        }
    }
}
