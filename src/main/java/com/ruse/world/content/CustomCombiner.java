package com.ruse.world.content;

import com.ruse.model.Item;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.Setter;

public class CustomCombiner {

    private Player player;

    public CustomCombiner(Player player) {
        this.player = player;
    }

    enum CustomCombinerData {
        ELITE_MELEE_WEAPON(new Item(8410, 1), new Item(12855, 3000000), new Item(8087), new Item(4151, 1), new Item(18686, 1), new Item(22077, 1), new Item(20549, 1), new Item(22084, 1), new Item(18750, 1)),
        ELITE_MELEE_HELM(new Item(8828, 1), new Item(12855, 2000000), new Item(11320), new Item(8810, 1), new Item(11140, 1), new Item(5022, 40000), new Item(6927, 1)),
        ELITE_MELEE_BODY(new Item(8829, 1), new Item(12855, 2000000), new Item(11321), new Item(19749, 1), new Item(8335, 1), new Item(5022, 40000), new Item(6928, 1)),
        ELITE_MELEE_LEGS(new Item(8833, 1), new Item(12855, 2000000), new Item(11322), new Item(22080, 1), new Item(19114, 2), new Item(5022, 40000), new Item(6929, 1)),

        EXEC_HELM(new Item(4684, 1), new Item(8828), new Item(15642, 1), new Item(15645, 1), new Item(10947, 1), new Item(12855, 3000000)),
        SUPREME_GLOVES(new Item(18883, 1), new Item(5071), new Item(14063, 1), new Item(11766, 1), new Item(10947, 1), new Item(12855, 4000000), new Item(5022, 1000000)),


        ELITE_RANGED_WEAPON(new Item(8411, 1), new Item(12855, 3000000), new Item(8088), new Item(11235, 1), new Item(18799, 1), new Item(19136, 1), new Item(20173, 1), new Item(22083, 1), new Item(18636, 1)),
        ELITE_RANGED_HELM(new Item(15642, 1), new Item(12855, 2000000), new Item(11340), new Item(8811, 1), new Item(8334, 1), new Item(5022, 40000), new Item(6930, 1)),
        ELITE_RANGED_BODY(new Item(15643, 1), new Item(12855, 2000000), new Item(11341), new Item(11195, 1), new Item(16137, 1), new Item(5022, 40000), new Item(6931, 1)),
        ELITE_RANGED_LEGS(new Item(15644, 1), new Item(12855, 2000000), new Item(11342), new Item(19115, 2), new Item(19114, 2), new Item(5022, 40000), new Item(6932, 1)),

        EXEC_BODY(new Item(4685, 1), new Item(8829), new Item(15643, 1), new Item(15646, 1), new Item(10947, 1), new Item(12855, 3000000)),
        SUPREME_BOOTS(new Item(18881, 1), new Item(3107), new Item(5072, 1), new Item(16265, 1), new Item(10947, 1), new Item(12855, 3000000)),


        ELITE_MAGE_WEAPON(new Item(8412, 1), new Item(12855, 3000000), new Item(8089), new Item(15486, 1), new Item(5095, 1), new Item(6936, 1), new Item(8809, 1), new Item(22092, 1), new Item(18629, 1)),
        ELITE_MAGE_HELM(new Item(15645, 1), new Item(12855, 2000000), new Item(11421), new Item(8812, 1), new Item(4367, 1), new Item(5022, 40000), new Item(6933, 1)),
        ELITE_MAGE_BODY(new Item(15646, 1), new Item(12855, 2000000), new Item(11422), new Item(19115, 2), new Item(19892, 1), new Item(5022, 40000), new Item(6934, 1)),
        ELITE_MAGE_LEGS(new Item(15647, 1), new Item(12855, 2000000), new Item(11423), new Item(15401, 1), new Item(8329, 1), new Item(5022, 40000), new Item(6935, 1)),

        EXEC_LEGS(new Item(4686, 1), new Item(8833), new Item(15644, 1), new Item(15647, 1), new Item(10947, 1), new Item(12855, 3000000)),
        SUPREME_SHIELD(new Item(19810, 1), new Item(15832), new Item(17702, 1), new Item(7544, 1), new Item(20438, 1), new Item(10947, 1), new Item(12855, 5000000), new Item(5022, 1000000)),


        TWISTED_BOW(new Item(5012, 1), new Item(8411, 3), new Item(8088, 3), new Item(10947, 3), new Item(12855, 7500000)),
        SCYTHE(new Item(12535, 1), new Item(8410, 3), new Item(8087, 3), new Item(10947, 3), new Item(12855, 7500000)),
        STAFF(new Item(17011, 1), new Item(8412, 3), new Item(8089, 3), new Item(10947, 3), new Item(12855, 7500000)),

