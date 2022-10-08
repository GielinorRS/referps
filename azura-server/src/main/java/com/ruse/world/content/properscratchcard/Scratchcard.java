package com.ruse.world.content.properscratchcard;


import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;

import java.util.Random;

public class Scratchcard {

    private final int INTERFACE_ID = 25400;
    private final int ITEM_CONTAINER_ID = 25412;

    private Player player;

    private Random random = new Random();

    private final int[] REWARDS = {7995, 22113, 22114, 22115, 10943, 10943};

    public Scratchcard(Player player) {
        this.player = player;
    }

    @Getter
    private ScratchcardGame game = new ScratchcardGame();

    private final int TICKET_ID = 22121;

    public void open() {
        if (!player.getInventory().contains(TICKET_ID)) {
            player.sendMessage("You need a Golden Scratch Card!");
            return;
        }
        player.getPacketSender().sendInterface(INTERFACE_ID);
        createGame();
        updateInterface();
    }


    private void createGame() {
        int[] items = random.ints(3, 0, REWARDS.length)
                .map(i -> REWARDS[i])
                .toArray();

        int[] REWARDS1 = {7995, 22113, 22114, 22115, 10943, 10943};
        if (Misc.getRandom(6) == 0) {
            int item = REWARDS1[Misc.getRandom(REWARDS1.length - 1)];
            items[0] = item;
            items[1] = item;
            items[2] = item;
        }

        game.setItems(items);
        game.setGameStarted(true);
    }

    public void claimPrize() {
        if (!game.isGameStarted()) {
            player.getPacketSender().sendMessage(game.isWinner() ? "You've already claimed your prize" : "Game has already ended");
            return;
        }
        if (game.isWinner()) {
            player.getPacketSender().sendMessage("You have just won a legendary prize, Congrats!");
            World.sendMessage("<shad=1>@yel@<img=14>[GOLDEN SCRATCH CARD]@cya@ " + player.getUsername() + "@cya@ received a @mag@LEGENDARY@cya@ reward from the @yel@Golden Card @cya@!");
            player.getInventory().add(game.getItems()[0], 1);
        } else {
            player.getPacketSender().sendMessage("Unfortunately you did not win.");
            player.getPacketSender().sendMessage("<shad=1>@cya@Lunite gave you a free $25 bond for trying !");
            player.getInventory().add(10934, 1);
        }

        game.setGameStarted(false);
        player.getPacketSender().sendInterfaceRemoval();

    }

    private void updateInterface() {
        player.getPacketSender().resetScratchcard();
        player.getPacketSender().sendItemArrayOnInterface(ITEM_CONTAINER_ID, game.getItems());
    }
}
