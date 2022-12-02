package com.ruse.world.content;

import com.ruse.model.container.impl.Shop;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;

public class DonatorShop {

    public static final int ITEM_CHILD_ID = 33900;
    public static final int ITEM_CHILD_ID_CLICK = -31636;
    public static final int INTERFACE_ID = 118000;

    private Player player;

    public DonatorShop(Player player) {
        this.player = player;
    }

    public boolean handleButton(int buttonId) {

        switch (buttonId) {
            case 118005:
                openInterface(DonatorShopType.WEAPON);
                return true;
            case 118006:
                openInterface(DonatorShopType.ARMOUR);
                return true;
            case 118007:
                openInterface(DonatorShopType.ACCESSORY);
                return true;
            case 118008:
                openInterface(DonatorShopType.MISC);
                return true;
        }

        return false;
    }

    public void openInterface(DonatorShopType type) {
        player.getPacketSender().sendConfig(5333, type.ordinal());
        Shop.ShopManager.getShops().get(type.getShopId()).open(player);
    }
    
    public static Object[] getPrice(int item){
        switch (item) {
            case 24020:// Mystical Aura
            case 24021:// Sharpshooter Aura
            case 24022:// Warrior Aura
                return new Object[]{1000, "Member Points"};

            case 24023:// Ultimate Aura
                return new Object[]{3000, "Member Points"};

            case 22121:// Golden Scratch Card
                return new Object[]{10, "Member Points"};

            case 23002:// Points Gamble Chest
                return new Object[]{250, "Member Points"};

            case 15084:// Dice
                return new Object[]{1000, "Member Points"};

            case 299:// Flowers
                return new Object[]{100, "Member Points"};

            case 23102:// Insane Gauntlets
                return new Object[]{500, "Member Points"};

            case 6833:// Golden Aura Goodiebag
                return new Object[]{750, "Member Points"};

            case 3578:// Golden Cape Goodiebag
                return new Object[]{750, "Member Points"};

            case 24101:// Adverse Goodiebag
                return new Object[]{200, "Member Points"};

            case 23126:// T3 Pots
            case 23120:
            case 23123:
                return new Object[]{50, "Member Points"};

            case 23171: //Box of Rares
                return new Object[]{300, "Member Points"};
            default:
                return new Object[]{1000, "Donator points"};
        }
    }

    @Getter
    public enum DonatorShopType {
        WEAPON(80),
        ARMOUR(200),
        ACCESSORY(201),
        MISC(202);
        private int shopId;

        DonatorShopType(int shopId) {
            this.shopId = shopId;
        }
        
        public static boolean isDonatorStore(int id){
            for (DonatorShopType donatorShopType : values()){
                if ( id == donatorShopType.getShopId()){
                    return true;
                }
            }
            return false;
        }
    }

}
