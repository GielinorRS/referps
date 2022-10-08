package mysql.impl;

import com.ruse.GameSettings;
import com.ruse.model.Item;
import com.ruse.model.PlayerRights;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.util.Misc;
import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.World;
import com.ruse.world.content.DonoDeal;
import com.ruse.world.content.PlayerLogs;
import com.ruse.world.content.PlayerPanel;
import com.ruse.world.content.raffle.DonationRaffle;
import com.ruse.world.content.raffle.VotingRaffle;
import com.ruse.world.entity.impl.player.Player;

import java.sql.*;

public class Donation implements Runnable {

    public static final String HOST = "104.161.43.58";
    public static final String USER = "pilfirgk_store";
    public static final String PASS = "LOVj,6(wlM]k";
    public static final String DATABASE = "pilfirgk_store";

    private Player player;
    private Connection conn;
    private Statement stmt;

    /**
     * The constructor
     *
     * @param player
     */
    public Donation(Player player) {
        this.player = player;
    }

    public static void checkForRankUpdate(Player player) {
        if (player.getRights().isStaff() || player.getRights() == PlayerRights.YOUTUBER) {
            return;
        }
        PlayerRights rights = null;
        if (player.getAmountDonated() >= 10)
            rights = PlayerRights.SAPPHIRE_DONATOR;
        if (player.getAmountDonated() >= 50)
            rights = PlayerRights.EMERALD_DONATOR;
        if (player.getAmountDonated() >= 250)
            rights = PlayerRights.RUBY_DONATOR;
        if (player.getAmountDonated() >= 500)
            rights = PlayerRights.DIAMOND_DONATOR;
        if (player.getAmountDonated() >= 1000)
            rights = PlayerRights.ONYX_DONATOR;
        if (player.getAmountDonated() >= 5000)
            rights = PlayerRights.ZENYTE_DONATOR;
        if (player.getAmountDonated() >= 10000)
            rights = PlayerRights.TANZANITE_DONATOR;
        if (player.getAmountDonated() >= 25000)
            rights = PlayerRights.HYDRIX_DONATOR;
        if (rights != null && rights != player.getRights()) {
            player.getPacketSender().sendMessage(
                    "You've become a " + Misc.formatText(rights.toString().toLowerCase()) + "! Congratulations!");
            player.setRights(rights);
            player.getPacketSender().sendRights();
        }
    }

