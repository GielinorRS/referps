package com.ruse.world.content.titles;


import com.ruse.model.Flag;
import com.ruse.model.definitions.NpcDefinition;
import com.ruse.util.Colours;
import com.ruse.util.Misc;
import com.ruse.world.content.KillsTracker;
import com.ruse.world.content.collectionlogs.CollectionLogs;
import com.ruse.world.content.skill.SkillManager;
import com.ruse.world.entity.impl.player.Player;

import java.util.ArrayList;

import static com.ruse.world.content.titles.Titles.TitleType.MISC;

public class TitlesManager {

    private final Player player;
    private final ArrayList<Titles> obtainedTitles = new ArrayList<>();
    private ArrayList<Titles> viewingTitles;
    private Titles previewingTitle;
    private Titles.TitleType titleTypeViewing;

    public TitlesManager(Player player) {
        this.player = player;
        if (!obtainedTitles.contains(Titles.NONE))
            obtainedTitles.add(Titles.NONE);
    }

    public ArrayList<Titles> getObtainedTitles() {
        return obtainedTitles;
    }

    /**
     * Handles button functions
     *
     * @param buttonId
     * @return
     */
    public boolean handleButton(final int buttonId) {
        if (buttonId == 152012) {
            reset();
            return true;
        } else if (buttonId == 152017) {
            applyChanges();
            return true;
        } else if (buttonId >= 152005 && buttonId <= 152009) {
            int index = (buttonId - 152005) / 2;
            if (index < Titles.TitleType.values().length) {
                titleTypeViewing = Titles.TitleType.values()[index];
                openInterface();
            }
            return true;
        } else if (buttonId >= 152152 && buttonId <= 152252) {
            int index = (buttonId - 152152) / 2;
            if (index < viewingTitles.size()) {
                previewingTitle = viewingTitles.get(index);
                loadInfo();
            }
            return true;
        } else if (buttonId == 132005) {
            return true;
        }
        return false;
    }

    /**
     * Initializes the interface
     */
    public void openInterface() {
        setupList();
        checkBossTitles();
        checkMiscTitles();
        loadInfo();
        player.getPacketSender().sendInterface(152000);
    }


    /**
     * Sets up the list of titles on the interface
     */
    private void setupList() {
        if (titleTypeViewing == null)
            titleTypeViewing = Titles.TitleType.LOYALTY;

        viewingTitles = Titles.getItems(titleTypeViewing);

        int length = viewingTitles.size();
        int id = 152151;

        int scroll = (length * 18);
        if (scroll <= 155)
            scroll = 155;

        for (int i = 0; i < (Math.max(length, 9)); i++) {
            if (viewingTitles.size() > i) {
                if (obtainedTitles.contains(viewingTitles.get(i))) {
                    player.getPacketSender().sendSpriteChange(id++, 1563);
                    player.getPacketSender().sendString(id++, "" + viewingTitles.get(i).getName().replace("##", ""));
                } else {
                    player.getPacketSender().sendSpriteChange(id++, 1596);
                    player.getPacketSender().sendString(id++, "    " + viewingTitles.get(i).getName().replace("##", ""));
                }
            } else {
                player.getPacketSender().sendSpriteChange(id++, 1563);
                player.getPacketSender().sendString(id++, "");
            }
        }

        player.getPacketSender().sendString(152013, "Total: (" + (obtainedTitles.size() - 1) + "/" + (Titles.values().length - 1) + ")");

        player.getPacketSender().sendConfig(4511, titleTypeViewing.ordinal());
        player.getPacketSender().setScrollBar(152100, scroll);
    }


    private void loadInfo() {
        if (previewingTitle == null)
            previewingTitle = Titles.GOOD;
        if (previewingTitle != null) {
            if (obtainedTitles.contains(previewingTitle)) {
                player.getPacketSender().sendString(152018, "Set Title");
            } else {
                player.getPacketSender().sendString(152018, "Unlock");
            }

            if (previewingTitle.getType() == Titles.TitleType.BOSS) {
                player.getPacketSender().sendString(152019, (player.getCollectionLogManager().isCompleted(CollectionLogs.forNpcId(previewingTitle.getNpcId())) ? "@gre@" : "@red@")
                        + previewingTitle.getRequirements()[0]);
                player.getPacketSender().sendString(152020, (KillsTracker.getTotalKillsForNpc(previewingTitle.getNpcId(), player) >= previewingTitle.getAmount() ? "@gre@" : "@red@")
                        + previewingTitle.getRequirements()[1]);
            } else {
                player.getPacketSender().sendString(152019, (obtainedTitles.contains(previewingTitle)? "@gre@" : "@red@") +  previewingTitle.getRequirements()[0]);
                player.getPacketSender().sendString(152020,(obtainedTitles.contains(previewingTitle)? "@gre@" : "@red@") +  previewingTitle.getRequirements()[1]);
            }
        }
        if (previewingTitle.getName().contains("##"))
            player.getPacketSender().sendString(152021, player.getUsername() + "</col> " + previewingTitle.getName().replace("##", ""));
        else
            player.getPacketSender().sendString(152021, previewingTitle.getName().replace("##", "") + " </col>" + player.getUsername());
    }


