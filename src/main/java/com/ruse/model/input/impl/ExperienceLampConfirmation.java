package com.ruse.model.input.impl;

import com.ruse.model.Skill;
import com.ruse.model.input.Input;
import com.ruse.util.Misc;
import com.ruse.world.content.ExperienceLamps;
import com.ruse.world.content.startertasks.StarterTasks;
import com.ruse.world.entity.impl.player.Player;

public class ExperienceLampConfirmation extends Input {

    @Override
    public void handleSyntax(Player player, String syntax) {
        if (syntax.equalsIgnoreCase("yes")) {
            int lampAmount = player.getLampsToUse();
            if (lampAmount <= 0) {
                player.sendMessage("Please try again!");
                return;
            }
            System.out.println("here");
            Skill skill = (Skill) player.getUsableObject()[1];
            ExperienceLamps.LampData lamp = (ExperienceLamps.LampData) player.getUsableObject()[2];
            if (player.getInventory().getAmount(lamp.getItemId()) < lampAmount) {
                player.sendMessage("You do not have this many experience lamps in your inventory!");
                player.getPA().sendInterfaceRemoval();
                return;
            }
            StarterTasks.doProgress(player, StarterTasks.StarterTask.USE_LAMPS);

            int exp = ExperienceLamps.getExperienceReward(player, lamp, skill);

            player.getInventory().delete(lamp.getItemId(), lampAmount);
            player.getSkillManager().addExperience(skill, exp * lampAmount);

            player.getPacketSender().sendMessage("You've received some experience in "
                    + Misc.formatText(skill.toString().toLowerCase()) + ".");
            player.getProgressionManager().getProgressionTask(2).incrementProgress(0, lampAmount);
            player.getProgressionManager().getProgressionTask(6).incrementProgress(2, lampAmount);
        } else if (syntax.equalsIgnoreCase("no")) {
            player.getPA().sendInterfaceRemoval();
            player.setLampsToUse(-1);
        } else {
            player.sendMessage("Please enter yes or no!");
            player.setLampsToUse(-1);
        }
        player.getPA().sendInterfaceRemoval();
    }

}