        LIGHT_TBOW(new Item(5011, 1), new Item(5012, 1), new Item(10949, 3)),
        LIGHT_VITUR(new Item(12537, 1), new Item(12535, 1), new Item(10949, 3)),
        LIGHT_STAFF(new Item(17013, 1), new Item(17011, 1), new Item(10949, 3)),

        DARK_TBOW(35, new Item(22113, 1), new Item(5011, 1), new Item(22112, 3),
                new Item(5022, 5_000_000), new Item(12855, 10_000_000)),
        DARK_VITUR(35, new Item(22115, 1), new Item(12537, 1), new Item(22112, 3),
                new Item(5022, 5_000_000), new Item(12855, 10_000_000)),
        DARK_STAFF(35, new Item(22114, 1), new Item(17013, 1), new Item(22112, 3),
                new Item(5022, 5_000_000), new Item(12855, 10_000_000)),

        BLOOD_TBOW(30, new Item(23222, 1), new Item(22113, 1), new Item(23223, 3),
                new Item(5022, 10_000_000), new Item(12855, 25_000_000)),
        BLOOD_VITUR(30, new Item(23221, 1), new Item(22115, 1), new Item(23223, 3),
                new Item(5022, 10_000_000), new Item(12855, 25_000_000)),
        BLOOD_STAFF(30, new Item(23220, 1), new Item(22114, 1), new Item(23223, 3),
                new Item(5022, 10_000_000), new Item(12855, 25_000_000)),


        LUCIFER_HEAD(new Item(22100, 1), new Item(22106, 2),
                new Item(4684, 1)),

        LUCIFER_BODY(new Item(22101, 1), new Item(22106, 2),
                new Item(4685, 1)),

        LUCIFER_LEGS(new Item(22102, 1), new Item(22106, 2),
                new Item(4686, 1)),

        LUCIFER_WINGS(new Item(22105, 1), new Item(22106, 2),
                new Item(20400, 1)),

        LUCIFER_GLOVES(new Item(22104, 1), new Item(22106, 1),
                new Item(18885, 1)),

        LUCIFER_BOOTS(new Item(22103, 1), new Item(22106, 1),
                new Item(18887, 1)),

        LIGHT_ATTACHEMENT(33, false, new Item(10949, 1), new Item(23224, 1),
                new Item(10947, 5), new Item(12855, 500000)),

        DARK_ATTACHEMENT(33, false, new Item(22112, 1), new Item(23517, 1),
                new Item(10947, 20), new Item(12855, 2000000)),

        BLOOD_ATTACHEMENT(33, false, new Item(23223, 1), new Item(23518, 1),
                new Item(10947, 30), new Item(12855, 4000000)),

        LUCIFER_PET(30, false, new Item(22107, 1), new Item(22106, 1),
                new Item(19000, 10_000), new Item(5022, 10_000_000),
                new Item(12855, 10_000_000)),


        OC_JEWELRY(new Item(23204, 2), new Item(7995, 1)),

        OWNERCAPE(25, true, new Item(7995, 1), new Item(22105, 1), new Item(12855, 50_000_000)),


        OWNER_AURA(new Item(22111, 1),
                new Item(12630, 1), new Item(22110, 1)),


        OWNER_AMMY(100,new Item(23713, 1), new Item(23351, 1),
                new Item(22110, 2)),
        OWNER_RING(100,new Item(23714, 1), new Item(23352, 1),
                new Item(22110, 2)),
        OWNER_BRAC(100,new Item(23715, 1), new Item(23353, 1),
                new Item(22110, 2)),
        OWNER_AURA_U(100,new Item(23712, 1),
                new Item(22111, 1), new Item(22110, 2)),



        OWNER_CAPE(100,new Item(22109, 1),
                new Item(7995, 1), new Item(22110, 2)),

        RAGE_POTION(new Item(15328, 1), new Item(15330, 1), new Item(12855, 50_000_000)),

        OWNER_POTION(new Item(23374, 1), new Item(7995, 1), new Item(15328, 1), new Item(12855, 100_000_000)),

        AUTO_DISSEMBLER(35, false, new Item(23375, 1), new Item(19886, 1), new Item(4446, 1), new Item(12855, 1_000_000)),

        EMPTY_CREST(new Item(782, 1), new Item(779, 1), new Item(780, 1), new Item(781, 1)),

