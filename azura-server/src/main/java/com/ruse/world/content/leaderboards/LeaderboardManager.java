package com.ruse.world.content.leaderboards;


import com.ruse.util.Misc;
import com.ruse.world.content.KillsTracker;
import com.ruse.world.content.collectionlogs.CollectionLogManager;
import com.ruse.world.content.collectionlogs.CollectionLogs;
import com.ruse.world.content.titles.Titles;
import com.ruse.world.entity.impl.player.Player;

import java.util.*;

public class LeaderboardManager {

    private final Player player;

    private LeaderboardData.LeaderboardType leaderboardType;
    private LeaderboardData leaderboardViewing;

    public LeaderboardManager(Player player) {
        this.player = player;
    }


    /**
     * Handles button functions
     *
     * @param buttonId
     * @return
     */
    public boolean handleButton(final int buttonId) {
        if (buttonId >= 154005 && buttonId <= 154007) {
            int index = (buttonId - 154005) / 2;
            if (index < LeaderboardData.LeaderboardType.values().length) {
                leaderboardType = LeaderboardData.LeaderboardType.values()[index];
                leaderboardViewing = LeaderboardData.getItems(leaderboardType).get(0);
                openInterface();
            }
            return true;
        } else if (buttonId >= 154151 && buttonId <= 154190) {
            int index = buttonId - 154151;
            if (index < viewingBoards.size()) {
                leaderboardViewing = viewingBoards.get(index);
                setupList();
                loadInfo();
            }
            return true;
        }
        return false;
    }

    /**
     * Initializes the interface
     */
    public void openInterface() {
        setupList();
        loadInfo();
        player.getPacketSender().sendInterface(154000);
    }


    /**
     * Sets up the list of titles on the interface
     */
    private ArrayList<LeaderboardData> viewingBoards;

    private void setupList() {
        if (leaderboardViewing == null)
            leaderboardViewing = LeaderboardData.NPC_KILLCOUNT;
        if (leaderboardType == null)
            leaderboardType = LeaderboardData.LeaderboardType.MAIN;

        viewingBoards = LeaderboardData.getItems(leaderboardType);

        int length = viewingBoards.size();
        int id = 154151;

        int scroll = (length * 18);
        if (scroll <= 195)
            scroll = 195;

        for (int i = 0; i < (Math.max(length, 11)); i++) {
            if (viewingBoards.size() > i) {
                player.getPacketSender().sendString(id++, (leaderboardViewing == viewingBoards.get(i) ? "@whi@" : "")
                        + viewingBoards.get(i).getName().replace("##", ""));
            } else {
                player.getPacketSender().sendString(id++, "");
            }
        }

        player.getPacketSender().sendConfig(4511, leaderboardType.ordinal());
        player.getPacketSender().setScrollBar(154100, scroll);
    }


    private void loadInfo() {
        int index = 0;

        if (Leaderboard.DATA.containsKey(leaderboardViewing)) {

            if (Leaderboard.DATA.get(leaderboardViewing).containsKey(player.getUsername())) {
                long entry = Leaderboard.DATA.get(leaderboardViewing).get(player.getUsername());
                if (leaderboardViewing == LeaderboardData.DONATED)
                    player.getPacketSender().sendString(154014, "Personal: $" + Misc.insertCommasToNumber(entry));
                else if (leaderboardViewing == LeaderboardData.TIME_PLAYED)
                    player.getPacketSender().sendString(154014, "Personal: " + Misc.getHoursPlayed(entry));
                else
                    player.getPacketSender().sendString(154014, "Personal: " + Misc.insertCommasToNumber(entry));
            }else{
                player.getPacketSender().sendString(154014, "Personal: " + 0);
            }


            List<Map.Entry<String, Long>> result = sortEntries(Leaderboard.DATA.get(leaderboardViewing));
            for (Iterator<Map.Entry<String, Long>> iterator = result.iterator(); iterator.hasNext(); ) {
                Map.Entry<String, Long> entry = iterator.next();
                String user = entry.getKey();
                Long entryValue = entry.getValue();
                if (index >= 50) {
                    break;
                }
                player.getPacketSender().sendString(154551 + (index * 3), "#" + (index + 1));
                if (leaderboardViewing == LeaderboardData.DONATED)
                    player.getPacketSender().sendString(154552 + (index * 3), "$" + Misc.insertCommasToNumber(entryValue));
                else if (leaderboardViewing == LeaderboardData.TIME_PLAYED)
                    player.getPacketSender().sendString(154552 + (index * 3), "" + Misc.getHoursPlayed(entryValue));
                else
                    player.getPacketSender().sendString(154552 + (index * 3), "" + Misc.insertCommasToNumber(entryValue));
                player.getPacketSender().sendString(154553 + (index * 3), user);
                index++;

                iterator.remove();
            }
        }
        if (index < 50) {
            int remaining = 50 - index;
            for (int i = 0; i < remaining; i++) {
                player.getPacketSender().sendString(154551 + (index * 3), "");
                player.getPacketSender().sendString(154552 + (index * 3), "");
                player.getPacketSender().sendString(154553 + (index * 3), "");
                index++;
            }
        }
    }


