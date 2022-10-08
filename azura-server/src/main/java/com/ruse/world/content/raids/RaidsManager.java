package com.ruse.world.content.raids;


import com.ruse.model.GameObject;
import com.ruse.model.Locations.Location;
import com.ruse.model.Skill;
import com.ruse.model.input.impl.InviteRaidsPlayer;
import com.ruse.world.content.chambersOfAnima.ChambersOfAnimaData;
import com.ruse.world.content.chambersOfAnima.ChambersOfAnimaParty;
import com.ruse.world.content.deathsanctum.DeathSanctumData;
import com.ruse.world.content.deathsanctum.DeathSanctumParty;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.content.dialogue.DialogueOptions;
import com.ruse.world.content.raids.impl.gods.GodsInterfaces;
import com.ruse.world.content.raids.impl.gods.GodsRaids;
import com.ruse.world.content.raids.impl.gods.GodsRaidsData;
import com.ruse.world.content.raids.impl.gods.GodsRaidsParty;
import com.ruse.world.content.zombie.ZombieParty;
import com.ruse.world.content.zombie.ZombieRaidData;
import com.ruse.world.entity.impl.player.Player;

/**
 * Handles basic functionalities for all parties for easy use.
 */
public class RaidsManager {

    /**
     * Handles the decision of whether or not an invited player will join a party.
     * Every new raid added to the game using this Party system
     * should have it's handleInviteDialogueAction method called here.
     *
     * @param player   The player that has been invited to the party.
     * @param accepted Whether the player accepted the invite or not.
     */
    public static void handleInviteDialogueOptions(Player player, boolean accepted) {
        GodsRaidsParty.handleInviteDialogueAction(player, accepted);
    }


    public static void openInterface(Player player) {
        /*if (player.getLocation() == Location.HOPE_LOBBY)
            SirenicRaidsParty.openInterface();*/
    }


    /**
     * Handles the object interactions of any object regarding a raid.
     * Every new raid added to the game using this Party system
     * should have it's handleObjectInteraction method called here.
     */
    public static boolean handleFirstClickObjectInteraction(Player player, GameObject gameObject) {
        switch (gameObject.getId()) {
            case 42220:
                GodsRaids.portalInteraction(player, gameObject);
                return true;
            case 52299:
                GodsInterfaces.openCoffer(player);
                return true;
            default:
                return false;
        }

    }

    public static boolean handleSecondClickObjectInteraction(Player player, GameObject gameObject) {
        switch (gameObject.getId()) {
            case 23609:
                GodsRaids.portalInteraction(player, gameObject);
                return true;
            default:
                return false;
        }

    }

    /**
     * Handles the dialogue interactions.
     * Every new raid added to the game using this Party system
     * should have it's handleDailogueOptions method called here.
     */
    public static boolean handleDialogueOptions(Player player, int actionId, int optionId) {
        if (optionId == DialogueOptions.FIRST_OPTION_OF_TWO) {
            switch (actionId) {
                case 8809:
                    if (inGodsRaids(player)) {
                        GodsRaidsParty.handleEntranceDialogue(player);
                    }
                    return true;
                case 7300:
                    player.setDialogueActionId(7301);
                    DialogueManager.start(player, 7301);
                    return true;
            }
        }
        if (optionId == DialogueOptions.SECOND_OPTION_OF_TWO) {
            switch (actionId) {
                case 8808:
                case 8809:
                case 8810:
                    player.getPacketSender().sendInterfaceRemoval();
                    return true;
            }
        }

        if (optionId == DialogueOptions.FIRST_OPTION_OF_THREE) {
            switch (actionId) {
                case 7301:
                    player.getPacketSender().sendInterfaceRemoval();
                    return true;
            }
        }
        if (optionId == DialogueOptions.SECOND_OPTION_OF_THREE) {
            switch (actionId) {
                case 7301:
                    player.getPacketSender().sendInterfaceRemoval();
                    return true;
            }
        }
        if (optionId == DialogueOptions.THIRD_OPTION_OF_THREE) {
            switch (actionId) {
                case 7301:
                    player.getPacketSender().sendInterfaceRemoval();
                    return true;
            }
        }

        return false;

    }

    public static boolean isMovingFromRaidLocation(Location prev, Location newLocation) {
        return (prev == Location.GODS_LOBBY && newLocation == Location.ISLE_GODS)
                || (prev == Location.ISLE_GODS && newLocation == Location.GODS_LOBBY);
    }

    public static boolean inGodsRaids(Player player) {
        return player.getLocation() == Location.ISLE_GODS;
    }

