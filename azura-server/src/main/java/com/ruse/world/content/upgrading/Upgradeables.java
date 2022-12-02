package com.ruse.world.content.upgrading;

import com.ruse.model.Item;
import lombok.Getter;

import java.util.ArrayList;

import static com.ruse.world.content.upgrading.Upgradeables.UpgradeType.*;

@Getter
public enum Upgradeables {

    HAT(TIER1,  new Item(15005, 1), new Item(4977, 1), 500000, 80),
    HAT1(TIER1, new Item(15006, 1), new Item(4989, 1), 500000, 80),
    HAT2(TIER1, new Item(15007, 1), new Item(4995, 1), 500000, 80),
    HAT3(TIER1, new Item(15200, 1), new Item(4977, 1), 500000, 80),
    HAT4(TIER1, new Item(15201, 1), new Item(4977, 1), 500000, 80),
    HAT5(TIER1, new Item(15100, 1), new Item(4977, 1), 500000, 80),
    HAT6(TIER1, new Item(11421, 1), new Item(1050, 1), 500000, 80),
    HAT7(TIER1, new Item(11422, 1), new Item(1050, 1), 500000, 80),
    HAT8(TIER1, new Item(11423, 1), new Item(1050, 1), 500000, 80),
    HAT9(TIER1, new Item(19164, 1), new Item(1050, 1), 500000, 80),
    HAT10(TIER1, new Item(19163, 1), new Item(1050, 1), 500000, 80),
    HAT11(TIER1, new Item(19161, 1), new Item(1050, 1), 500000, 80),

    WHIP(TIER2, new Item(4977, 1), new Item(1050, 1), 100000, 60),
    WHIP1(TIER2, new Item(4989, 1), new Item(1050, 1), 100000, 60),
    WHIP2(TIER2, new Item(4995, 1), new Item(1050, 1), 100000, 60),
    WHIP3(TIER2, new Item(19909, 1), new Item(1050, 1), 100000, 60),
    WHIP4(TIER2, new Item(7640, 1), new Item(1050, 1), 100000, 60),
    WHIP5(TIER2, new Item(20057, 1), new Item(1050, 1), 100000, 60),
    WHIP6(TIER2, new Item(18785, 1), new Item(1050, 1), 100000, 60),
    WHIP7(TIER2, new Item(18802, 1), new Item(1050, 1), 100000, 60),
    WHIP8(TIER2, new Item(21028, 1), new Item(1050, 1), 100000, 60),
    WHIP9(TIER2, new Item(21029, 1), new Item(1050, 1), 100000, 60),
    WHIP10(TIER2, new Item(21030, 1), new Item(1050, 1), 100000, 60),
    WHIP11(TIER2, new Item(14060, 1), new Item(1050, 1), 100000, 60),
    WHIP12(TIER2, new Item(14061, 1), new Item(1050, 1), 100000, 60),
    WHIP13(TIER2, new Item(14062, 1), new Item(1050, 1), 100000, 60),
    WHIP14(TIER2, new Item(5010, 1), new Item(1050, 1), 100000, 60),
    WHIP15(TIER2, new Item(4684, 1), new Item(1050, 1), 100000, 60),
    WHIP16(TIER2, new Item(4685, 1), new Item(1050, 1), 100000, 60),
    WHIP17(TIER2, new Item(4686, 1), new Item(1050, 1), 100000, 60),
    WHIP18(TIER2, new Item(18840, 1), new Item(1050, 1), 100000, 60),
    WHIP19(TIER2, new Item(18837, 1), new Item(1050, 1), 100000, 60),
    WHIP20(TIER2, new Item(18836, 1), new Item(1050, 1), 100000, 60),

