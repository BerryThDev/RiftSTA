package me.berry.riftSTA.events;

import me.berry.riftSTA.utils.ChatUtil;
import me.berry.riftSTA.utils.KeywordUtil;
import me.berry.riftSTA.utils.PlayerCache;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
	@EventHandler
	public void playChatEvent(AsyncPlayerChatEvent event) {
		PlayerCache playerCache = new PlayerCache();
		Player player = event.getPlayer();

		if(playerCache.isAsking(player.getUniqueId())) {
			KeywordUtil keywordUtil = new KeywordUtil();
			ChatUtil chatUtil = new ChatUtil();

			String message = event.getMessage();
			String rawMsg = keywordUtil.findReply(message);

			chatUtil.sendChatFromConfig(rawMsg, event.getPlayer());

			playerCache.remove(player.getUniqueId());

			event.setCancelled(true);
		}
	}
}
