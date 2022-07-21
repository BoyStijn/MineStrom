package blob.server.commands;

import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.minecraft.ArgumentEntity;
import net.minestom.server.command.builder.arguments.number.ArgumentInteger;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.utils.entity.EntityFinder;
import org.jetbrains.annotations.NotNull;

public class TimeCommand extends Command {

    public TimeCommand() {
        super("time");

        ArgumentEnum<TimeType> timetype = ArgumentType.Enum("timetype", TimeType.class).setFormat(ArgumentEnum.Format.LOWER_CASED);
        timetype.setCallback((sender, exception) -> {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Component.text("Only players can Query time", NamedTextColor.RED));
                return;
            }

            Player p = (Player) sender;
            long wtime = p.getInstance().getTime();

            sender.sendMessage(Component.text("Current time: " + getTimeString(wtime,true)));
        });

        ArgumentInteger time = ArgumentType.Integer("time");

        setDefaultExecutor((sender, context) -> {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Component.text("Only players can Query time", NamedTextColor.RED));
                return;
            }

            Player p = (Player) sender;
            long wtime = p.getInstance().getTime();

            sender.sendMessage(Component.text("Current time: " + getTimeString(wtime,true)));
        });

        addSyntax((sender, context) -> {
            TimeType mode = context.get(timetype);
            if (mode != TimeType.QUERY) {
                sender.sendMessage(Component.text("Invalid time", NamedTextColor.RED));
                return;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage(Component.text("Only players can Query time", NamedTextColor.RED));
                return;
            }

            Player p = (Player) sender;
            long wtime = p.getInstance().getTime();

            sender.sendMessage(Component.text("Current time: " + getTimeString(wtime,true)));

        }, timetype);

        //Command Syntax for /gamemode <gamemode> [targets]
        addSyntax((sender, context) -> {
            TimeType mode = context.get(timetype);
            Integer atime = context.get(time);
            if (!(sender instanceof Player)) {
                sender.sendMessage(Component.text("Only players can alter time", NamedTextColor.RED));
                return;
            }
            Player p = (Player) sender;
            long wtime = p.getInstance().getTime();
            switch (mode) {
                case ADD -> {
                    long ztime = wtime + atime;
                    if (ztime > 24000) ztime -= 24000;
                    p.getInstance().setTime(ztime);
                    sender.sendMessage(Component.text("Added: " + getTimeString(atime,false) + " to: " + getTimeString(wtime,true)));
                }
                case SET -> {
                    p.getInstance().setTime(atime);
                    sender.sendMessage(Component.text("Set: " + getTimeString(wtime,true) + " to: " + getTimeString(atime,true)));
                }
                case QUERY -> {
                    long ztime = wtime + atime;
                    if (ztime > 24000) ztime -= 24000;
                    sender.sendMessage(Component.text("Time after " + getTimeString(atime,false) + " will be: " + getTimeString(ztime,true)));
                }
            }

        }, timetype, time);

    }

    private static String getTimeString(long time, boolean offset) {
        if (offset) {
            time += 6000;
            if (time > 24000) time -= 24000;
        }
        long remainder = (time % 1000);
        long hours = (time-remainder)/1000;
        int minutes = (int) Math.floor(remainder/16.6667);
        double secrem = remainder % 16.6667;
        int seconds = (int) Math.ceil(secrem * 3.6);
        return String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
    }

    public enum TimeType {
        SET,
        ADD,
        QUERY
    }
}