        MELEE_CREST_LIGHT(new Item(23482, 1), new Item(782, 1), new Item(12537, 1)),
        RANGED_CREST_LIGHT(new Item(23483, 1), new Item(782, 1), new Item(17013, 1)),
        MAGIC_CREST_LIGHT(new Item(23484, 1), new Item(782, 1), new Item(5011, 1)),

        MELEE_CREST_DARK(new Item(23485, 1), new Item(782, 1), new Item(22115, 1)),
        RANGED_CREST_DARK(new Item(23486, 1), new Item(782, 1), new Item(22114, 1)),
        MAGIC_CREST_DARK(new Item(23487, 1), new Item(782, 1), new Item(22113, 1)),

        MELEE_CREST_BLOOD(new Item(23488, 1), new Item(782, 1), new Item(23221, 1)),
        RANGED_CREST_BLOOD(new Item(23489, 1), new Item(782, 1), new Item(23220, 1)),
        MAGIC_CREST_BLOOD(new Item(23490, 1), new Item(782, 1), new Item(23222, 1)),

        GRANDMASTER_SET(new Item(23509, 1), new Item(23497, 1), new Item(23498, 1), new Item(23499, 1)),

        REAPER_SET(new Item(23507, 1), new Item(23501, 1), new Item(23502, 1), new Item(23503, 1), new Item(23504, 1), new Item(23505, 1)),

        SUPREME_COSTUME(10, false, new Item(23520, 1), new Item(10947, 10)),
        DARK_SUPREME_COSTUME(50, true, new Item(23522, 1), new Item(23520, 1), new Item(22112, 5)),

        DARK_LORD_COSTUME(100, new Item(23554, 1), new Item(23507, 1), new Item(22100, 1), new Item(22101, 1), new Item(22102, 1), new Item(22103, 1), new Item(22104, 1)),

        HEART_GODS(new Item(23494, 1), new Item(23491, 1), new Item(23492, 1), new Item(23493, 1), new Item(12855, 50000000)),

        MELEE_BOOTS(100, new Item(23558, 1), new Item(23555, 1), new Item(22103, 1), new Item(12855, 25000000)),
        RANGED_BOOTS(100, new Item(23559, 1), new Item(23556, 1), new Item(22103, 1), new Item(12855, 25000000)),
        MAGIC_BOOTS(100, new Item(23560, 1), new Item(23557, 1), new Item(22103, 1), new Item(12855, 25000000)),

        MASS_DISSOLVER(100, new Item(23570, 1), new Item(23571, 1), new Item(5022, 2500000)),


        ASSASSIN_QUIVER(100, new Item(23705, 1), new Item(23706, 1), new Item(23488, 1), new Item(23489, 1), new Item(23490, 1)
                , new Item(12855, 50000000), new Item(23339, 25000), new Item(23340, 25000), new Item(23341, 25000)),

        ICYENIC_STAFF(100, new Item(23638, 1), new Item(23632, 1), new Item(23631, 2500), new Item(12855, 50_000_000), new Item(5022, 20_000_000), new Item(23220, 1)),
        ICYENIC_HAMMER(100, new Item(23640, 1), new Item(23634, 1), new Item(23631, 2500), new Item(12855, 50_000_000), new Item(5022, 20_000_000), new Item(23221, 1)),
        ICYENIC_BOW(100, new Item(23642, 1), new Item(23636, 1), new Item(23631, 2500), new Item(12855, 50_000_000), new Item(5022, 20_000_000), new Item(23222, 1)),

        INFERNAL_STAFF(100, new Item(23639, 1), new Item(23633, 1), new Item(23631, 2500), new Item(12855, 50_000_000), new Item(5022, 20_000_000), new Item(23220, 1)),
        INFERNAL_HAMMER(100, new Item(23641, 1), new Item(23635, 1), new Item(23631, 2500), new Item(12855, 50_000_000), new Item(5022, 20_000_000), new Item(23221, 1)),
        INFERNAL_BOW(100, new Item(23643, 1), new Item(23637, 1), new Item(23631, 2500), new Item(12855, 50_000_000), new Item(5022, 20_000_000), new Item(23222, 1)),




        CELESTIAL_AMULET(100, new Item(23740, 1), new Item(23713, 1), new Item(23736, 75000)),
        CELESTIAL_RING(100, new Item(23741, 1), new Item(23714, 1), new Item(23736, 75000)),
        CELESTIAL_BRAC(100, new Item(23737, 1), new Item(23715, 1), new Item(23736, 75000)),

        CELESTIAL_CAPE(100, new Item(23739, 1), new Item(22109, 1), new Item(23736, 200000)),

        CELESTIAL_AURA(100, new Item(23738, 1), new Item(23712, 1), new Item(23736, 150000)),

