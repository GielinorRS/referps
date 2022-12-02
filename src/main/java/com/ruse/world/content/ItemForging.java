package com.ruse.world.content;

import com.ruse.model.Graphic;
import com.ruse.model.Item;
import com.ruse.model.Skill;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.AchievementsOLD.AchievementDataOLD;
import com.ruse.world.entity.impl.player.Player;

/**
 * Handles item forging, such as Spirit shields making etc.
 *
 * @author Gabriel Hannason and Samy
 */
public class ItemForging {

    public static void forgeItem(final Player p, final int item1, final int item2) {
        if (item1 == item2)
            return;
        ItemForgeData data = ItemForgeData.getDataForItems(item1, item2);
        if (data == null || !p.getInventory().contains(item1) || !p.getInventory().contains(item2))
            return;
        if (!p.getClickDelay().elapsed(1000))
            return;
        if (p.getInterfaceId() > 0) {
            p.getPacketSender().sendMessage("Please close the interface you have open before doing this.");
            return;
        }
        Skill skill = Skill.forId(data.skillRequirement[0]);
        int skillReq = data.skillRequirement[1];
        if (p.getSkillManager().getCurrentLevel(skill) >= skillReq) {
            for (Item reqItem : data.requiredItems) {
                if (!p.getInventory().contains(reqItem.getId())
                        || p.getInventory().getAmount(reqItem.getId()) < reqItem.getAmount()) {
                    p.getPacketSender().sendMessage("You need " + Misc.anOrA(reqItem.getDefinition().getName()) + " "
                            + reqItem.getDefinition().getName() + " to forge a new item.");
                    return;
                }
            }
            p.performGraphic(new Graphic(2010));
            for (Item reqItem : data.requiredItems) {
                if (reqItem.getId() == 1755 || reqItem.getId() == 1595 || reqItem.getId() == 233
                        || reqItem.getId() == 7329 || reqItem.getId() == 7330 || reqItem.getId() == 7331
                        || reqItem.getId() == 10326 || reqItem.getId() == 10327)
                    continue;
                p.getInventory().delete(reqItem);
            }
            p.getInventory().add(data.product, true);
            final String itemName = Misc.formatText(ItemDefinition.forId(data.product.getId()).getName().toLowerCase());
            p.getPacketSender().sendMessage("<shad=1>@or2@You've made@mag@ " + Misc.anOrA(itemName) + " " + itemName + ".");

            if (data == ItemForgeData.EASTER_PENDANT || data == ItemForgeData.BUNNY_HAT_GREEN
            || data == ItemForgeData.BUNNY_HAT_GOLD || data == ItemForgeData.BUNNY_HAT_BLUE ||
                    data == ItemForgeData.BUNNY_HAT_RED || data == ItemForgeData.Collector_ammy
            || data == ItemForgeData.Collector_ring ||data == ItemForgeData.REAPER_HOOD) {
                String msg = "@blu@<img=5>[CREATION]<img=5>@red@ " + p.getUsername() + " has created "  + Misc.anOrA(itemName) + " " + itemName + "!";
                World.sendMessage(msg);
            }

            p.getClickDelay().reset();
            if (data.skillRequirement[0] != 21) {
                p.getSkillManager().addExperience(skill, 100);// data.skillRequirement[2]);
            }
            if (data == ItemForgeData.ARMADYL_GODSWORD || data == ItemForgeData.BANDOS_GODSWORD
                    || data == ItemForgeData.ZAMORAK_GODSWORD || data == ItemForgeData.SARADOMIN_GODSWORD) {
                AchievementsOLD.finishAchievement(p, AchievementDataOLD.ASSEMBLE_A_GODSWORD);
                AchievementsOLD.doProgress(p, AchievementDataOLD.ASSEMBLE_5_GODSWORDS);
            }
            return;
        } else {
            p.getPacketSender().sendMessage("You need " + Misc.anOrA(skill.getFormatName()) + " "
                    + skill.getFormatName() + " level of at least " + skillReq + " to forge this item.");
            return;
        }
    }

    /**
     * * The enum holding all our data
     */
    private static enum ItemForgeData {
        CRYSTAL_BOW(new Item[]{new Item(23785), new Item(23815)}, new Item(23817), new int[]{0, 0, 0}),
        CRYSTAL_WAND(new Item[]{new Item(23786), new Item(23815)}, new Item(23818), new int[]{0, 0, 0}),
        CRYSTAL_HALBERD(new Item[]{new Item(23787), new Item(23815)}, new Item(23819), new int[]{0, 0, 0}),
        CRYSTAL_SHIELD(new Item[]{new Item(23788), new Item(23816)}, new Item(23820), new int[]{0, 0, 0}),

