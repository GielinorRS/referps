package com.ruse.model.definitions;

import com.ruse.GameServer;
import com.ruse.model.container.impl.Equipment;
import lombok.Getter;

import java.io.*;

/**
 * This file manages every item definition, which includes their name,
 * description, value, skill requirements, etc.
 *
 * @author relex lawl
 */

public class ItemDefinition {

    /**
     * The directory in which item definitions are found.
     */
    private static final String FILE_DIRECTORY = "./data/def/txt/items.txt";
    private static final String NAME_DIRECTORY = "./data/def/txt/itemnames.txt";
    private static final String EXAMINE_DIRECTORY = "./data/def/txt/itemexamines.txt";

    /**
     * The max amount of items that will be loaded.-+
     */
    private static final int MAX_AMOUNT_OF_ITEMS = 24000;

    /**
     * ItemDefinition array containing all items' definition values.
     */
    private static ItemDefinition[] definitions = new ItemDefinition[MAX_AMOUNT_OF_ITEMS];
    /**
     * The id of the item.
     */
    private int id = 0;
    /**
     * The name of the item.
     */
    private String name = "None";
    /**
     * The item's description.
     */
    private String description = "Null";
    /**
     * Flag to check if item is stackable.
     */
    private boolean stackable;
    /**
     * The item's shop value.
     */
    private int value;
    /**
     * Flag that checks if item is noted.
     */
    private boolean noted;
    private boolean isTwoHanded;
    private boolean weapon;
    private EquipmentType equipmentType = EquipmentType.WEAPON;
    private double[] bonus = new double[18];
    private int[] requirement = new int[25];




