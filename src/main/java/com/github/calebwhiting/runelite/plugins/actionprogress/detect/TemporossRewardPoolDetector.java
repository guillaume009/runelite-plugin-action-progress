package com.github.calebwhiting.runelite.plugins.actionprogress.detect;

import com.github.calebwhiting.runelite.plugins.actionprogress.Action;
import com.google.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.annotations.Varbit;
import net.runelite.api.events.GameTick;
import net.runelite.api.gameval.VarbitID;
import net.runelite.client.eventbus.Subscribe;

public class TemporossRewardPoolDetector extends ActionDetector
{

	@Varbit private static final int VAR_TEMPOROSS_PERMITS = VarbitID.TEMPOROSS_REWARDPERMITS;

	@Inject private Client client;

	private int previousPermits = -1;

	@Subscribe
	public void onGameTick(GameTick evt)
	{
		int permits = this.client.getVarbitValue(VAR_TEMPOROSS_PERMITS);
		if (permits < this.previousPermits) {
			int permitsUsed = previousPermits - permits;
			boolean bigSearch = permitsUsed > 1;
			int searches = bigSearch ? (previousPermits + 4) / 5 : previousPermits;
			if (this.actionManager.getCurrentAction() != Action.TEMPOROSS_REWARD_POOL) {
				this.actionManager.setAction(Action.TEMPOROSS_REWARD_POOL, searches, -1);
			}
		}
		this.previousPermits = permits;
	}

}
