package com.ruse.world.entity.updating;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Phaser;

import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.World;
import com.ruse.world.content.events.PartyChest;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.npc.NpcAggression;
import com.ruse.world.entity.impl.player.Player;

public class PlayerUpdateSequence implements UpdateSequence<Player> {

	/** Used to block the game thread until updating is completed. */
	private final Phaser synchronizer;
	/** The thread pool that will update players in parallel. */
	private final ExecutorService updateExecutor;

	/**
	 * Create a new {@link PlayerUpdateSequence}.
	 *
	 * @param synchronizer   used to block the game thread until updating is
	 *                       completed.
	 * @param updateExecutor the thread pool that will update players in parallel.
	 */
	public PlayerUpdateSequence(Phaser synchronizer, ExecutorService updateExecutor) {
		this.synchronizer = synchronizer;
		this.updateExecutor = updateExecutor;
	}

	@Override
	public void executePreUpdate(Player t) {
		try {
			long lastTime = System.currentTimeMillis();
			long firstTime = System.currentTimeMillis();
			boolean sessionlag = false;

			t.getSession().handleQueuedMessages();
			if (t!= null && System.currentTimeMillis() - firstTime > 75) {
				sessionlag=  true;
				firstTime = System.currentTimeMillis();
			}

			t.process();
			if (t!= null && System.currentTimeMillis() - firstTime > 75) {
				DiscordMessager.sendWebhook(t.getUsername() + " - " + (System.currentTimeMillis() - firstTime) + " ms - process",
						Color.YELLOW, "https://discord.com/api/webhooks/968167481862684723/dUA3FcKteTGjmuG3EgHEIquHVyLL4cxtF3Ee8cAyCCqmg3larFWO_Wtk3raVi4JrztwP");
				firstTime = System.currentTimeMillis();
			}
			if (t.getWalkToTask() != null)
				t.getWalkToTask().tick();


				if (t!= null && System.currentTimeMillis() - firstTime > 75) {
					DiscordMessager.sendWebhook(t.getUsername() + " - " + (System.currentTimeMillis() - firstTime) + " ms - walking",
							Color.YELLOW, "https://discord.com/api/webhooks/968167481862684723/dUA3FcKteTGjmuG3EgHEIquHVyLL4cxtF3Ee8cAyCCqmg3larFWO_Wtk3raVi4JrztwP");
					firstTime = System.currentTimeMillis();
				}

			t.getMovementQueue().sequence();

			if (t!= null && System.currentTimeMillis() - firstTime > 75) {
				DiscordMessager.sendWebhook(t.getUsername() + " - " + (System.currentTimeMillis() - firstTime) + " ms - movement",
						Color.YELLOW, "https://discord.com/api/webhooks/968167481862684723/dUA3FcKteTGjmuG3EgHEIquHVyLL4cxtF3Ee8cAyCCqmg3larFWO_Wtk3raVi4JrztwP");
				firstTime = System.currentTimeMillis();
			}

			PartyChest.updateRequired = false;
			NpcAggression.target(t);


			if (t!= null && System.currentTimeMillis() - lastTime > 100 && !sessionlag) {
				System.out.println(t.getUsername() + " - time took: " + (System.currentTimeMillis() - lastTime) + " ms");

				if (System.currentTimeMillis() - lastTime > 200){
					World.sendStaffMessage("<col=FF0066><img=2> [PLAYER-LAG]<col=6600FF> " + t.getUsername()
							+ " created lag @red@" + (System.currentTimeMillis() - lastTime) + "ms <col=6600FF>- Message Nucky on Discord");
					DiscordMessager.sendWebhook(t.getUsername() + " - " + (System.currentTimeMillis() - lastTime) + " ms <@782919638291447819>",
							Color.RED, "https://discord.com/api/webhooks/968167481862684723/dUA3FcKteTGjmuG3EgHEIquHVyLL4cxtF3Ee8cAyCCqmg3larFWO_Wtk3raVi4JrztwP");
				}else if (t!= null && System.currentTimeMillis() - lastTime > 100) {
					DiscordMessager.sendWebhook(t.getUsername() + " - " + (System.currentTimeMillis() - lastTime) + " ms",
							Color.YELLOW, "https://discord.com/api/webhooks/968167481862684723/dUA3FcKteTGjmuG3EgHEIquHVyLL4cxtF3Ee8cAyCCqmg3larFWO_Wtk3raVi4JrztwP");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			World.deregister(t);
		}
	}

	@Override
	public void executeUpdate(Player t) {
		updateExecutor.execute(() -> {
			try {
				synchronized (t) {
					PlayerUpdating.update(t);
					NPCUpdating.update(t);
				}
			} catch (Exception e) {
				e.printStackTrace();
				World.deregister(t);
			} finally {
				synchronizer.arriveAndDeregister();
			}
		});
	}

	@Override
	public void executePostUpdate(Player t) {
		try {
			PlayerUpdating.resetFlags(t);
		} catch (Exception e) {
			e.printStackTrace();
			World.deregister(t);
		}
	}
}