        CELESTIAL_QUIVER(100, new Item(23742, 1), new Item(23705, 1), new Item(23736, 100000)),


        RAMMERNAUT_BOOTS(50,false, new Item(23758, 1), new Item(23558, 1), new Item(23559, 1), new Item(23560, 1)
                , new Item(23759, 1000)),

        GODCRUSHER_COSTUME(100, new Item(23774, 1), new Item(23772, 1), new Item(12855, 50000000)),


        DIVINE_SCROLL(100, new Item(23781, 1), new Item(23777, 1), new Item(4440, 1), new Item(4442, 1), new Item(12855, 250000000)),
        ANGELIC_POTION(100, new Item(23779, 1), new Item(23778, 1), new Item(23374, 1), new Item(12855, 250000000)),


        CELESTIAL_HELM(100, new Item(23743, 1), new Item(23525, 1),
                new Item(23528, 1),  new Item(23531, 1), new Item(23225, 1), new Item(23736, 25000)),
        CELESTIAL_BODY(100, new Item(23744, 1), new Item(23526, 1),
                new Item(23529, 1),  new Item(23532, 1), new Item(23225, 2), new Item(23736, 25000)),
        CELESTIAL_LEGS(100, new Item(23745, 1), new Item(23527, 1),
                new Item(23530, 1),  new Item(23533, 1), new Item(23225, 2), new Item(23736, 25000)),

        CELESTIAL_ATTACHMENT(100, new Item(23225), new Item(10949, 10), new Item(22112, 10),
                new Item(23223, 10),  new Item(23736, 25000)),

        SACRIFICIAL_COSTUME(100, new Item(23834), new Item(23729), new Item(23730), new Item(23731), new Item(23732)
                , new Item(23733), new Item(23734)),


        DORMANT_STAFF(100, new Item(23847), new Item(23638), new Item(23639)),
        DORMANT_SWORD(100, new Item(23848), new Item(23640), new Item(23641)),
        DORMANT_BOW(100, new Item(23846), new Item(23642), new Item(23643)),


        LEVIATHON_SIGIL(100, new Item(23852), new Item(23849), new Item(23850), new Item(23851)),

        LEVIATHON_STAFF(100, new Item(23842), new Item(23847), new Item(23852)),
        LEVIATHON_SWORD(100, new Item(23840), new Item(23848), new Item(23852)),
        LEVIATHON_BOW(100, new Item(23841), new Item(23846), new Item(23852)),

        ASSASSIN_CLAWS(100, new Item(23888), new Item(23696), new Item(23885)),

        ;

        CustomCombinerData(Item reward, Item... requirements) { // Upgrade item, chance, requirements
            this.successRate = 100;
            this.reward = reward;
            this.requirements = requirements;
            this.mainItem = requirements[0];
        }

        CustomCombinerData(int successRate, Item reward, Item... requirements) {
            this.successRate = successRate;
            this.reward = reward;
            this.requirements = requirements;
            this.mainItem = requirements[0];
        }

        CustomCombinerData(int successRate, boolean keepMainItemOnFail, Item reward, Item... requirements) {
            this.successRate = successRate;
            this.keepMainItemOnFail = keepMainItemOnFail;
            this.reward = reward;
            this.requirements = requirements;
            this.mainItem = requirements[0];
        }

        private boolean keepMainItemOnFail = true;
        private int successRate;
        private Item mainItem;
        private Item reward;
        private Item[] requirements;

    }

    private Item selectedItem = null;
    @Getter
    @Setter
    private boolean combinerOpen = false;

    @Getter
    @Setter
    private int tabOpen = 0;

    private final CustomCombinerData[] VALUES = CustomCombinerData.values();

    public void open() {
        player.getPacketSender().sendInterface(30330);
        updateInterface();
    }

    private void updateInterface() {
        int index = 0;

        for (CustomCombinerData data : VALUES) {
            player.getPacketSender().sendItemOnInterface(30351, data.reward.getId(), index, data.reward.getAmount());
            index++;
        }
    }

