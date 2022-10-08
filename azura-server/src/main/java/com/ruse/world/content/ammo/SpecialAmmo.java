package com.ruse.world.content.ammo;

import com.ruse.model.Item;
import com.ruse.model.container.impl.Equipment;
import com.ruse.util.Misc;
import com.ruse.world.content.combat.CombatType;
import com.ruse.world.content.dialogue.Dialogue;
import com.ruse.world.content.dialogue.DialogueExpression;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.content.dialogue.DialogueType;
import com.ruse.world.entity.impl.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SpecialAmmo {

    DEFAULT(23315, 23323, new double[]{1, 1, 1}),
    DEATH(23324, 23332, new double[]{1, 3, 5}),
    HEALING(23333, 23341, new double[]{0.001, 0.002, 0.003}),
    AOE(23342, 23350, new double[]{1.1, 1.25, 1.5}),
    ;

    private int itemId;
    private int endItemId;
    private double[] tierBoosts;

    public static CombatType[] types = new CombatType[]{CombatType.RANGED, CombatType.MAGIC, CombatType.MELEE};

    public static AmmoType getAmmo(Player player) {
        for (SpecialAmmo s : SpecialAmmo.values()) {
            int ammo = player.getEquipment().forSlot(Equipment.AMMUNITION_SLOT).getId();
            if (ammo >= s.getItemId()
                    && ammo <= s.getEndItemId()) {
                int ordinal = (ammo - s.getItemId()) / 3;
                double boost = s.getTierBoosts()[ordinal];
                int type = (ammo - s.getItemId()) % 3;
                return new AmmoType(ammo, boost, ordinal + 1, s, types[type]);
            }
        }
        return null;
    }

    public static boolean isAmmo(Player player, SpecialAmmo specialAmmo) {
        AmmoType ammo = getAmmo(player);
        if (ammo != null) {
            if (ammo.getCombatType() == player.getCurrentCombatType()
                    && ammo.getSpecialAmmo() == specialAmmo) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAmmo(Player player) {
        AmmoType ammo = getAmmo(player);
        if (ammo != null) {
            if (ammo.getCombatType() == player.getCurrentCombatType()) {
                return true;
            }
        }
        return false;
    }

    public static SpecialAmmo forId(int itemId) {
        for (SpecialAmmo s : SpecialAmmo.values()) {
            if (itemId >= s.getItemId()
                    && itemId <= s.getEndItemId()) {
                return s;
            }
        }
        return null;
    }

    public static boolean upgrade(Player player, Item item) {
        SpecialAmmo ammo = forId(item.getId());
        if (ammo == null) {
            return false;
        }
        int tier = ((item.getId() - ammo.getItemId()) / 3) + 1;
        int mult = tier == 1 ? 10 : 25;

        if (player.getInventory().contains(item.getId()) && !item.getDefinition().getName().contains("(3)")) {
            int amount = item.getAmount();
            if (amount * mult >= player.getInventory().getAmount(12855)) {
                amount = player.getInventory().getAmount(12855) / mult;
            }
            if (amount >= 1000000) {
                amount = 1000000;
            }
            player.setInteractingItem(new Item(item.getId(), amount));
            player.setAmmoUpgradeCost(mult * amount);
            if (amount > 0)
                DialogueManager.start(player, dialogue(player));
            else
                player.sendMessage("You need at least " + mult + " Upgrade tokens to upgrade this.");
        }
        return true;
    }

    public static void upgrade(Player player) {
        player.getPacketSender().sendInterfaceRemoval();

        if (player.getInventory().contains(player.getInteractingItem().getId())
                && player.getInventory().getAmount(12855) >= player.getAmmoUpgradeCost() && player.getInteractingItem().getId() <= 23347) {

            player.getInventory().delete(12855, player.getAmmoUpgradeCost());
            player.getInventory().delete(player.getInteractingItem().getId(), player.getInteractingItem().getAmount());
            player.getInventory().add(player.getInteractingItem().getId() + 3, player.getInteractingItem().getAmount());

            player.sendMessage("You upgraded your ammo.");

            player.setInteractingItem(null);
            player.setAmmoUpgradeCost(0);
        } else {
            player.sendMessage("You don't have the correct items.");
        }
    }

    public static Dialogue dialogue(final Player player) {
        return new Dialogue() {
            @Override
            public DialogueType type() {
                return DialogueType.OPTION;
            }

            @Override
            public int npcId() {
                return -1;
            }

            @Override
            public DialogueExpression animation() {
                return null;
            }

            @Override
            public String[] dialogue() {
                return new String[]{"Upgrade x" + player.getInteractingItem().getAmount() + " " + player.getInteractingItem().getDefinition().getName()
                        + " for " + Misc.insertCommasToNumber(player.getAmmoUpgradeCost()) + " Upgrade tokens.", "Cancel"
                };
            }

            @Override
            public void specialAction() {
                player.setDialogueActionId(7979);
            }
        };
    }


    @AllArgsConstructor
    @Getter
    public static class AmmoType {

        private int itemId;
        private double boost;
        private int tier;
        private SpecialAmmo specialAmmo;
        private CombatType combatType;

    }

}
