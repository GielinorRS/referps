package com.ruse.world.content.raids.impl.gods;


import com.ruse.model.input.Input;
import com.ruse.world.World;
import com.ruse.world.entity.impl.player.Player;

public class GodsRaidsInviteInput extends Input {

    @Override
    public void handleSyntax(Player inviter, String inputString) {

        if(GodsRaidsData.LOBBY_AREA.inside(inviter.getPosition(), inviter.getPosition().getZ())) {

            inviter.getPacketSender().sendInterfaceRemoval();
            Player invitee = World.getPlayerByName(inputString);
            if(invitee == null) {
                inviter.getPacketSender().sendMessage("That player is currently not online.");
                return;
            }
            if(inviter.getGodsRaidsParty().getPlayers().contains(invitee)) {
                inviter.getPacketSender().sendMessage("That player is already in your party.");
                return;
            }
            inviter.sendMessage("Sent invite to " + inputString);

            inviter.getGodsRaidsParty().invite(invitee);
        }
    }

}
