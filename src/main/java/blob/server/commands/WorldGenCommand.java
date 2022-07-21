package blob.server.commands;

import blob.server.gen.WorldGenerator;
import blob.server.gen.dimension.Dimension;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.coordinate.Point;
import net.minestom.server.entity.Player;

public class WorldGenCommand extends Command {

    public WorldGenCommand() {
        super("wgdata");

        // Executed if no other executor can be used
        setDefaultExecutor((sender, context) -> {
            if (!(sender instanceof Player)) sender.sendMessage(Component.text("Command can only be used by Players").color(NamedTextColor.DARK_RED));
            Player p = (Player) sender;
            WorldGenerator wg = (WorldGenerator) p.getInstance().generator();
            Dimension d = wg.getDim();
            Point pos = p.getPosition();
            sender.sendMessage("Roughness: " + d.getRoughnessAt(pos.blockX(), pos.blockZ()) + " | Seaness: " + d.getSeanessAt(pos.blockX(), pos.blockZ()) + " | Temperature: " + d.getTemperatureAt(pos.blockX(), pos.blockZ()));
        });
    }

}