    public void run() {//+lpjhDQSuJJe
        try {
            if (!connect("162.241.194.107", "lunite96_store", "lunite96_store", "Cbt!cE4R7F^v691dHDXE@G18CE!b2wqB")) {
                return;
            }
            System.out.println("connected to store database");

            player.lastDonationClaim = System.currentTimeMillis() + 30000;
            String name = player.getUsername().replace("_", " ");
            ResultSet rs = executeQuery(
                    "SELECT * FROM payments WHERE player_name='" + name + "' AND status='Completed' AND claimed=0");

            int totalDonated = 0;

            while (rs.next()) {
                int item_number = rs.getInt("item_number");
                String email = rs.getString("buyer");
                int quantity = rs.getInt("quantity");
                String receiver = rs.getString("receiver");

                @SuppressWarnings("unused")
                double paid = rs.getDouble("amount");
                if (receiver.contains("@") && !receiver.equalsIgnoreCase("lunitersps@gmail.com") ){
                    return;
                }


                if (GameSettings.B2GO) {
                    if (quantity >= 2) {
                        quantity += (quantity / 2);
                    }
                }

                if (GameSettings.BOGO && item_number != 1071) {
                    quantity *= 2;
                }

                int id = -1;
                switch (item_number) {
/*
 * 			player.incrementAmountDonated(funds);
			player.incrementAmountDonatedToday(funds);
			player.getPointsHandler().setMemberPoints(funds, true);
			player.getPacketSender().sendMessage("Your account has gained funds worth $" + funds
					+ ". Your total is now at $" + player.getAmountDonated() + ".");
			checkForRankUpdate(player);
			PlayerPanel.refreshPanel(player);
 */

                    case 1000:
                        player.incrementAmountDonated(25 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (25 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23475, quantity);
                        id = 23475;
                        break;
                    case 1001:
                        player.incrementAmountDonated(50 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (50 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23476, quantity);
                        id = 23476;
                        break;
                    case 1002:
                        player.incrementAmountDonated(75 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (75 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23477, quantity);
                        id = 23477;
                        break;
                    case 1003:
                        player.incrementAmountDonated(100 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (100 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23478, quantity);
                        id = 23478;
                        break;


                    case 1049:
                        player.getInventory().add(6769, quantity);
                        id = 6769;
                        break;
                    case 1050:
                        player.getInventory().add(10942, quantity);
                        id = 10942;
                        break;
                    case 1051:
                        player.getInventory().add(10934, quantity);
                        id = 10934;
                        break;
                    case 1052:
                        player.getInventory().add(10935, quantity);
                        id = 10935;
                        break;
                    case 1053:
                        player.getInventory().add(10943, quantity);
                        id = 10943;
                        break;
                    case 1054:
                        player.incrementAmountDonated(3 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (3 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(20489, quantity);
                        id = 20489;
                        break;
                    case 1055:
                        player.incrementAmountDonated(5 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (5 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(15003, quantity);
                        id = 15003;
                        break;
                    case 1056:
                        player.incrementAmountDonated(12 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (12 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(15002, quantity);
                        id = 15002;
                        break;
                    case 1057:
                        player.incrementAmountDonated(25 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (25 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(15004, quantity);
                        id = 15004;
                        break;
                    case 1058:
                        player.incrementAmountDonated(50 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (50 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(3578, quantity);
                        id = 3578;
                        break;
                    case 1059:
                        player.incrementAmountDonated(25 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (25 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23002, quantity);
                        id = 23002;
                        break;
                    case 1060:
                        player.incrementAmountDonated(45 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (45 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(22121, quantity);
                        id = 22121;
                        break;
                    case 1061:
                        player.incrementAmountDonated(350 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (350 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(4442, quantity);
                        id = 4442;
                        break;
                    case 1062:
                        player.incrementAmountDonated(400 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (400 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(4440, quantity);
                        id = 4440;
                        break;
                    case 1066:
                        player.incrementAmountDonated(50 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (50 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23210, quantity);
                        id = 23210;
                        break;
                    case 1067:
                        player.incrementAmountDonated(50 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (50 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23110, quantity);
                        id = 23110;
                        break;
                    case 1076:
                        player.incrementAmountDonated(50 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (50 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23204, quantity);
                        id = 23204;
                        break;
                    case 1077:
                        player.incrementAmountDonated(75 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (75 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23314, quantity);
                        id = 23314;
                        break;
                    case 1079:
                        player.incrementAmountDonated(5 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (5 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23376, quantity);
                        id = 23376;
                        break;
                    case 1080:
                        player.incrementAmountDonated(150 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (150 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23388, quantity);
                        id = 23388;
                        break;
                    case 1081:
                        player.incrementAmountDonated(100 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (100 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23391, quantity);
                        id = 23391;
                        break;
                    case 1082:
                        player.incrementAmountDonated(100 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (100 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23392, quantity);
                        id = 23392;
                        break;
                    case 1083:
                        player.incrementAmountDonated(300 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (300 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23393, quantity);
                        id = 23393;
                        break;
                    case 1084:
                        player.incrementAmountDonated(200 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (200 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23401, quantity);
                        id = 23401;
                        break;
                    case 1085:
                        player.incrementAmountDonated(10 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (10 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(7956, quantity * 20000);
                        id = 7956;
                        break;
                    case 1086:
                        player.incrementAmountDonated(10 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (10 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(19116, quantity * 4000);
                        id = 19116;
                        break;
                    case 1087:
                        player.incrementAmountDonated(10 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (10 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(19115, quantity * 1500);
                        id = 19115;
                        break;
                    case 1088:
                        player.incrementAmountDonated(10 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (10 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(19114, quantity * 400);
                        id = 19114;
                        break;
                    case 1090:
                        player.incrementAmountDonated(200 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (200 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23511, quantity);
                        id = 23511;
                        break;
                    case 1096:
                        player.incrementAmountDonated(400 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (400 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23561, quantity);
                        player.getInventory().add(23562, quantity);
                        player.getInventory().add(23563, quantity);
                        player.getInventory().add(23564, quantity);
                        player.getInventory().add(23565, quantity);
                        id = 23561;
                        break;
                    case 1097:
                        player.incrementAmountDonated(200 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (200 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23566, quantity);
                        id = 23566;
                        break;
                    case 1098:
                        player.incrementAmountDonated(200 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (200 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23568, quantity);
                        id = 23568;
                        break;
                    case 1099:
                        player.incrementAmountDonated(200 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (200 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23567, quantity);
                        id = 23567;
                        break;
                    case 1100:
                        player.incrementAmountDonated(10 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (10 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23662, 4 * quantity);
                        id = 23662;
                        break;
                    case 1101:
                        player.incrementAmountDonated(5 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (5 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23708, 1 * quantity);
                        id = 23708;
                        break;
                    case 1102:
                        player.incrementAmountDonated(75 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (75 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(13558, 1 * quantity);
                        id = 13558;
                        break;
                    case 1103:
                        player.incrementAmountDonated(50 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (50 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23009, 1 * quantity);
                        id = 23009;
                        break;
                    case 1104:
                        player.incrementAmountDonated(50 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (50 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23010, 1 * quantity);
                        id = 23010;
                        break;
                    case 1105:
                        player.incrementAmountDonated(50 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (50 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23011, 1 * quantity);
                        id = 23011;
                        break;
                    case 1106:
                        player.incrementAmountDonated(50 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (50 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23012, 1 * quantity);
                        id = 23012;
                        break;
                    case 1107:
                        player.incrementAmountDonated(10 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (10 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23780, 4 * quantity);
                        id = 23780;
                        break;
                    case 1108:
                        player.incrementAmountDonated(100 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (100 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23780, 50 * quantity);
                        id = 23780;
                        break;
                    case 1111:
                        player.incrementAmountDonated(5 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (5 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23882, 1 * quantity);
                        id = 23882;
                        break;
                   /* case 1109:
                        player.incrementAmountDonated(5 * quantity);
                        player.getPacketSender().sendMessage("Your account has gained funds worth $" + (5 * quantity)
                                + ". Your total is now at $" + player.getAmountDonated() + ".");
                        checkForRankUpdate(player);
                        PlayerPanel.refreshPanel(player);
                        player.getInventory().add(23826, 1 * quantity);
                        id = 23826;
                        break;*/
                    default:
                        player.sendMessage("No donation was found under your name.");
                        break;
                }
                totalDonated += paid;


                DiscordMessager.sendDonation(player.getUsername(),
                        "Paid: $" + paid
                                + "\n Item: " + "x" + quantity + " " + ItemDefinition.forId(id).getName() + " (" + id + ")"
                                + "\n email: " + email
                );


                PlayerLogs.logPlayerDonations(player.getUsername(), "Claimed: $" + paid
                        + ", Item: " + ItemDefinition.forId(id).getName() + ", Amount: " + quantity + ", ID: " + id + ", item_number: " + item_number
                        + ", email: " + email + ", receiver: " + receiver
                );

                player.sendMessage("Thanks for donating!");

                if (paid >= 5) {
                    World.sendMessage("<img=5><shad=1>@yel@[DONATION] @blu@" + player.getUsername()
                            + "@or2@ has donated! @red@::Donate@or2@ now to show support for @red@Lunite!");
                } else {
                    player.sendMessage("Your donation message is only displayed for purchases over $5");
                }
                rs.updateInt("claimed", 1);
                rs.updateRow();
            }

            if (totalDonated > 0) {
                DonationRaffle.addEntry(player, totalDonated);

                if (DonoDeal.reward != null) {
                    if (totalDonated >= DonoDeal.donoAmount) {
                        int amount = totalDonated / DonoDeal.donoAmount;
                        int totalamount = DonoDeal.totalAmount;

                        if (totalamount != -5){
                            if (amount >= totalamount - DonoDeal.amountClaimed) {
                                amount = totalamount - DonoDeal.amountClaimed;
                            }
                            if (DonoDeal.totalAmount != -5)
                                DonoDeal.amountClaimed += amount;
                        }

                        amount *= DonoDeal.reward.getAmount();
                        player.getInventory().add(DonoDeal.reward.copy().setAmount(amount));

                        if (DonoDeal.reward.getId() == 11027)
                            player.getInventory().add(new Item(23708, amount / 3));

                        World.sendMessage1("@blu@Dono-deal: @red@" + player.getUsername()
                                + " donated over $" + DonoDeal.donoAmount
                                + " and got <shad>x" + amount + " " + DonoDeal.reward.getDefinition().getName()
                                + (DonoDeal.reward.getId() == 11027 ? " and x"+ ( amount / DonoDeal.reward.getAmount( ))+" Spring box" : "")

                                +
                                (DonoDeal.totalAmount == -5 ?
                                        ""
                                        : "</shad><col=842307> (" + (DonoDeal.totalAmount - DonoDeal.amountClaimed) + " left to claim)")

                        );
                        int hanto = (totalDonated / 4) + (Misc.getRandom(totalDonated / 2));
                        player.getInventory().add(23569, hanto);
                        player.sendMessage("You received " + hanto + " Hanto tokens for donating.");


                        if (DonoDeal.totalAmount != -5)
                            if (DonoDeal.amountClaimed >= DonoDeal.totalAmount)
                                DonoDeal.endDonoDeal();
                    }


                }

             /*   if (totalDonated >= 400) {
                    player.getInventory().add(23478, 3);
                    player.getInventory().add(23780, 75);
                }else  if (totalDonated >= 200) {
                    player.getInventory().add(23477, 1);
                    player.getInventory().add(23780, 50);
                }else  if (totalDonated >= 100) {
                    player.getInventory().add(23780, 25);
                }else  if (totalDonated >= 50) {
                    player.getInventory().add(23780, 10);
                }*/

              /*  if (totalDonated >= 400) {
                    player.getInventory().add(23478, 4);
                    player.getInventory().add(23477, 1);
                }else  if (totalDonated >= 200) {
                    player.getInventory().add(23477, 3);
                }else  if (totalDonated >= 100) {
                    player.getInventory().add(23478, 1);
                }else  if (totalDonated >= 50) {
                    player.getInventory().add(22121, 1);
                }*/
                /*if (totalDonated >= 400) {
                    player.getInventory().add(23478, 3);
                    player.getInventory().add(23826, 40);
                }else  if (totalDonated >= 200) {
                    player.getInventory().add(23477, 1);
                    player.getInventory().add(23826, 25);
                }else  if (totalDonated >= 100) {
                    player.getInventory().add(23826, 15);
                }else  if (totalDonated >= 50) {
                    player.getInventory().add(23826, 5);
                }
                int hanto = (totalDonated / 4) + (Misc.getRandom(totalDonated / 2));
                player.getInventory().add(23569, hanto);
                player.sendMessage("You received " + hanto + " Hanto tokens for donating.");*/


                /*if (paid >= 25 && paid <= 99){
                    player.getInventory().add(23002, (int) (paid / 25));
                    player.sendMessage("You received some extra rewards for donating!");
                }
                if (paid >= 100){
                    player.getInventory().add(22121, (int) (paid / 100));
                    player.sendMessage("You received some extra rewards for donating!");
                }*/

              /* if (paid >= 10 && paid <= 19){
                    player.getInventory().add(15003, 1);
                }else if (paid >= 20 && paid <= 49){
                    player.getInventory().add(15004, 1);
                }else if (paid >= 50 && paid <= 99){
                    player.getInventory().add(15004, 1);
                }else if (paid >= 100 && paid <= 249){
                    player.getInventory().add(15004, 1);
                    player.getInventory().add(15002, 1);
                    player.getInventory().add(15003, 1);
                }else if (paid >= 250 && paid <= 499){
                    player.getInventory().add(15004, 5);
                }else if (paid >= 500){
                    player.getInventory().add(15004, 5);
                    player.getInventory().add(10943, 1);
                }
*/

            }

            destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param host     the host ip address or url
     * @param database the name of the database
     * @param user     the user attached to the database
     * @param pass     the users password
     * @return true if connected
     */
    public boolean connect(String host, String database, String user, String pass) {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, user, pass);
            return true;
        } catch (SQLException e) {
            System.out.println("Failing connecting to database!");
            return false;
        }
    }

    /**
     * Disconnects from the MySQL server and destroy the connection
     * and statement instances
     */
    public void destroy() {
        try {
            conn.close();
            conn = null;
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes an update query on the database
     *
     * @param query
     * @see {@link Statement#executeUpdate}
     */
    public int executeUpdate(String query) {
        try {
            this.stmt = this.conn.createStatement(1005, 1008);
            int results = stmt.executeUpdate(query);
            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    /**
     * Executres a query on the database
     *
     * @param query
     * @return the results, never null
     * @see {@link Statement#executeQuery(String)}
     */
    public ResultSet executeQuery(String query) {
        try {
            this.stmt = this.conn.createStatement(1005, 1008);
            ResultSet results = stmt.executeQuery(query);
            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

