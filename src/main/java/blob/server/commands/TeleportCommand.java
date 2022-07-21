package blob.server.commands;

import net.kyori.adventure.text.Component;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.utils.entity.EntityFinder;
import net.minestom.server.utils.location.RelativeVec;
import org.jetbrains.annotations.NotNull;

public class TeleportCommand extends Command {

    public TeleportCommand() {
        super("tp");

        setDefaultExecutor((source, context) -> source.sendMessage(Component.text("Usage: /tp x y z")));

        var posArg = ArgumentType.RelativeVec3("position");
        var playerArg = ArgumentType.Word("source");
        var player2Arg = ArgumentType.Word("target");

        addSyntax(this::onPlayerTeleport, player2Arg);
        addSyntax(this::onPositionTeleport, posArg);
        addSyntax(this::onPlayertoPlayerTeleport, playerArg, player2Arg);
        addSyntax(this::onPlayertoPositionTeleport, playerArg, posArg);
    }

    private void onPlayerTeleport(CommandSender sender, CommandContext context) {
        final String playerName = context.get("target");
        Player pl = MinecraftServer.getConnectionManager().getPlayer(playerName);
        if (sender instanceof Player player) {
            player.teleport(pl.getPosition());
        }
        sender.sendMessage(Component.text("Teleported to player " + playerName));
    }

    private void onPlayertoPlayerTeleport(CommandSender sender, CommandContext context) {
        final String playerName = context.get("source");
        final String playerName2 = context.get("target");
        Player pl = MinecraftServer.getConnectionManager().getPlayer(playerName);
        Player pl2 = MinecraftServer.getConnectionManager().getPlayer(playerName2);
        pl.teleport(pl2.getPosition());
        sender.sendMessage(Component.text("Teleported " + playerName + " to player " + playerName2));
    }

    private void onPlayertoPositionTeleport(CommandSender sender, CommandContext context) {
        final String playerName = context.get("source");
        Player pl = MinecraftServer.getConnectionManager().getPlayer(playerName);

        final RelativeVec relativeVec = context.get("position");
        final Pos position = pl.getPosition().withCoord(relativeVec.from(pl));
        pl.teleport(position);
        sender.sendMessage(Component.text(playerName + " has been teleported to " + position));
    }

    private void onPositionTeleport(CommandSender sender, CommandContext context) {
        final Player player = (Player) sender;

        final RelativeVec relativeVec = context.get("position");
        final Pos position = player.getPosition().withCoord(relativeVec.from(player));
        player.teleport(position);
        player.sendMessage(Component.text("You have been teleported to " + position));
    }
}
