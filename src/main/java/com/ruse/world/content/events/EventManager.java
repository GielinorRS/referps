package com.ruse.world.content.events;

import com.ruse.model.GameObject;
import com.ruse.world.entity.impl.player.Player;

public class EventManager {

    public enum Events {
        PARTY_CHEST,
        TILES
    }


    public static Events CURRENT_EVENT = null;


    public static void buttonClick(Player player, int id) {
        switch (id) {
            case 146010:
                PartyChest.addItems(player);
                break;
        }
    }

    public static void objectClick(Player player, GameObject gameObject) {
        if (gameObject.getId() >= 115 && gameObject.getId() <= 122){
            PartyChest.popBalloon(player, gameObject);
        }
        switch (gameObject.getId()) {
            case 26744:
                if (CURRENT_EVENT == Events.PARTY_CHEST)
                    PartyChest.openInterface(player);
                else
                    player.sendMessage("This chest is not active at the moment.");
                break;
        }
    }

    public static void itemContainerClick(Player player, int interfaceId) {
        if (interfaceId >= 14939 && interfaceId <= 14947) {
            PartyChest.removeItem(player, interfaceId - 14939);
        }
    }


    public static void locationProcess(Player player) {
        if (CURRENT_EVENT == Events.PARTY_CHEST)
            PartyChest.locationProcess(player);
    }

}
