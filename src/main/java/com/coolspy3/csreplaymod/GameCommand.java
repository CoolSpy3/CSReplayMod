package com.coolspy3.csreplaymod;

import com.coolspy3.csmodloader.GameArgs;
import com.coolspy3.csmodloader.interfaces.ExceptionSupplier;
import com.coolspy3.csmodloader.network.SubscribeToPacketStream;
import com.coolspy3.csmodloader.util.Utils;
import com.coolspy3.cspackets.datatypes.MCColor;
import com.coolspy3.cspackets.packets.ClientChatSendPacket;
import com.coolspy3.hypixelapi.APIConfig;
import com.coolspy3.util.ModUtil;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.data.type.GameType;
import net.hypixel.api.reply.StatusReply;

public abstract class GameCommand
{

    @SubscribeToPacketStream
    public boolean register(ClientChatSendPacket event)
    {
        if (shouldTrigger(event.msg))
        {
            ModUtil.executeAsync(() -> {
                HypixelAPI api =
                        Utils.reporting((ExceptionSupplier<HypixelAPI>) APIConfig::requireAPI);

                if (api == null) return;

                StatusReply reply = api.getStatus(GameArgs.get().uuid).join();
                StatusReply.Session session = reply.getSession();
                if (session.getServerType() != GameType.SKYBLOCK)
                {
                    if (session.getMap() == null || session.getMode() == null
                            || session.getMode().toLowerCase().equals("lobby"))
                    {
                        ModUtil.sendMessage(MCColor.RED + "You are not in a game!");
                        return;
                    }
                }
                invoke(session, event.msg);
            });

            return true;
        }

        return false;
    }

    public abstract boolean shouldTrigger(String msg);

    public abstract void invoke(StatusReply.Session session, String msg);

}
