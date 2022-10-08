package com.ruse.world.content.weightedRandom;

import com.ruse.util.Misc;

import java.security.SecureRandom;
import java.util.NavigableMap;
import java.util.TreeMap;

public class WeightedSystem<E> {

    private final NavigableMap<Double, E> map = new TreeMap<>();
    private double total = 0;
    private final SecureRandom random;

    public WeightedSystem(SecureRandom random) {
        this.random = random;
    }

    public WeightedSystem<E> add(double weight, E result) {
        if (weight <= 0) return this;
        total += weight;
        map.put(total, result);
        return this;
    }

    public WeightedSystem<E> clear() {
        if(total <= 0)
            return this;
        total = 0;
        map.clear();
        return this;
    }

    public E next() {
        double value = random.nextDouble() * total;
        return map.higherEntry(value).getValue();
    }

    public WeightedSystem() {
        this(Misc.SECURE_RANDOM);
    }
}