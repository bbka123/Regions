package ru.nukkit.regions.commands.builder;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Location;
import ru.nukkit.regions.Regions;
import ru.nukkit.regions.commands.Cmd;
import ru.nukkit.regions.commands.CmdDefine;
import ru.nukkit.regions.util.Message;

import java.util.List;

@CmdDefine(command = "replace", alias = "blockreplace", subCommands = {"\\S+","\\S+"}, permission = "regions.builder", description = Message.REPLACE_DESC)
public class CmdReplace extends Cmd {

    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        List<Location> locs = Regions.getSelector().getPoints(player);
        if (locs == null||locs.size()!=2) return Message.BUILD_SELECT.print(player);
        Block b1 = Regions.getBuilder().getNewBlock(args[0]);
        if (b1==null) return Message.BUILD_WRONG_ID.print(sender,args[0]);
        Block b2 = Regions.getBuilder().getNewBlock(args[1]);
        if (b2==null) return Message.BUILD_WRONG_ID.print(sender,args[1]);
        Regions.getBuilder().replaceBlock(player, b1,b2, args[0].contains(":"), locs.get(0),locs.get(1));
        return true;
    }
}
