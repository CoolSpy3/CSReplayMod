package com.coolspy3.csreplaymod;

import com.coolspy3.csmodloader.network.PacketHandler;
import com.coolspy3.cspackets.packets.ClientChatSendPacket;

import net.hypixel.api.reply.StatusReply.Session;

public class PlayAgainCommand extends GameCommand
{

    @Override
    public boolean shouldTrigger(String msg)
    {
        return msg.matches("/pa( .*)?");
    }

    @Override
    public void invoke(Session session, String msg)
    {
        PacketHandler.getLocal()
                .sendPacket(new ClientChatSendPacket("/play " + CSReplayMod.gameCode(session)));
    }

}
