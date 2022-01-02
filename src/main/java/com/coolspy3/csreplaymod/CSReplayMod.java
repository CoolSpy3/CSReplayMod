package com.coolspy3.csreplaymod;

import com.coolspy3.csmodloader.mod.Entrypoint;
import com.coolspy3.csmodloader.mod.Mod;
import com.coolspy3.csmodloader.network.PacketHandler;
import com.coolspy3.cspackets.packets.ClientChatSendPacket;

import net.hypixel.api.reply.StatusReply;

@Mod(id = "replaymod", name = "CSReplayMod",
        description = "Provides the ability to easily replay game types", version = "2.0.3",
        dependencies = {"csmodloader:[1,2)", "cspackets:[1,2)", "csutils:[1,2)",
                "cshypixelapi:[2,3)"})
public class CSReplayMod implements Entrypoint
{

    @Override
    public void init(PacketHandler handler)
    {
        handler.register(this);
        handler.register(new PlayAgainCommand()::register, ClientChatSendPacket.class);
        handler.register(new SPlayCommand()::register, ClientChatSendPacket.class);
        handler.register(new WhatGameIsThisCommand()::register, ClientChatSendPacket.class);
    }

    public static String filterSkywars(String gameCode)
    {
        return gameCode.startsWith("skywars_") ? gameCode.substring("skywars_".length()) : gameCode;
    }

    public static String filterSkyblock(String gameCode)
    {
        return gameCode.startsWith("skyblock_") ? "skyblock" : gameCode;
    }

    public static String gameCode(StatusReply.Session session)
    {
        return filterSkywars(filterSkyblock(session.getMode().toLowerCase()));
    }

}