    private void applyChanges() {
        if (obtainedTitles.contains(previewingTitle)) {
            setTitle(previewingTitle);
        } else {
            if (previewingTitle.getType() == Titles.TitleType.LOYALTY)
                unlockLoyalty();
            if (previewingTitle.getType() == Titles.TitleType.BOSS) {
                if (!player.getCollectionLogManager().isCompleted(CollectionLogs.forNpcId(previewingTitle.getNpcId()))) {
                    player.sendMessage("@red@You have not completed the " + NpcDefinition.forId(previewingTitle.getNpcId()).getName() + " Collection log.");
                }
                if (KillsTracker.getTotalKillsForNpc(previewingTitle.getNpcId(), player) < previewingTitle.getAmount()) {
                    player.sendMessage("@red@You need x" + previewingTitle.getAmount() + " " + NpcDefinition.forId(previewingTitle.getNpcId()).getName()
                            + " kills for this title. (" + KillsTracker.getTotalKillsForNpc(previewingTitle.getNpcId(), player) + "/" + previewingTitle.getAmount() + ")");
                }
            }
            if (previewingTitle.getType() == Titles.TitleType.MISC) {
                if (previewingTitle == Titles.BILLIONAIRE){
                    if (player.getInventory().getAmount(12855) >= 1000000000){
                        player.sendMessage("You purchase the title for 1b Upgrade tokens.");
                        player.getInventory().delete(12855,1000000000 );
                        unlock(previewingTitle);
                        openInterface();
                    }
                }
                player.sendMessage("@red@You do not have the requirements for this title..");
            }
        }
    }


    private void unlockLoyalty() {
        if (!obtainedTitles.contains(previewingTitle)) {
            if (previewingTitle.cost > 0) {
                if (player.getPointsHandler().getLoyaltyPoints() < previewingTitle.cost) {
                    player.sendMessage("You only have " + player.getPointsHandler().getLoyaltyPoints() + " Loyalty points!");
                    return;
                }
                player.getPointsHandler().setLoyaltyPoints(-previewingTitle.cost, true);
                unlock(previewingTitle);
                openInterface();
            } else {
                player.sendMessage("@red@You do not have this title unlocked!");
            }
        }
    }

    public void checkBossTitles() {
        for (Titles title : Titles.values()) {
            if (title.getType() == Titles.TitleType.BOSS) {
                if (player.getCollectionLogManager().isCompleted(CollectionLogs.forNpcId(title.getNpcId())) &&
                        KillsTracker.getTotalKillsForNpc(title.getNpcId(), player) >= title.getAmount()) {
                    unlock(title);
                }
            }
        }
    }
    public void checkMiscTitles() {
        for (Titles title : Titles.values()) {
            if (title.getType() == Titles.TitleType.MISC) {
                if (checkMiscTitle(title)) {
                    unlock(title);
                }
            }
        }

        if (getObtainedTitles().contains(Titles.GRINDER) && Misc.getHoursPlayedLong(player.getTotalPlayTime()) < 2000){
            getObtainedTitles().remove(Titles.GRINDER);
        }
        if (getObtainedTitles().contains(Titles.NOLIFE) && Misc.getHoursPlayedLong(player.getTotalPlayTime()) < 5000){
            getObtainedTitles().remove(Titles.NOLIFE);
        }
    }

    private boolean checkMiscTitle(Titles title) {
        if (title == Titles.MAXED) {
            if (SkillManager.maxed(player))
                return true;
        }
        if (title == Titles.DEVOURER) {
            if (KillsTracker.getTotalKills(player) >= 2500000)
                return true;
        }
        if (title == Titles.GRINDER) {
            if (Misc.getHoursPlayedLong(player.getTotalPlayTime()) >= 2000)
                return true;
        }
        if (title == Titles.NOLIFE) {
            if (Misc.getHoursPlayedLong(player.getTotalPlayTime()) >= 5000)
                return true;
        }
        if (title == Titles.ASSASSIN) {
            if (player.getAssassinsGuild().getTier() >= 4)
                return true;
        }
        if (title == Titles.CELESTIAL) {
            if (player.isCelestial())
                return true;
        }
        if (title == Titles.SELFLESS) {
            if (player.getServerPerksContributions() >= 250000000)
                return true;
        }
        return obtainedTitles.contains(title);
    }

    public void setTitle(Titles title) {
        player.setPlayerTitle(title);
        player.setTitle(Colours.convertColours(title.getName()));
        player.getUpdateFlag().flag(Flag.APPEARANCE);
        player.sendMessage("You have set your title: " + title.getName().replace("##", ""));
    }

    public void unlock(Titles title) {
        if (!obtainedTitles.contains(title)) {
            obtainedTitles.add(title);
            player.sendMessage("You have just unlocked the title: " + title.getName().replace("##", ""));
        }
    }

    public void remove(Titles title) {
        obtainedTitles.remove(title);
    }

    public void reset() {
        player.setTitle("");
        player.setPlayerTitle(Titles.NONE);
        player.getUpdateFlag().flag(Flag.APPEARANCE);
        player.sendMessage("@red@You have reset your title.");
    }

}
