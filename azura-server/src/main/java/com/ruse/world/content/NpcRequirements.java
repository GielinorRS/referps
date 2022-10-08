package com.ruse.world.content;

public enum NpcRequirements {

    MEGAAVATAR(4540, 250000),


    SPAWN(1614, 50),
    LORD(603, 1614, 100),
    DEMON(12843, 603, 200),
    DRAGON(53, 12843, 300),
    BEAST(8018, 53, 400),
    TROLLKING(13635, 8018, 500),
    AVATAR(8008, 13635, 1000),
    ANGEL(3308, 8008, 1200),
    LUCIEN(3117, 3308, 1500),
    HERCULES(201, 3117, 2500),
    SATAN(202, 201, 3500),
    ZEUS(203, 202, 5000),

    VASA(1120, 202, 25000),

    GROUDON(8010, 203, 15000),

    FENRIR(9810, 8010, 25000),
    FENRIR1(9811, 8010, 25000),
    FENRIR2(9812, 8010, 25000),

    DARK_PRYSM(9891, 9890, 50000),

    SHADOW_SHARK(9899, 9898, 50000),

    KRIL(6203, 6208, 50000),
    SPIRIT(10027, 10026, 75000),

    TARANIS(11872, 11874, 75000),

    SILVERHAWK(10030, 10032, 75000),

    BLOOD_ODIN(9813, 438, 10000),

    ;

    private int npcId;
    private int requireNpcId;
    private int amountRequired;
    private int killCount;

    NpcRequirements(int npcId, int requireNpcId, int amountRequired) {
        this.npcId = npcId;
        this.requireNpcId = requireNpcId;
        this.amountRequired = amountRequired;
        this.killCount = 0;
    }

    NpcRequirements(int npcId, int killCount) {
        this.npcId = npcId;
        this.killCount = killCount;
    }

    public int getNpcId() {
        return npcId;
    }

    public int getKillCount() {
        return killCount;
    }

    public int getRequireNpcId() {
        return requireNpcId;
    }

    public int getAmountRequired() {
        return amountRequired;
    }

}