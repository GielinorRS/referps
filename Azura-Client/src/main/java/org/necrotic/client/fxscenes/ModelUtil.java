package org.necrotic.client.fxscenes;


import org.necrotic.client.cache.definition.ItemDefinition;
import org.necrotic.client.world.Model;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class ModelUtil {

    public static ItemDefinition itemDef;


    public static Set<Entry<Integer, AtomicInteger>> getModelColors(Model model) {
        final TreeMap<Integer, AtomicInteger> weightedColors = new TreeMap<Integer, AtomicInteger>();

        for (int color : model.face_color) {
            weightedColors.putIfAbsent(color, new AtomicInteger(0));
            weightedColors.get(color).incrementAndGet();
        }
        return weightedColors.entrySet().stream().sorted(Comparator.comparingInt(entry -> entry.getValue().get()))
                .collect(Collectors.toSet());
    }

    public static Set<Entry<Integer, AtomicInteger>> getModelColors(Model model, Model model2) {
        final TreeMap<Integer, AtomicInteger> weightedColors = new TreeMap<Integer, AtomicInteger>();

        for (int color : model.face_color) {
            weightedColors.putIfAbsent(color, new AtomicInteger(0));
            weightedColors.get(color).incrementAndGet();
        }
        for (int color : model2.face_color) {
            weightedColors.putIfAbsent(color, new AtomicInteger(0));
            weightedColors.get(color).incrementAndGet();
        }
        return weightedColors.entrySet().stream().sorted(Comparator.comparingInt(entry -> entry.getValue().get()))
                .collect(Collectors.toSet());
    }

}
