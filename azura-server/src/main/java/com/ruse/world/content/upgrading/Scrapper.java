package com.ruse.world.content.upgrading;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import com.ruse.model.Item;
import lombok.Getter;

import java.util.ArrayList;
import com.ruse.model.PlayerRights;
import com.ruse.model.Skill;
import com.ruse.model.container.impl.Bank;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.PlayerLogs;
import com.ruse.world.content.skill.impl.summoning.BossPets;
import com.ruse.world.entity.impl.player.Player;
import mysql.impl.Donation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import static com.ruse.world.content.upgrading.Upgradeables.UpgradeType.*;


public enum Scrapper {


    IDD6(new Item(1042, 1), new Item(22016, 100)),

    ;
    private Item required, scrap;

    Scrapper(Item required, Item scrap) {
        this.required = required;
        this.scrap = scrap;
    }

    private Player player;
    private Upgradeables selectedUpgrade;
    private ArrayList<Upgradeables> upgradeablesArrayList;

    public void handleScraps(Player player) {

        if (!player.getClickDelay().elapsed(1200)) {
            player.getPacketSender().sendMessage("<shad=1>@red@Please wait a few seconds before trying to scrapping items again.");
            return;
        }
        player.getClickDelay().reset();

       {
            player.getPacketSender().sendMessage("You throw your items into the scrapper...");
            player.getPacketSender().sendMessage("You pull pick up some scraps from the bin!");
            player.getInventory().add(scrap);
        }
    }
}





