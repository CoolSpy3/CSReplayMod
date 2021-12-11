package com.coolspy3.csreplaymod;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.coolspy3.csmodloader.network.PacketHandler;
import com.coolspy3.cspackets.datatypes.MCColor;
import com.coolspy3.cspackets.packets.ClientChatSendPacket;
import com.coolspy3.util.ModUtil;

import net.hypixel.api.reply.StatusReply.Session;

public class SPlayCommand extends GameCommand
{

    public static final String regex = "/splay ([a-zA-Z0-9_\\-\\/]+)( .*)?";
    public static final Pattern pattern = Pattern.compile(regex);

    @Override
    public boolean shouldTrigger(String msg)
    {
        return msg.matches("/splay( .*)?");
    }

    @Override
    public void invoke(Session session, String msg)
    {
        Matcher matcher = pattern.matcher(msg);
        if (matcher.matches())
        {
            PacketHandler.getLocal().dispatch(new ClientChatSendPacket(
                    "/sc set " + matcher.group(1) + " /play " + CSReplayMod.gameCode(session)));
        }
        else
        {
            ModUtil.sendMessage(MCColor.RED + "Usage: /splay <command>");
        }
    }

}
