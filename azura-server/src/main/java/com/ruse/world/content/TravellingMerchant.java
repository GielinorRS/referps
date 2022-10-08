package com.ruse.world.content;

import com.ruse.model.Item;
import com.ruse.model.container.impl.Shop;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.entity.impl.player.Player;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;

public class TravellingMerchant {

    public static int dayInYear;
    public static boolean updatingTask;
    public final static ArrayList<Item> shopItems = new ArrayList<>();
    public static Item[] FIRST_ITEMS = new Item[]{
            new Item(15358, 5),//200k
            new Item(15359, 5),//200k
            new Item(15356, 2),//1m
            new Item(21218, 5),//50k
            new Item(22006, 25),//50k
            new Item(10947, 5),//100k
            new Item(23752, 3),//500k
            new Item(23753, 10),//50k
            new Item(23755, 1),//250m
            new Item(23759, 25),//250k
            new Item(23776, 5),//200k
            new Item(23782, 5),//200k
            new Item(23812, 5),//200k
            new Item(23835, 3),//250k
    };

    public static int getPrice(int itemID) {
        switch (itemID){
            case 23835:
                return 250000;
            case 23776:
            case 23782:
                return 200000;
            case 15358:
            case 15359:
                return 200000;
            case 15356:
                return 1000000;
            case 21218:
            case 22006:
                return 50000;
            case 10947:
                return 100000;
            case 23752:
                return 500000;
            case 23753:
                return 100000;
            case 23755:
                return 250000000;
            case 23759:
                return 250000;
        }
        return 10000000;
    }

    public static void sequence() {
        if (dayInYear <= 0) {
            Calendar cal = Calendar.getInstance();
            int day = cal.get(Calendar.DAY_OF_YEAR);
            dayInYear = day;
        }
        if (shopItems.size() == 0){
            resetItems();
            World.sendNewsMessage("The Traveling merchant restocked his shop with new items!");
        }
        if (ZonedDateTime.now().getHour() == 0 && ZonedDateTime.now().getMinute() == 0) {
            if (ZonedDateTime.now().getSecond() == 1 && !updatingTask) {
                updatingTask = true;
                dayInYear += 1;
                resetItems();
                World.sendNewsMessage("The Traveling merchant restocked his shop with new items!");
            } else if (ZonedDateTime.now().getSecond() == 2 && updatingTask) {
                updatingTask = false;
            }
        }
    }

    public static void resetItems() {
        shopItems.clear();
        if (!shopItems.contains(FIRST_ITEMS[8])) {
            shopItems.add(FIRST_ITEMS[8]);
        }
        while (shopItems.size() < 4) {
            Item newItem = FIRST_ITEMS[Misc.getRandom(FIRST_ITEMS.length - 1)];
            if (!shopItems.contains(newItem)) {
                shopItems.add(newItem);
            }
        }
    }

    public static void openShop(Player player) {
        if (player.getTravelingMerchantDay() != dayInYear) {
            player.getTravelingMerchantItems().clear();
            player.setMerchantItems(new Item[250]);
            for (int i = 0; i < 250; i++) {
                player.getMerchantItems()[i] = new Item(-1, 0);
            }

            int i = 0;
            for (Item item : shopItems) {
                player.getTravelingMerchantItems().add(new Item(item.getId(), item.getAmount()));
                player.getMerchantItems()[i++] = new Item(item.getId(), item.getAmount());
            }

            player.setTravelingMerchantDay(dayInYear);
        }
        new Shop(player, 3001, "Travelling Merchant", new Item(12855), player.getMerchantItems()).open(player);
       // Shop.ShopManager.getShops().get(31).open(player);
    }

}