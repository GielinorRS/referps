package com.ruse.world.content.raids;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum RaidDifficulty {

    EASY("Easy",
            new RaidRequirement(25_000)
    ),
    INTERMEDIATE("Medium",
            new RaidRequirement(50_000, 100)
    ),
    ADVANCED("Hard",
            new RaidRequirement(75_000, 250)
    );

    @Getter
    private String name;

    @Getter
    private RaidRequirement godsRequirement;

}