    public void updateData() {

        for (LeaderboardData data : LeaderboardData.values()) {
            if (data != null && data.getNpcId() > 0) {
                if (KillsTracker.getTotalKillsForNpc(data.getNpcId(), player) > 0) {
                    Leaderboard.DATA.get(LeaderboardData.forNpcId(data.getNpcId())).put(player.getUsername(), (long) KillsTracker.getTotalKillsForNpc(data.getNpcId(), player));
                }
            }
        }
        Leaderboard.DATA.get(LeaderboardData.NPC_KILLCOUNT).put(player.getUsername(), (long) KillsTracker.getTotalKills(player));

        long total = player.getTotalPlayTime();
        if (total >= 1_000_000_000_000L)
            total = 9_000_000_000L;
        Leaderboard.DATA.get(LeaderboardData.TIME_PLAYED).put(player.getUsername(), total);


        Leaderboard.DATA.get(LeaderboardData.DONATED).put(player.getUsername(), (long) player.getAmountDonated());

        for (CollectionLogManager.LogProgress log : player.getCollectionLogManager().getLogProgress()) {
            if (log != null) {
                if (log.getName().equalsIgnoreCase("TREASURE_HUNTER")) {
                    Leaderboard.DATA.get(LeaderboardData.TREASURE_HUNTER).put(player.getUsername(), (long) log.getCompleted());
                }
            }
        }

        Leaderboard.DATA.get(LeaderboardData.COLLECTION_LOGS_COMPLETED).put(player.getUsername(), (long) player.getCollectionLogManager().totalCompleted());

        Leaderboard.DATA.get(LeaderboardData.DAILY_TASKS_COMPLETED).put(player.getUsername(), (long) player.getDailyTasksCompleted());

        Leaderboard.DATA.get(LeaderboardData.TITLES_UNLOCKED).put(player.getUsername(), (long) player.getTitlesManager().getObtainedTitles().size());

        Leaderboard.DATA.get(LeaderboardData.EASY_SLAYER_TASKS).put(player.getUsername(), (long) player.getSlayer().getEasyTaskKC());
        Leaderboard.DATA.get(LeaderboardData.MED_SLAYER_TASKS).put(player.getUsername(), (long) player.getSlayer().getMedTaskKC());
        Leaderboard.DATA.get(LeaderboardData.HARD_SLAYER_TASKS).put(player.getUsername(), (long) player.getSlayer().getHardTaskKC());
        Leaderboard.DATA.get(LeaderboardData.BOSS_SLAYER_TASKS).put(player.getUsername(), (long) player.getSlayer().getBossTaskKC());
        Leaderboard.DATA.get(LeaderboardData.SANCTUM_OF_DEATH).put(player.getUsername(), (long) player.getDeathSanctumKC());


    }

    static <K, V extends Comparable<? super V>> List<Map.Entry<K, V>> sortEntries(Map<K, V> map) {

        List<Map.Entry<K, V>> sortedEntries = new ArrayList<Map.Entry<K, V>>(map.entrySet());

        Collections.sort(sortedEntries, (e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        return sortedEntries;

    }

}
