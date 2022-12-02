package com.ruse.world.content;

import com.ruse.model.GameObject;
import com.ruse.model.Item;
import com.ruse.model.Position;
import com.ruse.model.definitions.NpcDefinition;
import com.ruse.util.Misc;
import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.World;
import com.ruse.world.content.casketopening.Box;
import com.ruse.world.content.titles.Titles;
import com.ruse.world.content.transportation.TeleportHandler;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class AssassinsGuild {

    private Player player;

    @Getter
    @Setter
    private int tier = 0;

    private int openTier = 0;

    public AssassinsGuild(Player player) {
        this.player = player;
    }

    public static String[] information = new String[]{
            "The Guild is a completion minigame",
            "There is three tiers of the Guild",
            "You need requirements for each tier",
            "Completing each tier provides rewards",
            "Completing Tier 3 provides a costume",
    };

    public boolean teleport() {
        if (tier == 0) {
            openStarterInterface();
            return false;
        }
        player.sendMessage("You are a Tier " + tier + " Assassin.");
        return true;
    }

    public void handleDoors(GameObject gameObject) {
        if (gameObject.getPosition().getX() == 2576 || gameObject.getPosition().getX() == 2589
                || gameObject.getPosition().getX() == 2602) {
            int index = (gameObject.getPosition().getX() - 2576) / 13;
            if (tier > index) {
                if (player.getPosition().getY() <= gameObject.getPosition().getY()) {
                    player.moveTo(new Position(gameObject.getPosition().getX(), gameObject.getPosition().getY() + 1, gameObject.getPosition().getZ()));
                } else {
                    player.moveTo(new Position(gameObject.getPosition().getX(), gameObject.getPosition().getY(), gameObject.getPosition().getZ()));
                }
            } else {
                player.sendMessage("You must be a Tier " + (index + 1) + " Assassin to do this.");
            }
        }
    }

    public void openStarterInterface() {

        for (int i = 0; i < 5; i++) {
            player.getPacketSender().sendString(145509 + i, information[i]);
        }

        player.getPacketSender().sendString(145514, (KillsTracker.getTotalKillsForNpc(7134, player) >= 50000 ? "@gre@" : "@red@") +
                "50k Bork KC");
        player.getPacketSender().sendString(145515, "@red@Sacrifice 3 Light weapons");
        player.getPacketSender().sendString(145516, "");

        player.getPacketSender().sendInterface(145500);
    }

    public void openMainInterface() {
        if (openTier <= 1)
            openTier = 1;

        if (tier >= 2)
            player.getPacketSender().sendSpriteChange(145015, 1563);
        else
            player.getPacketSender().sendSpriteChange(145015, 1596);

        if (tier >= 3)
            player.getPacketSender().sendSpriteChange(145016, 1563);
        else
            player.getPacketSender().sendSpriteChange(145016, 1596);


        String[] strings = openTier == 1 ? tier1Info : openTier == 2 ? tier2Info : tier3Info;
        player.getPacketSender().sendString(145017, strings[0]);
        player.getPacketSender().sendString(145018, "");
        player.getPacketSender().sendString(145019, "Requirements for next tier:");
        player.getPacketSender().sendString(145020, (KillsTracker.getTotalKillsForNpc(Integer.parseInt(strings[1]), player) >= Integer.parseInt(strings[2]) ? "@gre@- " : "@red@- ") +
                Misc.insertCommasToNumber(Integer.parseInt(strings[2])) + " " + NpcDefinition.forId(Integer.parseInt(strings[1])).getName() + " KC");
        player.getPacketSender().sendString(145021, (tier > openTier ? "@gre@- " : "@red@- ") + strings[3]);


        player.getPacketSender().sendString(145022, strings[4]);
        player.getPacketSender().sendString(145023, "");

        player.getPacketSender().sendConfig(4511, openTier - 1);

        player.getPacketSender().sendInterface(145000);
    }



    public void openCostumeInterface() {
        player.getPacketSender().sendNpcOnInterface(145610, 10007, 600);
        player.getPacketSender().sendNpcOnInterface(145611, 10008, 600);
        player.getPacketSender().sendNpcOnInterface(145612, 10009, 600);

        player.getPacketSender().sendInterface(145600);
    }


    public static String[] tier1Info = new String[]{
            "Next Tier: @whi@2",
            "10007",
            "50000",
            "Sacrifice 2 Dark Weapons",
            "Assassin Claws",
    };
    public static String[] tier2Info = new String[]{
            "Next Tier: @whi@3",
            "10008",
            "100000",
            "Sacrifice a Blood Weapon",
            "Gemstone kaseki",
    };
    public static String[] tier3Info = new String[]{
            "Next Tier: @whi@Assassin Master",
            "10009",
            "150000",
            "Sacrifice 250m Upgrade Tokens",
            "Assassin Costume",
    };


    public void handleButton(int id) {

        switch (id) {
            case 145606:
                if (player.getInventory().contains(23707)){
                    player.getInventory().delete(23707, 1);
                    player.getInventory().add(23699, 1);
                    World.sendMessage("<col=070000><img=856>[Assassins Guild]<img=856><col=1a36b1>" + player.getUsername() + " has claimed the Faceless Assassin Costume!");
                }
                break;
            case 145607:
                if (player.getInventory().contains(23707)){
                    player.getInventory().delete(23707, 1);
                    player.getInventory().add(23701, 1);
                    World.sendMessage("<col=070000><img=856>[Assassins Guild]<img=856><col=1a36b1>" + player.getUsername() + " has claimed the Death Lotus Costume!");
                }
                break;
            case 145608:
                if (player.getInventory().contains(23707)){
                    player.getInventory().delete(23707, 1);
                    player.getInventory().add(23703, 1);
                    World.sendMessage("<col=070000><img=856>[Assassins Guild]<img=856><col=1a36b1>" + player.getUsername() + " has claimed the Shadow Hunter Costume!");
                }
                break;
            case 145009:
                openTier = 1;
                openMainInterface();
                break;
            case 145011:
                if (tier >= 2) {
                    openTier = 2;
                } else {
                    player.sendMessage("You need to be a Tier 2 Assassin to view this.");
                }
                openMainInterface();
                break;
            case 145013:
                if (tier >= 3) {
                    openTier = 3;
                } else {
                    player.sendMessage("You need to be a Tier 3 Assassin to view this.");
                }
                openMainInterface();
                break;
            case 145006:
                if (openTier == 1 && tier <= 1) {
                    if (KillsTracker.getTotalKillsForNpc(10007, player) >= 50000) {
                        int total = player.getInventory().getAmount(22113)
                                + player.getInventory().getAmount(22114) +
                                player.getInventory().getAmount(22115);
                        if (total >= 2) {
                            int took = 0;
                            for (Item item : player.getInventory().getItems()) {
                                if (item.getId() == 22113 || item.getId() == 22114 || item.getId() == 22115) {
                                    if (took < 2) {
                                        player.getInventory().delete(item);
                                        took += 1;
                                    }
                                }
                            }
                            if (took >= 2) {
                                tier = 2;
                                player.getInventory().add(23696, 1);
                                World.sendMessage("<col=070000><img=856>[Assassins Guild]<img=856><col=1a36b1>" + player.getUsername() + " is now a Tier 2 Assassin!");


                                DiscordMessager.sendWebhook(player.getUsername() + " - tier " + tier, Color.CYAN,
                                        "https://discord.com/api/webhooks/959301658939121704/lbuXqGJHSCQkc2F53hcYeyGXJjME8f0PpbrPoc2-1xbtrz1YBAZhs7IOTYVUNoWfkGP0");
                            }
                        } else {
                            player.sendMessage("You need at least two Dark weapons to do this.");
                        }
                    } else {
                        player.sendMessage("You need 50k Faceless Assassin kills before sacrificing the two Dark weapons.");
                    }
                } else if (openTier == 2 && tier <= 2) {
                    if (KillsTracker.getTotalKillsForNpc(10008, player) >= 100000) {
                        int total = player.getInventory().getAmount(23220)
                                + player.getInventory().getAmount(23221) +
                                player.getInventory().getAmount(23222);
                        if (total >= 1) {
                            int took = 0;
                            for (Item item : player.getInventory().getItems()) {
                                if (item.getId() == 23220 || item.getId() == 23221 || item.getId() == 23222) {
                                    if (took < 1) {
                                        player.getInventory().delete(item);
                                        took += 1;
                                    }
                                }
                            }
                            if (took >= 1) {
                                tier = 3;

                                player.getInventory().add(23706, 1);
                                World.sendMessage("<col=070000><img=856>[Assassins Guild]<img=856><col=1a36b1>" + player.getUsername() + " is now a Tier 3 Assassin!");


                                DiscordMessager.sendWebhook(player.getUsername() + " - tier " + tier, Color.CYAN,
                                        "https://discord.com/api/webhooks/959301658939121704/lbuXqGJHSCQkc2F53hcYeyGXJjME8f0PpbrPoc2-1xbtrz1YBAZhs7IOTYVUNoWfkGP0");
                            }
                        } else {
                            player.sendMessage("You need at least one Blood weapon to do this.");
                        }
                    } else {
                        player.sendMessage("You need 100k Lotus Warrior kills before sacrificing a Blood weapon.");
                    }
                } else if (openTier == 3 && tier <= 3) {
                    if (KillsTracker.getTotalKillsForNpc(10009, player) >= 150000) {
                        if (player.getInventory().getAmount(12855) >= 250_000_000) {
                            player.getInventory().delete(12855, 250_000_000);
                            tier = 4;
                            player.getInventory().add(23707, 1);
                            World.sendMessage("<col=070000><img=856>[Assassins Guild]<img=856><col=1a36b1>" + player.getUsername() + " is now an Assassin Master!");
                            player.getTitlesManager().unlock(Titles.ASSASSIN);

                            DiscordMessager.sendWebhook(player.getUsername() + " - tier " + tier, Color.CYAN,
                                    "https://discord.com/api/webhooks/959301658939121704/lbuXqGJHSCQkc2F53hcYeyGXJjME8f0PpbrPoc2-1xbtrz1YBAZhs7IOTYVUNoWfkGP0");
                        } else {
                            player.sendMessage("You need at least 250M Upgrade Tokens to do this.");
                        }
                    } else {
                        player.sendMessage("You need 150k Shadow Hunter kills before sacrificing 250M Upgrade Tokens.");
                    }
                } else {
                    player.sendMessage("You already completed this Tier.");
                }
                break;


            case 145506:
                if (tier == 0) {
                    if (KillsTracker.getTotalKillsForNpc(7134, player) >= 50000) {
                        int total = player.getInventory().getAmount(5011)
                                + player.getInventory().getAmount(12537) +
                                player.getInventory().getAmount(17013);
                        if (total >= 3) {
                            int took = 0;
                            for (Item item : player.getInventory().getItems()) {
                                if (item.getId() == 5011 || item.getId() == 12537 || item.getId() == 17013) {
                                    if (took < 3) {
                                        player.getInventory().delete(item);
                                        took += 1;
                                    }
                                }
                            }
                            if (took >= 3) {
                                tier = 1;
                                World.sendMessage("<col=070000><img=856>[Assassins Guild]<img=856><col=1a36b1>" + player.getUsername() + " is now a Tier 1 Assassin!");
                                TeleportHandler.teleportPlayer(player, new Position(2589, 5269, 1), player.getSpellbook().getTeleportType());

                                DiscordMessager.sendWebhook(player.getUsername() + " - tier " + tier, Color.CYAN,
                                        "https://discord.com/api/webhooks/959301658939121704/lbuXqGJHSCQkc2F53hcYeyGXJjME8f0PpbrPoc2-1xbtrz1YBAZhs7IOTYVUNoWfkGP0");
                            }
                        } else {
                            player.sendMessage("You need at least three Light weapons to do this.");
                        }
                    } else {
                        player.sendMessage("You need 50k Bork kills before sacrificing the Light weapons.");
                    }
                }
                break;
        }
    }

    public static Box[] loot = { //
            new Box(23696, 1, 1),
            new Box(23706, 1, 1),
            new Box(23707, 1, 1),
    };

}
