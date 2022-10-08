package com.ruse.model.input.impl;

import com.ruse.model.input.Input;
import com.ruse.world.entity.impl.player.Player;
import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static com.ruse.world.content.PlayerLogs.getTime;

public class EnterReferral extends Input {

    public static final String[] refs = {"merk", "didy", "zachtx","chris", "unrealchris", "walkchaos", "jacobson", "effigy",
            "winallday", "vihtic", "ipkmaxjr", "i pk max jr","frimb","wr3ckedyou", "chopper", "noobsown", "sprad","noobs own",
            "fpk merk","fpk", "runescapeorig", "stable", "stabletv", "lunite", "runehaven","casa", "walkchaos", "james", "casalusio", "didyscape", "tudor",
    "eggy", "perplexi", "runelocus", "rspslist", "topg", "youtuber", "sipsick", "divine"}; // codes here

    @Override
    public void handleSyntax(Player player, String syntax) {
        if (checkIps(player.getHostAddress())) {
            player.getPacketSender().sendMessage("You have already received a referral reward!");
            return;
        }

        if (referralResponse(player, syntax)) {
            try {
                BufferedWriter w = new BufferedWriter(new FileWriter("data/refer/referral_data.txt", true));
                w.write(player.getHostAddress() + " : " + player.getUsername() + " : " + getTime() + ": " + syntax);
                w.newLine();
                w.flush();
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	public static boolean referralResponse(Player player, String username) {
        if (Arrays.stream(refs).anyMatch(username::equalsIgnoreCase)
        || username.equalsIgnoreCase("eggy")
                || username.equalsIgnoreCase("lano")
                || username.equalsIgnoreCase("lanors")
                || username.equalsIgnoreCase("smoothie")
                || username.equalsIgnoreCase("rspsguy")
                || username.equalsIgnoreCase("rsps guy")
                || username.equalsIgnoreCase("morgen")
                || username.equalsIgnoreCase("didy")
                || username.equalsIgnoreCase("didyscape")) {
            player.getInventory().add(19116, 3);
            player.getInventory().add(19115, 2);
            player.getInventory().add(19114, 1);
            player.getInventory().add(12855, 10000);
            player.getInventory().add(18830, 150);
            player.sendMessage("@red@Congrats! Because you used the code " + username + " You have gotten a reward!");
            return true;
        }
        return false;
    }

    @SneakyThrows
    public static boolean checkIps(String ip) {
        File file = new File("data/refer/referral_data.txt");
        Scanner scanner = new Scanner(file);
        String content;
        int count = 0;
        while(scanner.hasNext()) {
            content = scanner.nextLine();
            String[] split = content.split(" : ");
            if (ip.equalsIgnoreCase(split[0])) {
                count++;
            }
            if (count >= 1) {
                return true;
            }
        }
        return false;
    }
}