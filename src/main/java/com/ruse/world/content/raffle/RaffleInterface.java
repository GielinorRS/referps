package com.ruse.world.content.raffle;


import com.ruse.util.Misc;
import com.ruse.world.entity.impl.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class RaffleInterface {
    private final Player player;

    public RaffleInterface(Player player) {
        this.player = player;
    }

    public boolean handleButton(int buttonId) {
        if (buttonId == 147005)
            openInterfacePerks(player);
        if (buttonId == 147007)
            openInterfaceVoting(player);
        if (buttonId == 147009)
            openInterfaceDonations(player);

        return false;
    }

    public static void openInterfacePerks(Player player) {
        player.getPacketSender().sendConfig(4511, 0);

        player.getPacketSender().sendString(147012, "@whi@" +  UniversalRaffle.getTimeTillDraw());
        player.getPacketSender().sendString(147015, "Obtain entry by: \\n@whi@Donating to Server Perks");
        player.getPacketSender().sendString(147016, "Total Entries: @whi@" + Misc.sendCashToString(PerksRaffle.entriesSize));
        player.getPacketSender().sendString(147017, "Players Participating: @whi@" + PerksRaffle.ENTRIES.size());

        player.getPacketSender().sendInterfaceItems(147019, PerksRaffle.REWARDS);

        player.getPacketSender().sendString(147021, "Total Entries: @whi@" +
                (PerksRaffle.ENTRIES.containsKey(player.getUsername()) ? Misc.sendCashToString(PerksRaffle.ENTRIES.get(player.getUsername())) : "0"));
        player.getPacketSender().sendString(147022, "Entry Limit: @whi@250M");

        for (int i = 0 ; i < 5 ; i ++) {
            String name = PerksRaffle.entriesRanked.size() > i ? PerksRaffle.entriesRanked.get(i) + " - " +
                    Misc.formatNumber(PerksRaffle.ENTRIES.get( PerksRaffle.entriesRanked.get(i))) : "N/A";
            player.getPacketSender().sendString(147024 + i, (i + 1) +  ". @whi@" + name );
        }

        player.getPacketSender().sendInterface(147000);
    }


    public static void openInterfaceVoting(Player player) {
        player.getPacketSender().sendConfig(4511, 1);

        player.getPacketSender().sendString(147012, "@whi@" +  UniversalRaffle.getTimeTillDraw());
        player.getPacketSender().sendString(147015, "Obtain entry by: \\n@whi@Voting for Lunite");
        player.getPacketSender().sendString(147016, "Total Entries: @whi@" + Misc.insertCommasToNumber(VotingRaffle.entriesSize));
        player.getPacketSender().sendString(147017, "Players Participating: @whi@" + VotingRaffle.ENTRIES.size());

        player.getPacketSender().sendInterfaceItems(147019, VotingRaffle.REWARDS);

        player.getPacketSender().sendString(147021, "Total Entries: @whi@" +
                (VotingRaffle.ENTRIES.containsKey(player.getUsername()) ? Misc.insertCommasToNumber(VotingRaffle.ENTRIES.get(player.getUsername()))
                        : "0"));
        player.getPacketSender().sendString(147022, "Entry Limit: @whi@56");

        for (int i = 0 ; i < 5 ; i ++) {
            String name = VotingRaffle.entriesRanked.size() > i ? VotingRaffle.entriesRanked.get(i) + " - " +  VotingRaffle.ENTRIES.get( VotingRaffle.entriesRanked.get(i)): "N/A";
            player.getPacketSender().sendString(147024 + i, (i + 1) +  ". @whi@" + name );
        }

        player.getPacketSender().sendInterface(147000);
    }


    public static void openInterfaceDonations(Player player) {
        player.getPacketSender().sendConfig(4511, 2);

        player.getPacketSender().sendString(147012, "@whi@" +  UniversalRaffle.getTimeTillDraw());

        player.getPacketSender().sendString(147015, "Obtain entry by: \\n@whi@Claiming a donation");

        if (player.getUsername().equalsIgnoreCase("test"))
            player.getPacketSender().sendString(147016, "Total Entries: @whi@" + Misc.insertCommasToNumber(DonationRaffle.entriesSize));
        else
            player.getPacketSender().sendString(147016, "Total Entries: @whi@N/A");

        player.getPacketSender().sendString(147017, "Players Participating: @whi@" + DonationRaffle.ENTRIES.size());

        player.getPacketSender().sendInterfaceItems(147019, DonationRaffle.REWARDS);

        player.getPacketSender().sendString(147021, "Total Entries: @whi@$" +
                (DonationRaffle.ENTRIES.containsKey(player.getUsername()) ? Misc.insertCommasToNumber(DonationRaffle.ENTRIES.get(player.getUsername())) : "0"));
        player.getPacketSender().sendString(147022, "Entry Limit: @whi@$1,000");


        for (int i = 0 ; i < 5 ; i ++) {
            String name = DonationRaffle.entriesRanked.size() > i ? DonationRaffle.entriesRanked.get(i) : "N/A";
            if (player.getUsername().equalsIgnoreCase("test"))
                name = DonationRaffle.entriesRanked.size() > i ? DonationRaffle.entriesRanked.get(i) + " - " +  DonationRaffle.ENTRIES.get( DonationRaffle.entriesRanked.get(i)): "N/A";
            player.getPacketSender().sendString(147024 + i, (i + 1) +  ". @whi@" + name);
        }


        player.getPacketSender().sendInterface(147000);
    }


    @AllArgsConstructor
    @Getter
    public enum RaffleType{

        PERKS("Donating to Server Perks"),
        VOTING("Voting on a site"),
        DONATIONS("Claiming a donation");

        private String obtained;

    }


}
