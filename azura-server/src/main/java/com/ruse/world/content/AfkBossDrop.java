package com.ruse.world.content;

import com.ruse.model.definitions.NPCDrops;
import com.ruse.world.content.combat.CombatBuilder.CombatDamageCache;
import com.ruse.world.content.combat.CombatFactory;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.util.*;
import java.util.Map.Entry;

public class AfkBossDrop {

    public static void handleDrop(NPC npc) {
        //World.getPlayers().forEach(p -> p.getPacketSender().sendString(26707, "@or2@WildyWyrm: @gre@N/A"));
        if (npc.getCombatBuilder().getDamageMap().size() == 0) {
            return;
        }
        Map<Player, Long> killers = new HashMap<>();
        for (Entry<Player, CombatDamageCache> entry : npc.getCombatBuilder().getDamageMap().entrySet()) {
            if (entry == null) {
                continue;
            }
            long timeout = entry.getValue().getStopwatch().elapsed();
            if (timeout > CombatFactory.DAMAGE_CACHE_TIMEOUT) {
                continue;
            }
            Player player = entry.getKey();
            if (player.getConstitution() <= 0 || !player.isRegistered()) {
                continue;
            }
            killers.put(player, entry.getValue().getDamage());
        }
        npc.getCombatBuilder().getDamageMap().clear();
        List<Entry<Player, Long>> result = sortEntries(killers);
        int count = 0;
        for (Iterator<Entry<Player, Long>> iterator = result.iterator(); iterator.hasNext(); ) {
            Entry<Player, Long> entry = iterator.next();
            Player killer = entry.getKey();
            long damage = entry.getValue();

            NPCDrops.dropItems(killer, npc, true);
            iterator.remove();
            killer.sendMessage("You were rewarded for your contribution.");
        }
    }


    /**
     * @param map
     * @return
     */
    static <K, V extends Comparable<? super V>> List<Entry<K, V>> sortEntries(Map<K, V> map) {
        List<Entry<K, V>> sortedEntries = new ArrayList<Entry<K, V>>(map.entrySet());
        Collections.sort(sortedEntries, (e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        return sortedEntries;
    }
}