    public static boolean handleButton(Player player, int id) {

        //if (id >= 144000  && id <= 144020){
        GodsInterfaces.handleButton(player,id);
        //return true;
        //}

        switch (id) {
            case 111703:
                createOrInviteButton(player);
                return true;

            case 111706:
                leaveButton(player);
                return true;

            case 111716:
            case 111719:
            case 111722:
            case 111725:
            case 111728:
            case 111731:
            case 111734:
            case 111737:
            case 111740:
            case 111743:
            case 111746:
            case 111749:
                if (inRaidParty(player)) {
                    if (inGodsRaids(player))
                        player.getGodsRaidsParty().kickPlayerButton(player, id);
                } else if (player.getLocation() == Location.ZOMBIE || player.getLocation() == Location.ZOMBIE_LOBBY) {
                    if (player.getZombieParty() != null) {
                        if (player.equals(player.getZombieParty().getOwner())) {
                            if (player.getZombieParty().getPlayers()
                                    .size() >= ((id - 111716) / 3) + 1) {
                                Player playerToKick = player.getZombieParty()
                                        .getPlayers().get((id - 111716) / 3);
                                if (playerToKick == player) {
                                    player.sendMessage("You cannot kick yourself!");
                                } else {
                                    player.getZombieParty().remove(playerToKick,
                                            true);

                                }
                            }
                        } else {
                            player.sendMessage("Only the leader of the party can kick players!");
                        }
                    }
                } else if (player.getLocation() == Location.DEATH_SANCTUM || player.getLocation() == Location.DEATH_SANCTUM_LOBBY) {
                    if (player.getSanctumOfDeathParty() != null) {
                        if (player.equals(player.getSanctumOfDeathParty().getOwner())) {
                            if (player.getSanctumOfDeathParty().getPlayers()
                                    .size() >= ((id - 111716) / 3) + 1) {
                                Player playerToKick = player.getSanctumOfDeathParty()
                                        .getPlayers().get((id - 111716) / 3);
                                if (playerToKick == player) {
                                    player.sendMessage("You cannot kick yourself!");
                                } else {
                                    player.getSanctumOfDeathParty().remove(playerToKick,
                                            true);
                                }
                            }
                        } else {
                            player.sendMessage("Only the leader of the party can kick players!");
                        }
                    }
                } else if (player.getLocation() == Location.ANIMA_CHAMBERS_LOBBY || player.getLocation() == Location.ANIMA_CHAMBERS) {
                    if (player.getChambersOfAnimaParty() != null) {
                        if (player.equals(player.getChambersOfAnimaParty().getOwner())) {
                            if (player.getChambersOfAnimaParty().getPlayers()
                                    .size() >= ((id - 111716) / 3) + 1) {
                                Player playerToKick = player.getChambersOfAnimaParty()
                                        .getPlayers().get((id - 111716) / 3);
                                if (playerToKick == player) {
                                    player.sendMessage("You cannot kick yourself!");
                                } else {
                                    player.getChambersOfAnimaParty().remove(playerToKick,
                                            true);
                                }
                            }
                        } else {
                            player.sendMessage("Only the leader of the party can kick players!");
                        }
                    }
                } else {
                    player.sendMessage("You must be in a raid to do this.");
                }
                return true;
        }

        return false;

    }

    /**
     * Handles the action of pressing the "Leave" button in the raids party interface.
     *
     * @param player
     */
    public static void leaveButton(Player player) {
        if (player.getGodsRaidsParty() != null) {
            if (player.getGodsRaidsParty() != null) {
                player.getGodsRaidsParty().remove(player);
                player.sendMessage("You left your Bossing party.");
            }
            player.moveTo(GodsRaidsData.LOBBY_POSITION);
        } else if (player.getLocation() == Location.ZOMBIE) {
            if (player.getZombieParty() != null) {
                player.getZombieParty().remove(player, true);
                player.sendMessage("You left your Raids party.");
            }
            player.moveTo(ZombieRaidData.lobbyPosition);
        } else if (player.getLocation() == Location.ZOMBIE_LOBBY) {
            if (player.getZombieParty() != null) {
                player.getZombieParty().remove(player, true);
                player.sendMessage("You left your Raids party.");
            }
        } else if (player.getLocation() == Location.DEATH_SANCTUM) {
            if (player.getSanctumOfDeathParty() != null) {
                player.getSanctumOfDeathParty().remove(player, true);
                player.sendMessage("You left your Raids party.");
            }
            player.moveTo(DeathSanctumData.lobbyPosition);
        } else if (player.getLocation() == Location.DEATH_SANCTUM_LOBBY) {
            if (player.getSanctumOfDeathParty() != null) {
                player.getSanctumOfDeathParty().remove(player, true);
                player.sendMessage("You left your Raids party.");
            }
        } else if (player.getLocation() == Location.ANIMA_CHAMBERS) {
            if (player.getChambersOfAnimaParty() != null) {
                player.getChambersOfAnimaParty().remove(player, true);
                player.sendMessage("You left your Raids party.");
            }
            player.moveTo(ChambersOfAnimaData.lobbyPosition);
        } else if (player.getLocation() == Location.ANIMA_CHAMBERS_LOBBY) {
            if (player.getChambersOfAnimaParty() != null) {
                player.getChambersOfAnimaParty().remove(player, true);
                player.sendMessage("You left your Raids party.");
            }
        } else {
            player.sendMessage("You must be in a raid to do this.");
        }
    }

