package me.berry.riftSTA.utils;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerCache {
	private static final ArrayList<UUID> playersAskingSomething = new ArrayList<>();

	public boolean isAsking(UUID playerUUID) {
		if(playersAskingSomething.contains(playerUUID)) return true;
		else return false;
	}

	public void updateCache(UUID playerUUID) {
		if(!playersAskingSomething.contains(playerUUID)) playersAskingSomething.add(playerUUID);
	}

	public void remove(UUID playerUUID) {
		playersAskingSomething.remove(playerUUID);
	}
}
