package com.ruse.world.content.raids;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RaidRequirement {

    private int cost, previousDifficultyAmount;

    public RaidRequirement(int cost) {
        this.cost = cost;
    }

}
