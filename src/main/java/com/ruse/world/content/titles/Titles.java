package com.ruse.world.content.titles;

import com.ruse.model.Item;
import com.ruse.model.definitions.NpcDefinition;
import com.ruse.util.Misc;
import com.ruse.world.content.collectionlogs.CollectionLogs;
import lombok.Getter;

import java.util.ArrayList;

import static com.ruse.world.content.titles.Titles.TitleType.*;

@Getter
public enum Titles {

    NONE(RESET, "",  new String[]{"", ""}),

    //LOYALTY
    GOOD(LOYALTY, "<col=C86400>Good", 5000),
    EVIL(LOYALTY, "<col=C86400>Evil", 5000),
    BARONESS(LOYALTY, "<col=C86400>Baroness", 10000),
    BARON(LOYALTY, "<col=C86400>Baron", 10000),
    DUCHESS(LOYALTY, "<col=C86400>Duchess", 50000),
    DUKE(LOYALTY, "<col=C86400>Duke", 50000),
    LORD(LOYALTY, "<col=C86400>Lord", 100000),
    LADY(LOYALTY, "<col=C86400>Lady", 100000),
    QUEEN(LOYALTY, "<col=C86400>Queen", 250000),
    KING(LOYALTY, "<col=C86400>King", 250000),
    LOYAL(LOYALTY, "<col=ca3600>the Loyal##", 1000000),

    // BOSSES
    SCARLET(BOSS, "<col=F8371C>the Falcon##", 2949, 15000),
    HERBAL(BOSS, "<col=0AB807>Rogue", 2342,15000),
    AZURE(BOSS, "<col=0156BC>Azure", 3831,15000),
    JOKER(BOSS, "<col=BB14F0>the Joker##", 185, 25000),
    CRYSTAL(BOSS, "<col=67FFEC>Crystal", 6430, 25000),
    SUPREME(BOSS, "<col=ABE5D9>Supreme", 440, 30000),
    VASA(BOSS, "<col=6C6B6D>the Ancient##", 1120, 50000),
    ELITE(BOSS, "<col=A8D7DB>the Elite##", 8015, 50000),
    MEGA(BOSS, "<col=1C5BEC>the Cursed##", 4540, 50000),
    INFERNAL(BOSS, "<col=AB3E35>the Infernal##", 12810, 25000),
    LUCIFER(BOSS, "<col=6E1717>the Satanic##", 9012, 50000),
    DARK(BOSS, "<col=110303>Dark", 438, 50000),
    ODIN(BOSS, "<col=E14A4A>the All-Father##", 9813, 100000),
    SARA(BOSS, "<col=C3DEE9>of Saradomin##", 10000, 5000),
    ZAMMY(BOSS, "<col=FF381C>of Zamorak##", 10001, 5000),

    //MISC
    SELFLESS(MISC, "<col=A0E095>Selfless", new String[]{"- Donate 250m to Server perks", ""}),
    BILLIONAIRE(MISC, "<col=F2D40D>the Billionaire##", new String[]{"- Buy for 1B Upgrade tokens", ""}),
    MAXED(MISC, "<col=003BFF>Maxed", new String[]{"- Max in all skills",""}),
    DEVOURER(MISC, "<col=504B73>the Devourer##", new String[]{"- 2.5m Npc Killcount",""}),
    GRINDER(MISC, "<col=06E5D4>the Grinder##", new String[]{"- 2,000 Hours Playtime",""}),
    NOLIFE(MISC, "<col=E87D0A>No-Life", new String[]{"- 5,000 Hours Playtime",""}),
    ASSASSIN(MISC, "<col=644323>Assassin", new String[]{"- Become an Assassin Master",""}),
    CELESTIAL(MISC, "<col=137eb9>Celestial", new String[]{"- Unlock the Celestial Zone",""}),

    ;

    private final String name;
    private final TitleType type;
    private final String[] requirements;
    public int cost;
    public int section;
    public int npcId;
    public int amount;
    Titles(TitleType type, String name, int cost) {
        this.type = type;
        this.name = name;
        this.cost = cost;
        this.requirements = new String[]{"- " + Misc.insertCommasToNumber( cost) + " Loyalty Points", ""};
    }
    Titles(TitleType type, String name, int npcId, int amount) {
        this.type = type;
        this.name = name;
        this.npcId = npcId;
        this.amount = amount;
        this.requirements = 
                new String[]{"- " + NpcDefinition.forId(npcId).getName() + " Collection log",
                      "- "+  Misc.insertCommasToNumber(amount) + " " + NpcDefinition.forId(npcId).getName() + " KC"};
    }

    Titles(TitleType type, String name, String[] requirements) {
        this.type = type;
        this.name = name;
        this.requirements = requirements;
    }

    public static ArrayList<Titles> getItems(TitleType type) {
        ArrayList<Titles> items = new ArrayList<>();
        for (Titles s : Titles.values()) {
            if (s.type == type) {
                items.add(s);
            }
        }
        return items;
    }

    public String[] getRequirements() {
        return requirements;
    }

    public String getName() {
        return name;
    }

    public enum TitleType {

        LOYALTY, BOSS,  MISC, RESET
        ;

        TitleType() {
        }
    }
}