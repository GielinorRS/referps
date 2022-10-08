package com.ruse;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.ruse.engine.GameEngine;
import com.ruse.engine.task.TaskManager;
import com.ruse.engine.task.impl.LotteryTask;
import com.ruse.engine.task.impl.ServerTimeUpdateTask;
import com.ruse.model.container.impl.Shop.ShopManager;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.model.definitions.NPCDrops;
import com.ruse.model.definitions.NpcDefinition;
import com.ruse.model.definitions.WeaponInterfaces;
import com.ruse.net.PipelineFactory;
import com.ruse.net.security.ConnectionHandler;
import com.ruse.util.infologs.AccountsCreated;
import com.ruse.util.infologs.UniqueAccountsCreated;
import com.ruse.world.World;
import com.ruse.world.allornothing.DoubleOrNothing;
import com.ruse.world.clip.region.RegionClipping;
import com.ruse.world.content.*;
import com.ruse.world.content.clan.ClanChatManager;
import com.ruse.world.content.combat.effect.CombatPoisonEffect.CombatPoisonData;
import com.ruse.world.content.combat.strategy.CombatStrategies;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.content.discordbot.Bot;
import com.ruse.world.content.discordbot.DiscordIntegration;
import com.ruse.world.content.grandLottery.GrandLottery;
import com.ruse.world.content.grandexchange.GrandExchangeOffers;
import com.ruse.world.content.groupironman.GroupManager;
import com.ruse.world.content.leaderboards.Leaderboard;
import com.ruse.world.content.polling.PollManager;
import com.ruse.world.content.pos.POSItemPrice;
import com.ruse.world.content.pos.PlayerOwnedShopManager;
import com.ruse.world.content.pos.TaxCollection;
import com.ruse.world.content.raffle.DonationRaffle;
import com.ruse.world.content.raffle.PerksRaffle;
import com.ruse.world.content.raffle.VotingRaffle;
import com.ruse.world.content.seasonpass.PassRewards;
import com.ruse.world.content.serverperks.ServerPerks;
import com.ruse.world.content.youtube.YoutubeMgr;
import com.ruse.world.entity.impl.npc.NPC;
import com.server.service.login.ServiceManager;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.util.HashedWheelTimer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Credit: lare96, Gabbe
 */
public final class GameLoader {

	private final ExecutorService serviceLoader = Executors
			.newSingleThreadExecutor(new ThreadFactoryBuilder().setNameFormat("GameLoadingThread").build());
	private final GameEngine engine;
	private final int port;

	protected GameLoader(int port) {
		this.port = port;
		this.engine = new GameEngine();
	}

	public void init() {
		Preconditions.checkState(!serviceLoader.isShutdown(), "The bootstrap has been bound already!");

		ServiceManager.INSTANCE.init();

		executeServiceLoad();
		serviceLoader.shutdown();
		World.LOGIN_SERVICE.postLoad();


	}

	public void finish() throws IOException, InterruptedException {
		if (!serviceLoader.awaitTermination(15, TimeUnit.MINUTES))
			throw new IllegalStateException("The background service load took too long!");
		final ExecutorService networkExecutor = Executors.newCachedThreadPool();
		final ServerBootstrap serverBootstrap = new ServerBootstrap (new NioServerSocketChannelFactory(networkExecutor, networkExecutor));
		serverBootstrap.setPipelineFactory(new PipelineFactory(new HashedWheelTimer()));
		serverBootstrap.bind(new InetSocketAddress(port));
		engine.init();
		TaskManager.submit(new ServerTimeUpdateTask());
	}

	private void executeServiceLoad() {
		/*
		 * if (GameSettings.MYSQL_ENABLED) { serviceLoader.execute(() ->
		 * MySQLController.init()); }
		 */

		serviceLoader.execute(VotingStreak::loadRewards);
		serviceLoader.execute(PassRewards::init);

		serviceLoader.execute(GroupManager::loadGroups);
		serviceLoader.execute(ConnectionHandler::init);
		serviceLoader.execute(PlayerPunishment::init);

		serviceLoader.execute(RegionClipping::init);
		serviceLoader.execute(CustomObjects::init);
		serviceLoader.execute(ItemDefinition::init);

		serviceLoader.execute(Lottery::init);
		serviceLoader.execute(GrandExchangeOffers::init);
		serviceLoader.execute(Scoreboards::init);

		serviceLoader.execute(WellOfGoodwill::init);
		serviceLoader.execute(ClanChatManager::init);
		serviceLoader.execute(CombatPoisonData::init);
		serviceLoader.execute(CombatStrategies::init);
		serviceLoader.execute(() -> NpcDefinition.parseNpcs().load());
		serviceLoader.execute(() -> NPCDrops.parseDrops().load());
		serviceLoader.execute(() -> WeaponInterfaces.parseInterfaces().load());
		serviceLoader.execute(() -> ShopManager.parseShops().load());
		serviceLoader.execute(PlayerOwnedShopManager::loadShops);
		serviceLoader.execute(POSItemPrice::load);
		serviceLoader.execute(() -> DialogueManager.parseDialogues().load());
		serviceLoader.execute(NPC::init);
		serviceLoader.execute(DoubleOrNothing::initialize);
		serviceLoader.execute(PollManager::initialize);
		serviceLoader.execute(GrandLottery::init);
		serviceLoader.execute(DoubleXPSkillEvent::pickNext);
		serviceLoader.execute(ShopManager::parseTaxShop);
		serviceLoader.execute(LotterySystem::loadTickets);
		serviceLoader.execute(DiscordIntegration::loadConnectedAccounts);
		serviceLoader.execute(DiscordIntegration::discordPoint);
		serviceLoader.execute(AOESystem.getSingleton()::parseData);
		serviceLoader.execute(Leaderboard::init);
		if (!GameSettings.LOCALHOST) {
			discordBot = new Bot();
			serviceLoader.execute(discordBot::init);
		}
		TaskManager.submit(new LotteryTask());

		serviceLoader.execute(TaxCollection::load);
		serviceLoader.execute(AccountsCreated::load);
		serviceLoader.execute(UniqueAccountsCreated::load);

		serviceLoader.execute(AddedCommands::load);
		serviceLoader.execute(ServerPerks.getInstance()::load);
		serviceLoader.execute(PlayerCompletions::load);


		serviceLoader.execute(PerksRaffle::load);
		serviceLoader.execute(VotingRaffle::load);
		serviceLoader.execute(DonationRaffle::load);


		serviceLoader.execute(YoutubeMgr::load);


	}

	public static Bot discordBot;

	public GameEngine getEngine() {
		return engine;
	}
}