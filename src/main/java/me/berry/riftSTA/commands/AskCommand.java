package me.berry.riftSTA.commands;

import me.berry.riftSTA.utils.ChatUtil;
import me.berry.riftSTA.utils.ConfigUtil;
import me.berry.riftSTA.utils.PlayerCache;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AskCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		ChatUtil chatUtil = new ChatUtil();
		ConfigUtil configUtil = new ConfigUtil();
		PlayerCache playerCache = new PlayerCache();

		if(strings.length != 1) return false;

		Player player = Bukkit.getPlayer(strings[0]);

		if(!playerCache.isAsking(player.getUniqueId())) {
			playerCache.updateCache(player.getUniqueId());
			chatUtil.sendChatFromConfig(configUtil.askQuestionMessage(), player);
		}

		return false;
	}
}
