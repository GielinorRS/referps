package com.ruse.world.content;

import com.ruse.model.Item;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.world.content.dialogue.Dialogue;
import com.ruse.world.content.dialogue.DialogueExpression;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.content.dialogue.DialogueType;
import com.ruse.world.entity.impl.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class Disassembling {
    private Player player;

    public Disassembling(Player player) {
        this.player = player;
    }

    @Getter
    @Setter
    private Disassemble currentDisassemble;

    @Getter
    @Setter
    private int currentDisassembleAmount = 0;

    @Getter
    @AllArgsConstructor
    public enum Disassemble {
        LIGHT_ATTACH(10949, new Item[]{new Item(23224, 3)}),
        DARK_ATTACH(22112, new Item[]{new Item(23517, 3)}),
        BLOOD_ATTACH(23223, new Item[]{new Item(23518, 3)}),


        HANTO_HELM(23561, new Item[]{new Item(23569, 50)}),
        HANTO_BODY(23562, new Item[]{new Item(23569, 50)}),
        HANTO_LEGS(23563, new Item[]{new Item(23569, 50)}),
        HANTO_GLOVES(23564, new Item[]{new Item(23569, 50)}),
        HANTO_BOOTS(23565, new Item[]{new Item(23569, 50)}),
        HANTO_WEP1(23566, new Item[]{new Item(23569, 50)}),
        HANTO_WEP2(23567, new Item[]{new Item(23569, 50)}),
        HANTO_WEP3(23568, new Item[]{new Item(23569, 50)}),


        DYE1(23009, new Item[]{new Item(11027, 40)}),
        DYE2(23010, new Item[]{new Item(11027, 40)}),
        DYE3(23011, new Item[]{new Item(11027, 40)}),
        DYE4(23012, new Item[]{new Item(11027, 40)}),
        BUNNYMASK(23013, new Item[]{new Item(11027, 40)}),

        LUNITE_PIECE_1(23394, new Item[]{new Item(23456, 50)}),
        LUNITE_PIECE_2(23395, new Item[]{new Item(23456, 50)}),
        LUNITE_PIECE_3(23396, new Item[]{new Item(23456, 50)}),
        LUNITE_PIECE_4(23397, new Item[]{new Item(23456, 50)}),
        LUNITE_PIECE_5(23398, new Item[]{new Item(23456, 50)}),
        LUNITE_PIECE_6(23399, new Item[]{new Item(23456, 50)}),

        RAMMERNAUT_FLAKES(23759, new Item[]{new Item(23736, 25)}),

        MELEE_CREST_LIGHT(23482, new Item[]{new Item(12537, 1)}),
        RANGED_CREST_LIGHT(23483, new Item[]{new Item(17013, 1)}),
        MAGIC_CREST_LIGHT(23484, new Item[]{new Item(5011, 1)}),

        MELEE_CREST_DARK(23485, new Item[]{new Item(22115, 1)}),
        RANGED_CREST_DARK(23486, new Item[]{new Item(22114, 1)}),
        MAGIC_CREST_DARK(23487, new Item[]{new Item(22113, 1)}),

        MELEE_CREST_BLOOD(23488, new Item[]{new Item(23221, 1)}),
        RANGED_CREST_BLOOD(23489, new Item[]{new Item(23220, 1)}),
        MAGIC_CREST_BLOOD(23490, new Item[]{new Item(23222, 1)}),


        ;

        private int id;
        private Item[] rewards;
    }

    public void handle(int id, int amount) {
        for (Disassemble data : Disassemble.values()) {
            if (data.getId() == id) {
                DialogueManager.start(player, new Dialogue() {

                    @Override
                    public DialogueType type() {
                        return DialogueType.OPTION;
                    }

                    @Override
                    public DialogueExpression animation() {
                        return null;
                    }

                    @Override
                    public String[] dialogue() {
                        return new String[]{"Disassemble for x" + (data.getRewards()[0].getAmount() * amount) + " " +
                                ItemDefinition.forId(data.getRewards()[0].getId()).getName() + ((data.getRewards()[0].getAmount() * amount) > 1 ?  "s":""), "Cancel"};
                    }

                    @Override
                    public void specialAction() {
                        player.setDialogueActionId(99122);
                        setCurrentDisassembleAmount(amount);
                        setCurrentDisassemble(data);
                    }

                });
                break;
            }
        }

    }


}

/*
 * private void handleProgressions(int[][] progressions) { for(int[] progression
 * : progressions) {
 * player.getProgressionManager().getProgressionTask(progression[0]).
 * incrementProgress(progression[1], progression[2]); } }
 *
 * }
 */
