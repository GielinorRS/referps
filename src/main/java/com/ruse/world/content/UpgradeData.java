package com.ruse.world.content;

import com.ruse.model.Item;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public enum UpgradeData {
    // Tier 0 (ZERO)
    WHIP(0, new Item(4151, 1), new Item(18686, 1), 60, new Item(12855, 125)),
    D_HELMET(0, new Item(4716, 1), new Item(13996, 1), 60, new Item(12855, 125)),
    D_CHAINBODY(0, new Item(4720, 1), new Item(13913, 1), 60, new Item(12855, 125)),
    D_PLATESKIRT(0, new Item(4722, 1), new Item(13919, 1), 60, new Item(12855, 125)),
    D_BOW(0, new Item(11235, 1), new Item(18799, 1), 60, new Item(12855, 125)),
    X_HELMET(0, new Item(4732, 1), new Item(18834, 1), 60, new Item(12855, 125)),
    X_CHESTPLATE(0, new Item(4736, 1), new Item(18801, 1), 60, new Item(12855, 125)),
    X_CHAINSKIRT(0, new Item(4738, 1), new Item(18800, 1), 60, new Item(12855, 125)),
    SOL(0, new Item(15486, 1), new Item(5095, 1), 60, new Item(12855, 125)),
    A_HELM(0, new Item(4708, 1), new Item(19140, 1), 60, new Item(12855, 125)),
    A_BODY(0, new Item(4712, 1), new Item(19139, 1), 60, new Item(12855, 125)),
    A_LEGS(0, new Item(4714, 1), new Item(19138, 1), 60, new Item(12855, 125)),
    FIRECAPE(0, new Item(6570, 1), new Item(4411, 1), 60, new Item(12855, 125)),
    DEFENDER(0, new Item(13262, 1), new Item(19887, 1), 60, new Item(12855, 125)),
    CMAUL(0, new Item(18353, 1), new Item(22078, 1), 60, new Item(12855, 125)),
    DBOOTS(0, new Item(11732, 1), new Item(19123, 1), 60, new Item(12855, 125)),
    FURY(0, new Item(6585, 1), new Item(11617, 1), 60, new Item(12855, 125)),
    BRING(0, new Item(6737, 1), new Item(3909, 1), 60, new Item(12855, 125)),
    GLOVES(0, new Item(7462, 1), new Item(3318, 1), 60, new Item(12855, 125)),
    AURA_T1(0, new Item(23044, 1), new Item(23045, 1), 80, new Item(12855, 100000)),
    SLAYER_BOX(0, new Item(7120, 1), new Item(23479, 1), 20, new Item(12855, 5000)),
    PVM_BOX_T2(0, new Item(7956, 1), new Item(23480, 1), 50, new Item(12855, 25)),

    // tier 1 (ONE)
    TORMENTED_WHIP(1, new Item(18686, 1), new Item(22077, 1), 50, new Item(12855, 250)),
    G_HELMET(1, new Item(13996, 1), new Item(6927, 1), 50, new Item(12855, 250)),
    G_CHAINBODY(1, new Item(13913, 1), new Item(6928, 1), 50, new Item(12855, 250)),
    G_PLATESKIRT(1, new Item(13919, 1), new Item(6929, 1), 50, new Item(12855, 250)),
    K_CBOW(1, new Item(18799, 1), new Item(19136, 1), 50, new Item(12855, 250)),
    K_HELMET(1, new Item(18834, 1), new Item(6930, 1), 50, new Item(12855, 250)),
    K_CHESTPLATE(1, new Item(18801, 1), new Item(6931, 1), 50, new Item(12855, 250)),
    K_CHAINSKIRT(1, new Item(18800, 1), new Item(6932, 1), 50, new Item(12855, 250)),
    SKOLLSTAFF(1, new Item(5095, 1), new Item(6936, 1), 50, new Item(12855, 250)),
    M_HELM(1, new Item(19140, 1), new Item(6933, 1), 50, new Item(12855, 250)),
    M_BODY(1, new Item(19139, 1), new Item(6934, 1), 50, new Item(12855, 250)),
    M_LEGS(1, new Item(19138, 1), new Item(6935, 1), 50, new Item(12855, 250)),
    DEVILWINGS(1, new Item(4411, 1), new Item(12634, 1), 50, new Item(12855, 250)),
    DEVILNECK(1, new Item(19887, 1), new Item(17291, 1), 50, new Item(12855, 250)),
    DEMONMAUL(1, new Item(22078, 1), new Item(22079, 1), 50, new Item(12855, 250)),
    UNIVERSAL_BOOTS(1, new Item(19123, 1), new Item(6937, 1), 50, new Item(12855, 250)),
    FURYZ(1, new Item(11617, 1), new Item(15418, 1), 50, new Item(12855, 250)),
    RINGZ(1, new Item(3909, 1), new Item(3324, 1), 50, new Item(12855, 250)),
    BOSSGLOVES(1, new Item(3318, 1), new Item(3905, 1), 50, new Item(12855, 250)),
    AURA_T2(1, new Item(23045, 1), new Item(23046, 1), 70, new Item(12855, 400000)),

    /// TIER 2 (TWO)
    BRUTAL_WHIP1(2, new Item(22077, 1), new Item(20549, 1), 40, new Item(12855, 500)),
    TORVA_HELMET(2, new Item(6927, 1), new Item(8800, 1), 40, new Item(12855, 500)),
    TORVA_CHAINBODY(2, new Item(6928, 1), new Item(8801, 1), 40, new Item(12855, 500)),
    TORVA_PLATESKIRT(2, new Item(6929, 1), new Item(8802, 1), 40, new Item(12855, 500)),
    VIXIE_BOW(2, new Item(19136, 1), new Item(20173, 1), 40, new Item(12855, 500)),
    PERNIX_COWL(2, new Item(6930, 1), new Item(8803, 1), 40, new Item(12855, 500)),
    PERNIX_CHAINBODY(2, new Item(6931, 1), new Item(8804, 1), 40, new Item(12855, 500)),
    PERNIX_PLATESKIRT(2, new Item(6932, 1), new Item(8805, 1), 40, new Item(12855, 500)),
    VIXIE_STAFF(2, new Item(6936, 1), new Item(8809, 1), 40, new Item(12855, 500)),
    VIRTUS_MASK(2, new Item(6933, 1), new Item(8806, 1), 40, new Item(12855, 500)),
    VIRTUS_BODY(2, new Item(6934, 1), new Item(8807, 1), 40, new Item(12855, 500)),
    VIRTUS_LEGS(2, new Item(6935, 1), new Item(8808, 1), 40, new Item(12855, 500)),
    INFERNOWINGS(2, new Item(12634, 1), new Item(8810, 1), 40, new Item(12855, 500)),
    NECKLACE(2, new Item(17291, 1), new Item(19749, 1), 40, new Item(12855, 500)),
    ANKOUMAUL(2, new Item(22079, 1), new Item(22080, 1), 40, new Item(12855, 500)),
    VIXIEBOOTS(2, new Item(6937, 1), new Item(8811, 1), 40, new Item(12855, 500)),
    ICEFURY(2, new Item(15418, 1), new Item(11195, 1), 40, new Item(12855, 500)),
    RINGII(2, new Item(3324, 1), new Item(15401, 1), 40, new Item(12855, 500)),
    BOSSGLOVES2(2, new Item(3905, 1), new Item(8812, 1), 40, new Item(12855, 500)),
    AURA_T3(2, new Item(23046, 1), new Item(23047, 1), 60, new Item(12855, 1000000)),

    // TIER 3 (THREE)
    T3_MELEE_WHIP(3, new Item(20549, 1), new Item(22084, 1), 30, new Item(12855, 4200)),
    T3_MBHELM(3, new Item(8800, 1), new Item(8326, 1), 30, new Item(12855, 4200)),
    T3_MBODY(3, new Item(8801, 1), new Item(8327, 1), 30, new Item(12855, 4200)),
    T3_MLEGS(3, new Item(8802, 1), new Item(8328, 1), 30, new Item(12855, 4200)),
    T3_BOW(3, new Item(20173, 1), new Item(22083, 1), 30, new Item(12855, 4200)),
    T3_RCOWL(3, new Item(8803, 1), new Item(8330, 1), 30, new Item(12855, 4200)),
    T3_RBODY(3, new Item(8804, 1), new Item(8331, 1), 30, new Item(12855, 4200)),
    T3_RLEGS(3, new Item(8805, 1), new Item(8332, 1), 30, new Item(12855, 4200)),
    T3_STAFF(3, new Item(8809, 1), new Item(22092, 1), 30, new Item(12855, 4200)),
    T3_VMASK(3, new Item(8806, 1), new Item(8323, 1), 30, new Item(12855, 4200)),
    T3_VBODY(3, new Item(8807, 1), new Item(8324, 1), 30, new Item(12855, 4200)),
    T3_VLEGS(3, new Item(8808, 1), new Item(8325, 1), 30, new Item(12855, 4200)),
    WING(3, new Item(8810, 1), new Item(4367, 1), 30, new Item(12855, 4200)),
    DEFENDER3(3, new Item(19749, 1), new Item(8329, 1), 30, new Item(12855, 4200)),
    BIGMAUL(3, new Item(22080, 1), new Item(16137, 1), 30, new Item(12855, 4200)),
    BOOOTS(3, new Item(8811, 1), new Item(8334, 1), 30, new Item(12855, 4200)),
    AMULET(3, new Item(11195, 1), new Item(19892, 1), 30, new Item(12855, 4200)),
    RING2(3, new Item(15401, 1), new Item(8335, 1), 30, new Item(12855, 4200)),
    GLOVES2(3, new Item(8812, 1), new Item(11140, 1), 30, new Item(12855, 4200)),

    AURA_T4(3, new Item(23047, 1), new Item(23048, 1), 50, new Item(12855, 2000000)),
    //
    TRINITY(4, new Item(22084, 1), new Item(18750, 1), 20, new Item(12855, 5000)),
    MELEE_HELM(4, new Item(8326, 1), new Item(18753, 1), 20, new Item(12855, 5000)),
    MELEE_BODY(4, new Item(8327, 1), new Item(18752, 1), 20, new Item(12855, 5000)),
    MELEE_LEGS(4, new Item(8328, 1), new Item(18751, 1), 20, new Item(12855, 5000)),
    BLASTBOMB(4, new Item(22083, 1), new Item(18636, 1), 20, new Item(12855, 5000)),
    RANGE_HELM(4, new Item(8330, 1), new Item(18749, 1), 20, new Item(12855, 5000)),
    RANGE_BODY(4, new Item(8331, 1), new Item(18748, 1), 20, new Item(12855, 5000)),
    RANGE_LEGS(4, new Item(8332, 1), new Item(18638, 1), 20, new Item(12855, 5000)),
    ARTS_STAFF(4, new Item(22092, 1), new Item(18629, 1), 20, new Item(12855, 5000)),
    MAGE_HELM(4, new Item(8323, 1), new Item(18631, 1), 20, new Item(12855, 5000)),
    MAGE_BODY(4, new Item(8324, 1), new Item(18637, 1), 20, new Item(12855, 5000)),
    MAGE_LEGS(4, new Item(8325, 1), new Item(18623, 1), 20, new Item(12855, 5000)),
    ARTDEF(4, new Item(8329, 1), new Item(17700, 1), 20, new Item(12855, 5000)),
    ARTSWORD(4, new Item(16137, 1), new Item(17698, 1), 20, new Item(12855, 5000)),

    AURA_T5(4, new Item(23048, 1), new Item(23049, 1), 40, new Item(12855, 4000000)),

    //
    MAX_MELEE_WEAPON(5, new Item(18750, 1), new Item(8087, 1), 22, new Item(12855, 35000)),
    MAX_MELEE_HELM(5, new Item(18753, 1), new Item(11320, 1), 25, new Item(12855, 35000)),
    MAX_MELEE_BODY(5, new Item(18752, 1), new Item(11321, 1), 25, new Item(12855, 35000)),
    MAX_MELEE_LEGS(5, new Item(18751, 1), new Item(11322, 1), 25, new Item(12855, 35000)),

    MAX_RANGE_CBOW(5, new Item(18636, 1), new Item(8088, 1), 22, new Item(12855, 35000)),
    MAX_RANGE_HELM(5, new Item(18749, 1), new Item(11340, 1), 25, new Item(12855, 35000)),
    MAX_RANGE_BODY(5, new Item(18748, 1), new Item(11341, 1), 25, new Item(12855, 35000)),
    MAX_RANGE_LEGS(5, new Item(18638, 1), new Item(11342, 1), 25, new Item(12855, 35000)),

    MAX_MAGE_STAFF(5, new Item(18629, 1), new Item(8089, 1), 22, new Item(12855, 35000)),
    MAX_MAGE_HELM(5, new Item(18631, 1), new Item(11421, 1), 25, new Item(12855, 35000)),
    MAX_MAGE_BODY(5, new Item(18637, 1), new Item(11422, 1), 25, new Item(12855, 35000)),
    MAX_MAGE_LEGS(5, new Item(18623, 1), new Item(11423, 1), 25, new Item(12855, 35000)),
    COLLECTOR_NECK(5, new Item(19886, 1), new Item(19888, 1), 25, new Item(12855, 35000)),
    COLLECTOR_RING(5, new Item(4446, 1), new Item(18823, 1), 25, new Item(12855, 35000)),
    AURA_T6(5, new Item(23049, 1), new Item(12630, 1), 30, new Item(12855, 10000000)),
    INSTANCE_TOKEN(5, new Item(4278, 1), new Item(23211, 1), 25, new Item(12855, 2500)),
    INV_LAMP(5, new Item(11137, 1), new Item(21218, 1), 20, new Item(12855, 10000)),
    EXECUTION_CAPE(5, new Item(1486, 1), new Item(9939, 1), 20, new Item(12855, 1000000)),

    ARRAV_COSTUME(5, new Item(23809, 1), new Item(23811, 1), 100, new Item(12855, 5000000)),
    ARRAV_SHIELD(5, new Item(23801, 1), new Item(23807, 1), 100, new Item(12855, 2500000)),


    RUBY_CASKET(5, new Item(15003, 1), new Item(15002, 1), 100, new Item(12855, 2000000)),
    DIAMOND_CASKET(5, new Item(15002, 2), new Item(15004, 1), 100, new Item(12855, 0)),
    ONYX_CASKET(5, new Item(15004, 2), new Item(23210, 1), 100, new Item(12855, 0)),
    ZENYTE_CASKET(5, new Item(23210, 2), new Item(23314, 1), 100, new Item(12855, 0)),

    ;

    private int tab;
    private Item required, reward, dustRequired;
    private int chance;

    UpgradeData(int tab, Item required, Item reward, int chance, Item dustRequired) {
        this.tab = tab;
        this.required = required;
        this.reward = reward;
        this.chance = chance;
        this.dustRequired = dustRequired;// 2 items on int
    }

    public static ArrayList<Item> getItems(int tab){
        ArrayList<Item> itemIds = new ArrayList<>();
        for (UpgradeData upgradeData : UpgradeData.values()){
            if (upgradeData.getTab() == tab){
                itemIds.add(upgradeData.getRequired());
            }
        }
        return itemIds;
    }

}