        CRYSTAL_HELM(new Item[]{new Item(23789), new Item(23816)}, new Item(23821), new int[]{0, 0, 0}),
        CRYSTAL_BODY(new Item[]{new Item(23790), new Item(23816)}, new Item(23822), new int[]{0, 0, 0}),
        CRYSTAL_LEGS(new Item[]{new Item(23791), new Item(23816)}, new Item(23823), new int[]{0, 0, 0}),
        CRYSTAL_BOOTS(new Item[]{new Item(23792), new Item(23816)}, new Item(23824), new int[]{0, 0, 0}),
        CRYSTAL_GLOVES(new Item[]{new Item(23793), new Item(23816)}, new Item(23825), new int[]{0, 0, 0}),


        ELITE_VOID_TOP(new Item[]{new Item(23580), new Item(23588)}, new Item(23586), new int[]{0, 0, 0}),
        ELITE_VOID_LEGS(new Item[]{new Item(23581), new Item(23588)}, new Item(23587), new int[]{0, 0, 0}),


        VOID_WAND(new Item[]{new Item(23589), new Item(23591)}, new Item(23592), new int[]{0, 0, 0}),
        VOID_SWORD(new Item[]{new Item(23590), new Item(23591)}, new Item(23593), new int[]{0, 0, 0}),



        REAPER_HOOD(new Item[]{new Item(11789), new Item(23107)}, new Item(11790), new int[]{0, 0, 0}),


        BUNNY_HAT_GREEN(new Item[]{new Item(23013), new Item(23009)}, new Item(23014), new int[]{0, 0, 0}),
        BUNNY_HAT_GOLD(new Item[]{new Item(23013), new Item(23010)}, new Item(23015), new int[]{0, 0, 0}),
        BUNNY_HAT_BLUE(new Item[]{new Item(23013), new Item(23011)}, new Item(23016), new int[]{0, 0, 0}),
        BUNNY_HAT_RED(new Item[]{new Item(23013), new Item(23012)}, new Item(23017), new int[]{0, 0, 0}),

        EASTER_PENDANT(new Item[]{new Item(13558), new Item(23354)}, new Item(23000), new int[]{0, 0, 0}),


        ABYSSAL_TENTACLE(new Item[]{new Item(4151), new Item(22007)}, new Item(22008), new int[]{21, 0, 0}),

        Collector_ammy(new Item[]{new Item(19888), new Item(9084)}, new Item(18888), new int[]{21, 0, 0}),
        Collector_ring(new Item[]{new Item(18823), new Item(9084)}, new Item(18818), new int[]{21, 0, 0}),

        ENRAGEDCAPE(new Item[]{new Item(20591), new Item(9083)}, new Item(20400), new int[]{21, 0, 0}),
        TBOW(new Item[]{new Item(5012), new Item(10949, 3)}, new Item(5011), new int[]{21, 0, 0}),
        SCYTHE(new Item[]{new Item(12535), new Item(10949, 3)}, new Item(12537), new int[]{21, 0, 0}),
        SANG(new Item[]{new Item(17011), new Item(10949, 3)}, new Item(17013), new int[]{21, 0, 0}),

        MEGA1(new Item[]{new Item(18883), new Item(9080, 1), new Item(9081, 1), new Item(9082, 1)}, new Item(18885), new int[]{21, 0, 0}),
        MEGA2(new Item[]{new Item(18881), new Item(9080, 1), new Item(9081, 1), new Item(9082, 1)}, new Item(18887), new int[]{21, 0, 0}),
        MEGA3(new Item[]{new Item(19810), new Item(9080, 1), new Item(9081, 1), new Item(9082, 1)}, new Item(18889), new int[]{21, 0, 0}),


        ABYSSAL_TENTACLE_WHITE(new Item[]{new Item(15443), new Item(22007)}, new Item(22008),
                new int[]{21, 0, 0}),
        ABYSSAL_TENTACLE_YELLOW(new Item[]{new Item(15441), new Item(22007)}, new Item(22008),
                new int[]{21, 0, 0}),
        ABYSSAL_TENTACLE_BLUE(new Item[]{new Item(15442), new Item(22007)}, new Item(22008), new int[]{21, 0, 0}),
        ABYSSAL_TENTACLE_GREEN(new Item[]{new Item(15444), new Item(22007)}, new Item(22008),
                new int[]{21, 0, 0}),