    public void handleSelection(Item item) {
        selectedItem = item;
        for (CustomCombinerData data : VALUES) {
            Item[] ing = data.requirements;
            player.setUpgradeSelection(item);
            int success = data.successRate;


            if (data == CustomCombinerData.RAMMERNAUT_BOOTS ) {
                success = 100;
                ing = new Item[]{new Item(23558, 1), new Item(23559, 1), new Item(23560, 1)
                        , new Item(23759, 2500)};
            }

            if (data == CustomCombinerData.CELESTIAL_AMULET ){
                ing = new Item[]{new Item(23713, 1), new Item(23736, 75000)};
            }else                if (data == CustomCombinerData.CELESTIAL_RING ){
                ing = new Item[]{new Item(23714, 1), new Item(23736, 75000)};
            }else                if (data == CustomCombinerData.CELESTIAL_BRAC ){
                ing = new Item[]{new Item(23715, 1), new Item(23736, 75000)};
            }else                if (data == CustomCombinerData.CELESTIAL_CAPE ){
                ing = new Item[]{new Item(22109, 1), new Item(23736, 200000)};
            }else                if (data == CustomCombinerData.CELESTIAL_AURA ){
                ing = new Item[]{new Item(23712, 1), new Item(23736, 150000)};
            }else                if (data == CustomCombinerData.CELESTIAL_QUIVER ){
                ing = new Item[]{new Item(23705, 1), new Item(23736, 100000)};
            }


            if (data.reward.getId() == selectedItem.getId()) {
                player.getPacketSender().resetItemsOnInterface(30340, 14);
                player.getPacketSender().sendCombinerItemsOnInterface(30340, ing);
                player.getPacketSender().sendItemOnInterface(30336, data.reward.getId(), 0, data.reward.getAmount());
                player.getPacketSender().sendString(30347,
                        "Success Rate: " + (success == 100 ? "@gre@" + success : "@red@" + success) + "%");
                updateInterface();
                break;
            }
        }
    }

    public void combine() {

        if (selectedItem == null) {
            player.sendMessage("@red@You haven't selected an item yet.");
            return;
        }
        for (CustomCombinerData data : VALUES) {
            if (data.reward.getId() == selectedItem.getId()) {

                if (data.ordinal() >= CustomCombinerData.EMPTY_CREST.ordinal()
                        && data.ordinal() <= CustomCombinerData.MAGIC_CREST_BLOOD.ordinal()
                        && !player.isLearnedCrestCreation()) {
                    player.sendMessage("You have not learned how to create this item.");
                    return;
                }

                Item[] ing = data.requirements;
                int success = data.successRate;

                if (data == CustomCombinerData.RAMMERNAUT_BOOTS ) {
                    success = 100;
                    ing = new Item[]{new Item(23558, 1), new Item(23559, 1), new Item(23560, 1)
                            , new Item(23759, 2500)};
                }
                    if (data == CustomCombinerData.CELESTIAL_AMULET ){
                    ing = new Item[]{new Item(23713, 1), new Item(23736, 75000)};
                }else                if (data == CustomCombinerData.CELESTIAL_RING ){
                    ing = new Item[]{new Item(23714, 1), new Item(23736, 75000)};
                }else                if (data == CustomCombinerData.CELESTIAL_BRAC ){
                    ing = new Item[]{new Item(23715, 1), new Item(23736, 75000)};
                }else                if (data == CustomCombinerData.CELESTIAL_CAPE ){
                    ing = new Item[]{new Item(22109, 1), new Item(23736, 200000)};
                }else                if (data == CustomCombinerData.CELESTIAL_AURA ){
                    ing = new Item[]{new Item(23712, 1), new Item(23736, 150000)};
                }else                if (data == CustomCombinerData.CELESTIAL_QUIVER ){
                    ing = new Item[]{new Item(23705, 1), new Item(23736, 100000)};
                }


                if (player.getInventory().containsAll(ing)) {
                    if (success < 100) {
                        if (Misc.getRandom(99) >= success) {
                            player.getInventory().deleteItemSet(ing);
                            player.sendMessage("@red@You've failed to make the " + ItemDefinition.forId(data.reward.getId()).getName() + "!");
                            if (data.keepMainItemOnFail)
                                player.getInventory().add(data.mainItem);
                            return;
                        }
                    }

                    player.getInventory().deleteItemSet(ing);
                    player.getInventory().add(data.reward);
                    player.getDailyTaskManager().submitProgressToIdentifier(32, 1);

                    PlayerLogs.logUpgrades(player.getUsername(), "INVENTED : " + selectedItem.getDefinition().getName()
                            + ", id: " + selectedItem.getId() + ", amount: " + selectedItem.getAmount() + " --into-- " +
                            data.reward.getDefinition().getName()
                            + ", id: " + data.reward.getId() + ", amount: " + data.reward.getAmount());

                    World.sendMessage("<img=5>@blu@[Invention]<img=5> @red@" + player.getUsername() + "@blu@ has created "
                            +
                            (data.reward.getAmount() > 1 ? "x" + data.reward.getAmount() + " @red@" : "@red@")
                            + data.reward.getDefinition().getName());
                } else {
                    player.sendMessage("@red@You don't have the required items for this invention.");
                }
                break;
            }
        }

    }


}
