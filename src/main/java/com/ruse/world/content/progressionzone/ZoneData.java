package com.ruse.world.content.progressionzone;

import com.ruse.model.Item;
import com.ruse.model.Position;
import com.ruse.util.Misc;
import lombok.Getter;

public class ZoneData {

    public enum Monsters {

        PHASE_1(9001, 10, new Item[]{new Item(22077), new Item(19136), new Item(6936)}),
        PHASE_2(9002, 20, new Item[]{new Item(12855,10000), new Item(7956, 10), new Item(19116, 3)}),
        PHASE_3(9003, 30, new Item[]{new Item(19115, 3), new Item(10025, 3), new Item(19114, 3)}),
        PHASE_4(9004, 50, new Item[]{new Item(6833), new Item(14018), new Item(989, 50)}),
        PHASE_5(9005, 100, new Item[]{new Item(15288, 1), new Item(5022, 50000), new Item(19000, 100)}),
        FINAL_PHASE(9006, 200, new Item[]{new Item(8334), new Item(19892), new Item(8335)});

        @Getter
        private int npcId;
        @Getter
        private int amountToKill;
        @Getter
        private Item[] rewards;

        Monsters(int npcId, int amountToKill, Item[] rewards) {
            this.npcId = npcId;
            this.amountToKill = amountToKill;
            this.rewards = rewards;
        }

        public static Monsters forID(int npcId) {
            for (Monsters monster : Monsters.values()) {
                if (monster.getNpcId() == npcId) {
                    return monster;
                }
            }
            return null;
        }

        public String getName() {
            return Misc.ucFirst(name().toLowerCase().replace("_", " "));
        }

        public Position getCoords() {
            return new Position(2782, 9487 + (ordinal() * 23));
        }
    }

}