        SERPENTINE_HELM(new Item[]{new Item(12929), new Item(12934, 500)}, new Item(12931), new int[]{21, 0, 0}),

        CAP_AND_GOGGLES(new Item[]{new Item(9945), new Item(9472)}, new Item(9946), new int[]{21, 0, 0}),

        BLESSED_SPIRIT_SHIELD(new Item[]{new Item(13754), new Item(13734)}, new Item(13736), new int[]{21, 0, 0}),
        SPECTRAL_SPIRIT_SHIELD(new Item[]{new Item(13752), new Item(13736)}, new Item(13744),
                new int[]{13, 85, 40000}),
        ARCANE_SPIRIT_SHIELD(new Item[]{new Item(13746), new Item(13736)}, new Item(13738),
                new int[]{13, 85, 40000}),
        ELYSIAN_SPIRIT_SHIELD(new Item[]{new Item(13750), new Item(13736)}, new Item(13742),
                new int[]{13, 85, 40000}),
        DIVINE_SPIRIT_SHIELD(new Item[]{new Item(13748), new Item(13736)}, new Item(13740),
                new int[]{13, 85, 40000}),
        SHADOW_SPIRIT_SHIELD(new Item[]{new Item(18784), new Item(13736)}, new Item(6293),
                new int[]{13, 85, 40000}),
        HELLFIRE_SPIRIT_SHIELD(new Item[]{new Item(18783), new Item(13736)}, new Item(18754),
                new int[]{13, 85, 40000}),

        /*
         * AMULET_OF_GLORY(new Item[] {new Item(2357), new Item(1615)}, new Item(1704),
         * new int[] {12, 80, 10000}), AMULET_OF_STRENGTH(new Item[] {new Item(2357),
         * new Item(1603)}, new Item(1725), new int[] {12, 50, 3500}),
         * AMULET_OF_MAGIC(new Item[] {new Item(2357), new Item(1607)}, new Item(1727),
         * new int[] {12, 24, 1400}), AMULET_OF_POWER(new Item[] {new Item(2357), new
         * Item(1601)}, new Item(1731), new int[] {12, 70, 4500}), AMULET_OF_DEFENCE(new
         * Item[] {new Item(2357), new Item(1605)}, new Item(1729), new int[] {12, 31,
         * 2100}), AMULET_OF_FURY(new Item[] {new Item(2357), new Item(6573)}, new
         * Item(6585), new int[] {12, 90, 175000}),
         *
         * ONYX_BOLTS(new Item[] {new Item(2363), new Item(6573)}, new Item(9245, 15),
         * new int[] {9, 90, 20000}), DRAGONSTONE_BOLTS(new Item[] {new Item(2363), new
         * Item(1615)}, new Item(9244, 15), new int[] {9, 80, 10000}), DIAMOND_BOLTS(new
         * Item[] {new Item(2361), new Item(1601)}, new Item(9243, 15), new int[] {9,
         * 70, 8000}), RUBY_BOLTS(new Item[] {new Item(2361), new Item(1603)}, new
         * Item(9242, 15), new int[] {9, 60, 6000}), EMERALD_BOLTS(new Item[] {new
         * Item(2359), new Item(1605)}, new Item(9241, 15), new int[] {9, 40, 2000}),
         * SAPPHIRE_BOLTS(new Item[] {new Item(9142), new Item(9189)}, new Item(9337,
         * 15), new int[] {9, 30, 1000}),
         */
        TOKKUL(new Item[]{new Item(6570), new Item(6522)}, new Item(6529, 1000), new int[]{12, 95, 10000}),

        ARDOUGNE_CLOAK_4(new Item[]{new Item(15103), new Item(15104), new Item(15105), new Item(15106)},
                new Item(19748), new int[]{5, 94, 500}),

        DRAGON_SQ_SHIELD(new Item[]{new Item(2368), new Item(2366)}, new Item(1187), new int[]{13, 60, 10000}),
        DRAGON_PLATEBY(new Item[]{new Item(14472), new Item(14474), new Item(14476)}, new Item(14479),
                new int[]{13, 92, 500}),
        DRAGONFIRE_SHIELD(new Item[]{new Item(11286), new Item(1540)}, new Item(11283), new int[]{13, 82, 36000}),

        CRYSTAL_KEY(new Item[]{new Item(985), new Item(987)}, new Item(989), new int[]{21, 0, 0}),

