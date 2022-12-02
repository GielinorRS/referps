package com.ruse.world.content.chambersOfAnima.boss;

import com.ruse.model.Position;
import com.ruse.world.content.chambersOfAnima.ChambersOfAnimaParty;
import com.ruse.world.content.combat.strategy.CombatStrategy;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.Setter;

public abstract class RaidBoss {

    @Getter
    @Setter
    private ChambersOfAnimaParty party;

    public RaidBoss(ChambersOfAnimaParty party, int npcId, long constitution) {
        setParty(party);
        this.npcId = npcId;
        this.originalHealth = constitution;
    }

    @Getter
    private final int npcId;
    @Getter
    private final long originalHealth;

    public abstract void spawn();
    public abstract CombatStrategy strategy();
    public abstract void onDeath(NPC npc);
    public abstract Position startPosition();

    public void onPlayerDeath(Player player) {
    }

}
