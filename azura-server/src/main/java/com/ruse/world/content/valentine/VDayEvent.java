package com.ruse.world.content.valentine;

import com.ruse.model.Item;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.entity.impl.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VDayEvent {

    public static int maxAmount = 5000;
    public static int ROSE_PETAL = 23536;

    private Player player;
    private int love;
    private int hate;
    private boolean[] unlockedLove = new boolean[LoveRewards.values().length];
    private boolean[] unlockedHate = new boolean[HateRewards.values().length];
    private Boolean unlockedCrown = false;

    public VDayEvent(Player player) {
        this.player = player;
    }

    public void openInterface(int amount) {
        player.getPacketSender().sendProgressBar(143012, 0, (int) (((double)love / (double)maxAmount) * 100D), 0);
        player.getPacketSender().sendProgressBar(143013, 0, (int) (((double)hate / (double)maxAmount) * 100D), 0);

        player.getPacketSender().sendString(143015, "" + love);
        player.getPacketSender().sendString(143017, "" + hate);


        player.getPacketSender().sendString(143009, "" + amount);

        player.getPacketSender().sendItemOnInterface(143014, 23544, 1);

        int interfaceID = 143026;
        for (int i = 0 ; i < unlockedLove.length; i ++) {
            player.getPacketSender().sendString(interfaceID--, unlockedLove[i] ? "Unlocked!" : "" + Misc.insertCommasToNumber(LoveRewards.values()[i].getPercentage()));
            player.getPacketSender().sendItemOnInterface(interfaceID--, LoveRewards.values()[i].getItemId(), 1);
        }

        interfaceID = 143034;
        for (int i = 0 ; i < unlockedHate.length; i ++) {
            player.getPacketSender().sendString(interfaceID--, unlockedHate[i] ? "Unlocked!" : "" +  Misc.insertCommasToNumber(HateRewards.values()[i].getPercentage()));
            player.getPacketSender().sendItemOnInterface(interfaceID--, HateRewards.values()[i].getItemId(), 1);
        }

        player.getPacketSender().sendInterface(143000);
    }

    public void sprinkle() {
        int amount = player.getInventory().getAmount(ROSE_PETAL);

        if (love >= maxAmount) {
            player.sendMessage("@red@You have already reached the maximum amount of love.");
            return;
        }

        if (amount == 0) {
            player.sendMessage("@red@You need Rose petals in your inventory to do this.");
            return;
        }

        if (love + amount >= maxAmount) {
            amount = maxAmount - love;
        }
        player.sendMessage("@red@You sprinkle " + amount + " Rose petals.");
        player.getInventory().delete(ROSE_PETAL, amount);
        love += amount;

        for (int i = 0 ; i < unlockedLove.length; i ++){
            if (!unlockedLove[i] && love >= LoveRewards.values()[i].getPercentage()){
                unlockedLove[i] = true;
                if (player.getInventory().hasSpaceFor(LoveRewards.values()[i].getItemId(), 1))
                    player.getInventory().add(LoveRewards.values()[i].getItemId(), 1);
                else
                    player.depositItemBank(new Item(LoveRewards.values()[i].getItemId(), 1));
                player.sendMessage("@red@You have unlocked " + ItemDefinition.forId(LoveRewards.values()[i].getItemId()).getName());
                World.sendMessage("<img=1572><col=d90c9e>[Valentines]<img=1572> <col=bb2528>" + player.getUsername()
                        + "<col=B56FA1> has just received <col=bb2528>"+ItemDefinition.forId(LoveRewards.values()[i].getItemId()).getName()+"!");
            }
        }

        if (!unlockedCrown && love >= maxAmount && hate >= maxAmount){
            unlockedCrown = true;
            if (player.getInventory().hasSpaceFor(23544, 1))
            player.getInventory().add(23544, 1);
            else
                player.depositItemBank(new Item(23544, 1));
            player.sendMessage("@red@You have unlocked " + ItemDefinition.forId(23544).getName());
            World.sendMessage("<img=1572><col=d90c9e>[Valentines]<img=1572> <col=bb2528>" + player.getUsername()
                    + "<col=B56FA1> has just received <col=bb2528>"+ItemDefinition.forId(23544).getName()+"!");
        }

        openInterface(player.getInventory().getAmount(ROSE_PETAL));
    }

    public void crush() {
        int amount = player.getInventory().getAmount(ROSE_PETAL);

        if (hate >= maxAmount) {
            player.sendMessage("@red@You have already reached the maximum amount of hate.");
            return;
        }

        if (amount == 0) {
            player.sendMessage("@red@You need Rose petals in your inventory to do this.");
            return;
        }

        if (hate + amount >= maxAmount) {
            amount = maxAmount - hate;
        }
        player.sendMessage("@red@You crushed " + amount + " Rose petals.");
        player.getInventory().delete(ROSE_PETAL, amount);
        hate += amount;

        for (int i = 0 ; i < unlockedHate.length; i ++) {
            if (!unlockedHate[i] && hate >= HateRewards.values()[i].getPercentage()){
                unlockedHate[i] = true;
                if (player.getInventory().hasSpaceFor(HateRewards.values()[i].getItemId(), 1))
                    player.getInventory().add(HateRewards.values()[i].getItemId(), 1);
                else
                    player.depositItemBank(new Item(HateRewards.values()[i].getItemId(), 1));
                player.sendMessage("@red@You have unlocked " + ItemDefinition.forId(HateRewards.values()[i].getItemId()).getName());
                World.sendMessage("<img=1572><col=d90c9e>[Valentines]<img=1572> <col=bb2528>" + player.getUsername()
                        + "<col=B56FA1> has just received <col=bb2528>"+ItemDefinition.forId(HateRewards.values()[i].getItemId()).getName()+"!");
            }
        }

        if (!unlockedCrown && love >= maxAmount && hate >= maxAmount){
            unlockedCrown = true;
            if (player.getInventory().hasSpaceFor(23544, 1))
                player.getInventory().add(23544, 1);
            else
                player.depositItemBank(new Item(23544, 1));
            player.sendMessage("@red@You have unlocked " + ItemDefinition.forId(23544).getName());
            World.sendMessage("<img=1572><col=d90c9e>[Valentines]<img=1572> <col=bb2528>" + player.getUsername()
                    + "<col=B56FA1> has just received <col=bb2528>"+ItemDefinition.forId(23544).getName()+"!");
        }
        openInterface(player.getInventory().getAmount(ROSE_PETAL));
    }


    public boolean handleButton(final int buttonId) {
        if (buttonId == 143010) {
            sprinkle();
            return true;
        } else if (buttonId == 143011) {
            crush();
            return true;
        }
        return false;
    }

    @Getter
    @AllArgsConstructor
    public enum LoveRewards {
        AMULET(23540, 1000),
        SHIELD(23539, 2000),
        WEAPON(13556, 3000),
        HELM(23542, 4000),

        ;
        private int itemId;
        private int percentage;
    }

    @Getter
    @AllArgsConstructor
    public enum HateRewards {
        AMULET(23541, 1000),
        SHIELD(23538, 2000),
        WEAPON(23537, 3000),
        HELM(23543, 4000),
        ;
        private int itemId;
        private int percentage;
    }

}
