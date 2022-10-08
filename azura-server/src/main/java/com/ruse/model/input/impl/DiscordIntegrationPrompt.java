package com.ruse.model.input.impl;

import com.ruse.model.input.Input;
import com.ruse.world.content.discordbot.DiscordIntegration;
import com.ruse.world.entity.impl.player.Player;

public class DiscordIntegrationPrompt extends Input {

    @Override
    public void handleSyntax(Player player, String code) {
        DiscordIntegration.integrateAccount(player, code);
    }

}