    public static void dump() {
        File f = new File("itemexamines.txt");
        System.out.println("Dumping Item examines..");
        // String[] variableNames = new String[] { "name", };
        try {
            f.createNewFile();
            BufferedWriter bf = new BufferedWriter(new FileWriter(f));
            for (int index = 0; index < definitions.length ; index++) {
                ItemDefinition def = forId(index);
                String name = def.name;

                if (name != null) {
                    bf.write("Item Id: " + index);
                    bf.newLine();
                    bf.write("Name: " + name);
                    bf.newLine();
                    bf.write("finish");
                    bf.newLine();
                    bf.newLine();
                }
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Dumping Complete!");
    }


    /**
     * Loading all item definitions
     */
    public static void init() {
        ItemDefinition definition = definitions[0];
        try {
            File file = new File(FILE_DIRECTORY);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("inish")) {
                    definitions[definition.id] = definition;
                    continue;
                }
                String[] args = line.split(": ");
                if (args.length <= 1)
                    continue;
                String token = args[0], value = args[1];
                if (line.contains("Bonus[")) {
                    String[] other = line.split("]");
                    int index = Integer.valueOf(line.substring(6, other[0].length()));
                    double bonus = Double.valueOf(value);
                    definition.bonus[index] = bonus;
                    continue;
                }
                if (line.contains("Requirement[")) {
                    String[] other = line.split("]");
                    int index = Integer.valueOf(line.substring(12, other[0].length()));
                    int requirement = Integer.valueOf(value);
                    definition.requirement[index] = requirement;
                    continue;
                }
                switch (token.toLowerCase()) {
                    case "item id":
                        int id = Integer.valueOf(value);
                        definition = new ItemDefinition();
                        definition.id = id;
                        break;
                    case "name":
                        if (value == null)
                            continue;
                        definition.name = value;
                        break;
                    case "value":
                        int price = Integer.valueOf(value);
                        definition.value = price;
                        break;
                    case "stackable":
                        definition.stackable = Boolean.valueOf(value);
                        break;
                    case "noted":
                        definition.noted = Boolean.valueOf(value);
                        break;
                    case "double-handed":
                        definition.isTwoHanded = Boolean.valueOf(value);
                        break;
                    case "equipment type":
                        definition.equipmentType = EquipmentType.valueOf(value);
                        break;
                    case "is weapon":
                        definition.weapon = Boolean.valueOf(value);
                        break;
                    case "desc":
                        definition.description = value;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 23324; i <= 23350; i ++) {
            copyDefinitions(i, definitions[i - 9]);
        }


        try {
            File file = new File(NAME_DIRECTORY);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("inish")) {
                    definitions[definition.id] = definition;
                    continue;
                }

                String[] args = line.split(": ");
                if (args.length <= 1)
                    continue;
                String token = args[0], value = args[1];

                switch (token.toLowerCase()) {
                    case "item id":
                        int id = Integer.valueOf(value);
                        if (id < definitions.length)
                            definition = ItemDefinition.forId(id);
                        break;
                    case "name":
                        if (definition != null)
                            definition.name = value;
                        break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        try {
            File file = new File(EXAMINE_DIRECTORY);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("inish")) {
                    definitions[definition.id] = definition;
                    continue;
                }

                String[] args = line.split(": ");
                if (args.length <= 1)
                    continue;
                String token = args[0], value = args[1];

                switch (token.toLowerCase()) {
                    case "item id":
                        int id = Integer.valueOf(value);
                        if (id < definitions.length)
                            definition = ItemDefinition.forId(id);
                        break;
                    case "examine":
                        definition.description = value;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

      dumpItems();

        dump();
    }
    public static void dumpItems() {

        if (new File("./data/def/itemstats.dat").exists()){
            new File("./data/def/itemstats.dat").delete();
        }

        GameServer.getLoader().getEngine().submit(() -> {
            try {
                FileWriter fw = new FileWriter("./data/def/itemstats.dat", true);
                if (fw != null) {
                    fw.write("[STATS]");
                    fw.write(System.lineSeparator());
                    fw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        for (ItemDefinition def : definitions) {
            if (def == null || def.getBonus() == null)
                continue;

            boolean dump = false;
            for (double b : def.getBonus()) {
                if (b > 0) {
                    dump = true;
                    break;
                }
            }

            if (dump) {
                GameServer.getLoader().getEngine().submit(() -> {
                    try {
                        FileWriter fw = new FileWriter("./data/def/itemstats.dat", true);
                        String write = def.id + " ";
                        for (int i = 0; i < 10; i++) {
                            write += (int) def.getBonus()[i] + " ";
                        }
                        write += (int) def.getBonus()[14] + " ";
                        write += (int) def.getBonus()[15] + " ";
                        write += (int) def.getBonus()[16] + " ";
                        write += (int) def.getBonus()[17];

                        if (fw != null) {
                            fw.write(write);
                            fw.write(System.lineSeparator());
                            fw.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }


    public static void copyDefinitions(int index, ItemDefinition def) {
        definitions[index] = new ItemDefinition();
        definitions[index].id = index;
        definitions[index].name = def.name;
        definitions[index].description = def.description;
        definitions[index].equipmentType = def.equipmentType;
        definitions[index].isTwoHanded = def.isTwoHanded;
        definitions[index].value = def.value;
        definitions[index].stackable = def.stackable;
        definitions[index].noted = def.noted;
        definitions[index].requirement = def.requirement;
        definitions[index].bonus = def.bonus;
        definitions[index].weapon = def.weapon;
    }

    public static ItemDefinition[] getDefinitions() {
        return definitions;
    }

    /**
     * Gets the item definition correspondent to the id.
     *
     * @param id The id of the item to fetch definition for.
     * @return definitions[id].
     */
    public static ItemDefinition forId(int id) {
        return (id < 0 || id > definitions.length || definitions[id] == null) ? new ItemDefinition() : definitions[id];
    }

    /**
     * Gets the max amount of items that will be loaded in Niobe.
     *
     * @return The maximum amount of item definitions loaded.
     */
    public static int getMaxAmountOfItems() {
        return MAX_AMOUNT_OF_ITEMS;
    }

    public static int getItemId(String itemName) {
        for (int i = 0; i < MAX_AMOUNT_OF_ITEMS; i++) {
            if (definitions[i] != null) {
                if (definitions[i].getName().equalsIgnoreCase(itemName)) {
                    return definitions[i].getId();
                }
            }
        }
        return -1;
    }

    /**
     * Gets the item's id.
     *
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the item's name.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the item's description.
     *
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the item is stackable.
     *
     * @return stackable.
     */
    public boolean isStackable() {
        if (noted)
            return true;
        return stackable;
    }

    /**
     * Gets the item's shop value.
     *
     * @return value.
     */
    public int getValue() {
        return isNoted() ? ItemDefinition.forId(getId() - 1).value : value;
    }

    /**
     * Gets the item's equipment slot index.
     *
     * @return equipmentSlot.
     */
    public int getEquipmentSlot() {
        return equipmentType.slot;
    }

    /**
     * Checks if item is noted.
     *
     * @return noted.
     */
    public boolean isNoted() {
        return noted;
    }

    /**
     * Checks if item is two-handed
     */
    public boolean isTwoHanded() {
        return isTwoHanded;
    }

    public boolean isWeapon() {
        return weapon;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    /**
     * Checks if item is full body.
     */
    public boolean isFullBody() {
        return equipmentType.equals(EquipmentType.PLATEBODY);
    }

    /**
     * Checks if item is full helm.
     */
    public boolean isFullHelm() {
        return equipmentType.equals(EquipmentType.FULL_HELMET);
    }
    public boolean isFullHelm1() {
        return equipmentType.equals(EquipmentType.FULL_HELMET1);
    }

    public double[] getBonus() {
        return bonus;
    }

    public int[] getRequirement() {
        return requirement;
    }

    @Override
    public String toString() {
        return "[ItemDefinition(" + id + ")] - Name: " + name + "; equipment slot: " + getEquipmentSlot() + "; value: "
                + value + "; stackable ? " + Boolean.toString(stackable) + "; noted ? " + Boolean.toString(noted)
                + "; 2h ? " + isTwoHanded;
    }

    public enum EquipmentType {
        HAT(Equipment.HEAD_SLOT), CAPE(Equipment.CAPE_SLOT), SHIELD(Equipment.SHIELD_SLOT),
        GLOVES(Equipment.HANDS_SLOT), BOOTS(Equipment.FEET_SLOT), AMULET(Equipment.AMULET_SLOT),
        RING(Equipment.RING_SLOT), ARROWS(Equipment.AMMUNITION_SLOT), FULL_MASK(Equipment.HEAD_SLOT),
        FULL_HELMET(Equipment.HEAD_SLOT), BODY(Equipment.BODY_SLOT), PLATEBODY(Equipment.BODY_SLOT)
        , FULL_HELMET1(Equipment.HEAD_SLOT),
        LEGS(Equipment.LEG_SLOT), WEAPON(Equipment.WEAPON_SLOT), AURA(Equipment.AURA_SLOT),
        COSTUME(Equipment.COSTUME_SLOT),;

        public static EquipmentType forSlot(int slot) {
            for (EquipmentType type : values()) {
                if (type.slot == slot)
                    return type;
            }
            return null;
        }

        @Getter
        private int slot;

        private EquipmentType(int slot) {
            this.slot = slot;
        }
    }
}
