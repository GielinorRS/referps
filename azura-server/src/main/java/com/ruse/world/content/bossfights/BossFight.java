package com.ruse.world.content.bossfights;

import com.ruse.world.content.combat.strategy.CombatStrategy;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public abstract class BossFight {

    @Getter
    private final Player player;

    @Getter
    @Setter
    private NPC currentNpc;

    public abstract int npcId();

    public abstract void begin();

    public abstract void onDeath(NPC npc);

    public abstract void onPlayerDeath();

    public abstract void endFight();

    public abstract CombatStrategy bossStrategy();

    @Getter
    @Setter
    private int currentStage = 0;

    @Getter
    @Setter
    private int myHeight = 0;

    @Getter
    private List<NPC> npcList = new ArrayList<>();

    @Getter
    @Setter
    private static int globalHeight = 0;


    }