    RING(TIER3, new Item(23050, 1), new Item(1050, 1),  2500000, 40),
    RING1(TIER3, new Item(23051, 1), new Item(1050, 1),  2500000, 40),
    RING2(TIER3, new Item(23052, 1), new Item(1050, 1),  2500000, 40),
    RING3(TIER3, new Item(23055, 1), new Item(1050, 1),  2500000, 40),
    RING4(TIER3, new Item(23056, 1), new Item(1050, 1),  2500000, 40),
    RING5(TIER3, new Item(23053, 1), new Item(1050, 1),  2500000, 40),
    RING6(TIER3, new Item(23054, 1), new Item(1050, 1),  2500000, 40),
    RING7(TIER3, new Item(23061, 1), new Item(1050, 1),  2500000, 40),
    RING8(TIER3, new Item(23062, 1), new Item(1050, 1),  2500000, 40),
    RING9(TIER3, new Item(23063, 1), new Item(1050, 1), 2500000, 40),
    RING10(TIER3, new Item(23066, 1), new Item(1050, 1), 2500000, 40),
    RING11(TIER3, new Item(23067, 1), new Item(1050, 1), 2500000, 40),
    RING12(TIER3, new Item(23068, 1), new Item(1050, 1), 2500000, 40),
    RING13(TIER3, new Item(23075, 1), new Item(1050, 1), 2500000, 40),
    RING14(TIER3, new Item(23076, 1), new Item(1050, 1), 2500000, 40),
    RING15(TIER3, new Item(23077, 1), new Item(1050, 1), 2500000, 40),
    RING16(TIER3, new Item(17600, 1), new Item(1050, 1), 2500000, 40),
    RING17(TIER3, new Item(23175, 1), new Item(1050, 1), 2500000, 40),
    RING18(TIER3, new Item(23092, 1), new Item(1050, 1), 2500000, 40),
    RING19(TIER3, new Item(23094, 1), new Item(1050, 1), 2500000, 40),
    RING20(TIER3, new Item(23093, 1), new Item(1050, 1), 2500000, 40),
    RING21(TIER3, new Item(23096, 1), new Item(1050, 1), 2500000, 40),
    RING222(TIER3, new Item(23097, 1), new Item(1050, 1), 2500000, 40),
    RING23(TIER3, new Item(23098, 1), new Item(1050, 1), 2500000, 40),

    IDD(TIER4, new Item(23021, 1), new Item(1050, 1), 5000000, 20),
    IDD1(TIER4, new Item(23022, 1), new Item(1050, 1), 5000000, 20),
    IDD2(TIER4, new Item(23023, 1), new Item(1050, 1), 5000000, 20),
    IDD3(TIER4, new Item(23026, 1), new Item(1050, 1), 5000000, 20),
    IDD4(TIER4, new Item(23024, 1), new Item(1050, 1),  5000000, 20),
    IDD5(TIER4, new Item(23025, 1), new Item(1050, 1), 5000000, 20),
    IDD6(TIER4, new Item(23028, 1), new Item(1050, 1), 5000000, 20),
    IDD7(TIER4, new Item(23029, 1), new Item(1050, 1), 5000000, 20),
    IDD8(TIER4, new Item(23030, 1), new Item(1050, 1), 5000000, 20),
    IDD9(TIER4, new Item(23033, 1), new Item(1050, 1), 5000000, 20),
    IDD10(TIER4, new Item(23031, 1), new Item(1050, 1), 5000000, 20),
    IDD11(TIER4, new Item(23032, 1), new Item(1050, 1), 5000000, 20),
    IDD12(TIER4, new Item(23034, 1), new Item(1050, 1), 5000000, 20),
    IDD13(TIER4, new Item(23035, 1), new Item(1050, 1), 5000000, 20),
    IDD14(TIER4, new Item(23036, 1), new Item(1050, 1), 5000000, 20),
    IDD15(TIER4, new Item(23039, 1), new Item(1050, 1), 5000000, 20),
    IDD16(TIER4, new Item(23037, 1), new Item(1050, 1), 5000000, 20),
    IDD17(TIER4, new Item(23038, 1), new Item(1050, 1), 5000000, 20),

    ;

    private UpgradeType type;
    private Item required, reward;
    private int cost, successRate;
    private boolean rare;

    Upgradeables(UpgradeType type, Item required, Item reward, int cost, int successRate, boolean rare) {
        this.type = type;
        this.required = required;
        this.reward = reward;
        this.cost = cost;
        this.successRate = successRate;
        this.rare = rare;
    }

    Upgradeables(UpgradeType type, Item required, Item reward, int cost, int successRate) {
        this.type = type;
        this.required = required;
        this.reward = reward;
        this.cost = cost;
        this.successRate = successRate;
        this.rare = false;
    }
    
    public static ArrayList<Upgradeables> getForType(UpgradeType type){
        ArrayList<Upgradeables> upgradeablesArrayList = new ArrayList<>();
        for (Upgradeables upgradeables : values()){
            if (upgradeables.getType() == type){
                upgradeablesArrayList.add(upgradeables);
            }
        }
        return upgradeablesArrayList;
    }
    

    public enum UpgradeType{

        TIER1, TIER2, TIER3, TIER4;

    }


}
