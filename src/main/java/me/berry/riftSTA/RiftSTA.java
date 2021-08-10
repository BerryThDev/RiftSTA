package me.berry.riftSTA;

import me.berry.riftSTA.commands.AskCommand;
import me.berry.riftSTA.events.ChatEvent;
import me.berry.riftSTA.utils.ConfigUtil;
import org.bukkit.plugin.java.JavaPlugin;

public class RiftSTA extends JavaPlugin {
	private static RiftSTA instance;

	@Override
	public void onEnable() {
		instance = this;

		ConfigUtil configUtil = new ConfigUtil();
		configUtil.init();

		this.getCommand("ask").setExecutor(new AskCommand());

		getServer().getPluginManager().registerEvents(new ChatEvent(), this);
	}

	public static RiftSTA getInstance() {
		return instance;
	}
}
