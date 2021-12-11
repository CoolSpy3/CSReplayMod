package com.coolspy3.csreplaymod;

import com.coolspy3.csmodloader.mod.Entrypoint;
import com.coolspy3.csmodloader.mod.Mod;
import com.coolspy3.csmodloader.network.PacketHandler;

import net.hypixel.api.reply.StatusReply;

@Mod(id = "replaymod", name = "CSReplayMod",
        description = "Provides the ability to easily replay game types", version = "2.0.2",
        dependencies = {"csmodloader:[1,2)", "cspackets:[1,2)", "csutils:[1,2)",
                "cshypixelapi:[2,3)"})
public class CSReplayMod implements Entrypoint
{

    @Override
    public void init(PacketHandler handler)
    {
        handler.register(this);
        handler.register(new PlayAgainCommand());
        handler.register(new SPlayCommand());
        handler.register(new WhatGameIsThisCommand());
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
        return filterSkywars(filterSkyblock(
                session.getGameType().getDbName() + "_" + session.getMode().toLowerCase()));
    }

}
