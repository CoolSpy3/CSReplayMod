package com.coolspy3.csreplaymod;

import com.coolspy3.cspackets.datatypes.MCColor;
import com.coolspy3.util.ModUtil;

import net.hypixel.api.reply.StatusReply.Session;

public class WhatGameIsThisCommand extends GameCommand
{

    @Override
    public boolean shouldTrigger(String msg)
    {
        return msg.matches("/whatgameisthis( .*)?") || msg.matches("/wgit( .*)?");
    }

    @Override
    public void invoke(Session session, String msg)
    {
        ModUtil.sendMessage(
                MCColor.AQUA + "You are playing \"" + CSReplayMod.gameCode(session) + "\"");
    }

}