        ARCHER_RING(new Item[]{new Item(607), new Item(6733)}, new Item(15019), new int[]{21, 0, 0}),
        WARRIOR_RING(new Item[]{new Item(607), new Item(6735)}, new Item(15020), new int[]{21, 0, 0}),
        BERSERKER_RING(new Item[]{new Item(607), new Item(6737)}, new Item(15220), new int[]{21, 0, 0}),
        SEERS_RING(new Item[]{new Item(607), new Item(6731)}, new Item(15018), new int[]{21, 0, 0}),

        GODSWORD_BLADE(new Item[]{new Item(11710), new Item(11712), new Item(11714)}, new Item(11690),
                new int[]{21, 0, 0}),
        ARMADYL_GODSWORD(new Item[]{new Item(11702), new Item(11690)}, new Item(11694), new int[]{21, 0, 0}),
        BANDOS_GODSWORD(new Item[]{new Item(11704), new Item(11690)}, new Item(11696), new int[]{21, 0, 0}),
        SARADOMIN_GODSWORD(new Item[]{new Item(11706), new Item(11690)}, new Item(11698), new int[]{21, 0, 0}),
        ZAMORAK_GODSWORD(new Item[]{new Item(11708), new Item(11690)}, new Item(11700), new int[]{21, 0, 0}),

        AMULET_OF_FURY_ORNAMENT(new Item[]{new Item(19333), new Item(6585)}, new Item(19335),
                new int[]{21, 0, 0}),
        DRAGON_FULL_HELM_SPIKE(new Item[]{new Item(19354), new Item(11335)}, new Item(19341),
                new int[]{21, 0, 0}),
        DRAGON_PLATELEGS_SPIKE(new Item[]{new Item(19356), new Item(4087)}, new Item(19343), new int[]{21, 0, 0}),
        DRAGON_PLATEBODY_SPIKE(new Item[]{new Item(19358), new Item(14479)}, new Item(19342),
                new int[]{21, 0, 0}),
        DRAGON_SQUARE_SHIELD_SPIKE(new Item[]{new Item(19360), new Item(1187)}, new Item(19345),
                new int[]{21, 0, 0}),
        DRAGON_FULL_HELM_GOLD(new Item[]{new Item(19346), new Item(11335)}, new Item(19336), new int[]{21, 0, 0}),
        DRAGON_PLATELEGS_GOLD(new Item[]{new Item(19348), new Item(4087)}, new Item(19338), new int[]{21, 0, 0}),
        DRAGON_PLATEBODY_GOLD(new Item[]{new Item(19350), new Item(14479)}, new Item(19337), new int[]{21, 0, 0}),
        DRAGON_SQUARE_SHIELD_GOLD(new Item[]{new Item(19352), new Item(1187)}, new Item(19340),
                new int[]{21, 0, 0}),

        FULL_SLAYER_HELMET(new Item[]{new Item(13263), new Item(15490), new Item(15488)}, new Item(15492),
                new int[]{18, 75, 0}),

        /* H'WEEN SHIT */
        // BONEMEAL(new Item[] {new Item(526), new Item(233)}, new Item(17680), new
        // int[] {21, 0, 0}), //whiteberries, pestle & mortar
        CRUSHED_WHITEBERRIES(new Item[]{new Item(239), new Item(233)}, new Item(17680), new int[]{21, 0, 0}), // whiteberries,
        // pestle
        // &
        // mortar
        PASTE(new Item[]{new Item(17680), new Item(227)}, new Item(2424), new int[]{21, 0, 0}), // crushed
        // whiteberries,
        // vial,
        // bonemeal
        DEATH_HOOD(new Item[]{new Item(3057), new Item(12961)}, new Item(22036), new int[]{21, 0, 0}), // mask
        DEATH_ROBE_TOP(new Item[]{new Item(581), new Item(4837)}, new Item(22037), new int[]{21, 0, 0}), //
        DEATH_ROBE_BOTTOM(new Item[]{new Item(1015), new Item(4837)}, new Item(22038), new int[]{21, 0, 0}), //
        ;

        private Item[] requiredItems;
        private Item product;
        private int[] skillRequirement;
        ItemForgeData(Item[] requiredItems, Item product, int[] skillRequirement) {
            this.requiredItems = requiredItems;
            this.product = product;
            this.skillRequirement = skillRequirement;
        }

        public static ItemForgeData getDataForItems(int item1, int item2) {
            for (ItemForgeData shieldData : ItemForgeData.values()) {
                int found = 0;
                for (Item it : shieldData.requiredItems) {
                    if (it.getId() == item1 || it.getId() == item2)
                        found++;
                }
                if (found >= 2)
                    return shieldData;
            }
            return null;
        }
    }
}
