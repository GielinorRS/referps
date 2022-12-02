package com.ruse.world.content;

import com.ruse.model.Item;
import com.ruse.model.Position;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.combat.CombatBuilder.CombatDamageCache;
import com.ruse.world.content.combat.CombatFactory;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.util.*;
import java.util.Map.Entry;

public class GokuBossDrop {

    public static void handleDrop(NPC npc) {
        GokuSystem.alive = false;
    }

}
