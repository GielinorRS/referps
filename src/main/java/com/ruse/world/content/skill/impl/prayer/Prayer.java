package com.ruse.world.content.skill.impl.prayer;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.Animation;
import com.ruse.model.Item;
import com.ruse.model.Skill;
import com.ruse.world.content.AchievementsOLD;
import com.ruse.world.content.Sounds;
import com.ruse.world.content.AchievementsOLD.AchievementDataOLD;
import com.ruse.world.content.Sounds.Sound;
import com.ruse.world.content.achievement.Achievements;
import com.ruse.world.entity.impl.player.Player;

/**
 * The prayer skill is based upon burying the corpses of enemies. Obtaining a
 * higher level means more prayer abilities being unlocked, which help out in
 * combat.
 * 
 * @author Gabriel Hannason
 */

public class Prayer {

	public static boolean isBone(int bone) {
		return BonesData.forId(bone) != null;
	}

	public static void buryBone(final Player player, final int itemId) {
		if (!player.getClickDelay().elapsed(2000))
			return;
		final BonesData currentBone = BonesData.forId(itemId);
		if (currentBone == null)
			return;
		player.getSkillManager().stopSkilling();
		player.getPacketSender().sendInterfaceRemoval();
		player.performAnimation(new Animation(827));
		player.getPacketSender().sendMessage("You dig a hole in the ground..");
		final Item bone = new Item(itemId);
		player.getInventory().delete(bone);


		TaskManager.submit(new Task(3, player, false) {
			@Override
			public void execute() {
				player.getPacketSender().sendMessage("..and bury the " + bone.getDefinition().getName() + ".");
				player.getSkillManager().addExperience(Skill.PRAYER, currentBone.getBuryingXP());
				Sounds.sendSound(player, Sound.BURY_BONE);
				Achievements.doProgress(player, Achievements.Achievement.PRAYER);
				Achievements.doProgress(player, Achievements.Achievement.PRAYER_RITUAL);
				Achievements.doProgress(player, Achievements.Achievement.PRAYER_DEVOTION);
				if (currentBone.equals(BonesData.FROSTDRAGON_BONES)) {
					Achievements.doProgress(player, Achievements.Achievement.BURY_ALOT);
				}
				if (currentBone == BonesData.BIG_BONES)
					AchievementsOLD.finishAchievement(player, AchievementDataOLD.BURY_A_BIG_BONE);
				if (currentBone == BonesData.DRAGON_BONES)
					AchievementsOLD.finishAchievement(player, AchievementDataOLD.BURY_A_DRAGON_BONE);
				else if (currentBone == BonesData.FROSTDRAGON_BONES) {

					AchievementsOLD.doProgress(player, AchievementDataOLD.BURY_25_FROST_DRAGON_BONES);
					AchievementsOLD.doProgress(player, AchievementDataOLD.BURY_500_FROST_DRAGON_BONES);
				}
				stop();
			}
		});
		player.getClickDelay().reset();
	}
}
