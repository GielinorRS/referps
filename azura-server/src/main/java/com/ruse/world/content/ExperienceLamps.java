package com.ruse.world.content;

import com.ruse.model.Difficulty;
import com.ruse.model.Item;
import com.ruse.model.Skill;
import com.ruse.util.Misc;
import com.ruse.world.content.dialogue.Dialogue;
import com.ruse.world.content.dialogue.DialogueExpression;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.content.dialogue.DialogueType;
import com.ruse.world.content.skill.SkillManager;
import com.ruse.world.content.startertasks.StarterTasks;
import com.ruse.world.entity.impl.player.Player;

public class ExperienceLamps {

    public static boolean handleLamp(Player player, int item) {
        LampData lamp = LampData.forId(item);
        if (lamp == null)
            return false;
        if (player.getInterfaceId() > 0) {
            player.getPacketSender().sendMessage("Please close the interface you have open before doing this.");
        } else {
            player.getPacketSender().sendString(38006, "Choose XP type...").sendString(38090,
                    "What sort of XP would you like?");
            player.getPacketSender().sendInterface(38000);
            player.setUsableObject(new Object[3]).setUsableObject(0, "xp").setUsableObject(2, lamp);
        }
        return true;
    }

    public static boolean handleButton(Player player, int button) {
        return handleButton(player, button, false);
    }

    public static boolean handleButton(Player player, int button, boolean use) {
        if (button == -27451) {
            try {
                player.getPacketSender().sendInterfaceRemoval();
                player.getPacketSender().sendString(38006, "Choose XP type...");
                if (player.getUsableObject()[0] != null) {
                    Skill skill = (Skill) player.getUsableObject()[1];
                    switch (((String) player.getUsableObject()[0]).toLowerCase()) {
                        case "reset":
                            player.getSkillManager().resetSkill(skill, false);
                            break;
                        case "prestige":
                            player.getSkillManager().resetSkill(skill, true);
                            break;
                        case "xp":
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
                                    return new String[]{"Use 1", "Use 5", "Use 10", "Use All", "Use X"};
                                }

                                @Override
                                public void specialAction() {
                                    player.setDialogueActionId(4419);
                                    player.setUseLamp(use);
                                }
                            });
                            break;
                    }
                }
            } catch (Exception e) {
            }
            return true;
        } else {
            Interface_Buttons interfaceButton = Interface_Buttons.forButton(button);
            if (interfaceButton == null)
                return false;
            if (interfaceButton == Interface_Buttons.CONSTRUCTION) {
                player.getPacketSender().sendMessage("That skill is not trainable yet.");
                return true;
            }
            Skill skill = Skill.forName(interfaceButton.toString());
            if (skill == null)
                return true;
            player.setUsableObject(1, skill);
            player.getPacketSender().sendString(38006, Misc.formatText(interfaceButton.toString().toLowerCase()));
            boolean prestige = player.getUsableObject()[0] != null && player.getUsableObject()[0] instanceof String
                    && ((String) (player.getUsableObject()[0])).equals("prestige");
            if (prestige) {
                int pts = SkillManager.getPrestigePoints(player, skill);
                player.getPacketSender().sendMessage("<img=5> <col=996633>You will receive " + pts + " Prestige point"
                        + (pts > 1 ? "s" : "") + " if you prestige in " + skill.getFormatName() + ".");
            }
        }
        return false;
    }

    public static enum LampData {
        NORMAL_XP_LAMP(11137), DRAGONKIN_LAMP(18782), GOLDENLAMP_LAMP(3907);

        LampData(int itemId) {
            this.itemId = itemId;
        }

        private int itemId;

        public int getItemId() {
            return this.itemId;
        }

        public static LampData forId(int id) {
            for (LampData lampData : LampData.values()) {
                if (lampData != null && lampData.getItemId() == id)
                    return lampData;
            }
            return null;
        }
    }

    enum Interface_Buttons {

        ATTACK(-27529), MAGIC(-27526), MINING(-27523), WOODCUTTING(-27520), AGILITY(-27517), FLETCHING(-27514),
        THIEVING(-27511), STRENGTH(-27508), RANGED(-27505), SMITHING(-27502), FIREMAKING(-27499), HERBLORE(-27496),
        SLAYER(-27493), CONSTRUCTION(-27490), DEFENCE(-27487), PRAYER(-27484), FISHING(-27481), CRAFTING(-27478),
        FARMING(-27475), HUNTER(-27472), SUMMONING(-27469), CONSTITUTION(-27466), DUNGEONEERING(-27463),
        COOKING(-27460), RUNECRAFTING(-27457);

        Interface_Buttons(int button) {
            this.button = button;
        }

        private int button;

        public static Interface_Buttons forButton(int button) {
            for (Interface_Buttons skill : Interface_Buttons.values()) {
                if (skill != null && skill.button == button) {
                    return skill;
                }
            }
            return null;
        }
    }

    public static int getExperienceReward(Player player, LampData lamp, Skill skill) {
        int base = lamp == LampData.GOLDENLAMP_LAMP ? (int) (250000 / Difficulty.getDifficultyModifier(player, skill))
                : (int) (100000 / Difficulty.getDifficultyModifier(player, skill));
        int maxLvl = player.getSkillManager().getMaxLevel(skill);
        return (int) (base + maxLvl);
    }

    public static boolean selectingExperienceReward(Player player) {
        return player.getInterfaceId() == 38000;
    }
}