    /**
     * Handles the action of pressing the "invite/create" button on the raids party interface.
     * Every new raid added to the game using this Party system
     * should have it's createOrInviteButton method called here.
     *
     * @param player Player that pressed the button.
     */
    public static void createOrInviteButton(Player player) {
        if (player.getLocation() == Location.GODS_LOBBY
                ||player.getLocation() == Location.ISLE_GODS) {
            GodsRaidsParty.createOrInviteButton(player);
        }else        if (player.getLocation() == Location.ZOMBIE_LOBBY) {
            if (player.getSkillManager().getMaxLevel(Skill.SLAYER) < 98 && player.getSkillManager().getMaxLevel(Skill.SLAYER) < 79) {
                player.getPacketSender().sendMessage("<shad=1>@or2@You must be 99+ Slayer to do Island Raids.");
                player.getPacketSender().sendMessage("You must be 80+ Invention to do Island Raids.");
                return;
            }

            if (player.getZombieParty() != null) {
                if (player.getZombieParty().getOwner() != player) {
                    player.getPacketSender().sendMessage("Only the party leader can invite other players.");
                } else {
                    player.setInputHandling(new InviteRaidsPlayer());
                    player.getPacketSender().sendEnterInputPrompt("Invite Player");
                }
            } else {
                new ZombieParty(player).create();
            }
        } else if (player.getLocation() == Location.DEATH_SANCTUM_LOBBY) {
            if (player.getSanctumOfDeathParty() != null) {
                if (player.getSanctumOfDeathParty().getOwner() != player) {
                    player.getPacketSender().sendMessage("Only the party leader can invite other players.");
                } else {
                    player.setInputHandling(new InviteRaidsPlayer());
                    player.getPacketSender().sendEnterInputPrompt("Invite Player");
                }
            } else {
                new DeathSanctumParty(player).create();
            }
        } else if (player.getLocation() == Location.ANIMA_CHAMBERS_LOBBY) {
            if (player.getChambersOfAnimaParty() != null) {
                if (player.getChambersOfAnimaParty().getOwner() != player) {
                    player.getPacketSender().sendMessage("Only the party leader can invite other players.");
                } else {
                    player.setInputHandling(new InviteRaidsPlayer());
                    player.getPacketSender().sendEnterInputPrompt("Invite Player");
                }
            } else {
                new ChambersOfAnimaParty(player).create();
            }
        } else {
            player.sendMessage("You must be in a raid to do this.");
        }
    }


    public static boolean inParty(Player player) {
        return inRaidParty(player);
    }

    public static boolean inRaidParty(Player player) {
        return player.getGodsRaidsParty() != null;
    }

    public static boolean isAllowedSoulsplit(Player player) {
        if (player.getGodsRaidsParty() != null) {
            return true;
        }

        if (inRaidParty(player))
            return true;


        return true;
    }

    public static void leaveActiveParties(Player player) {
        leaveActiveRaidParties(player);


    }

    public static void leaveActiveRaidParties(Player player) {

        if (player.getGodsRaidsParty() != null)
            player.getGodsRaidsParty().leave(player);

    }

    public static boolean handleRightClickInvite(Player player, Player attacked) {


        if (player.getLocation() == Location.GODS_LOBBY && GodsRaidsData.LOBBY_AREA.inside(player.getPosition(), player.getPosition().getZ())) {
            player.setEntityInteraction(attacked);
            if (attacked.getIndex() != player.getIndex()) {
                if (player.getGodsRaidsParty() != null && player.getGodsRaidsParty().getOwner().equals(player)) {
                    player.sendMessage("Sent invite to " + attacked.getUsername());
                    player.getGodsRaidsParty().invite(attacked);
                } else {
                    player.sendMessage("You must be the leader of a party to do this.");
                }
            }
            return true;
        }


        return false;
    }
}
