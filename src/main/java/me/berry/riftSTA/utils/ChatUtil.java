package me.berry.riftSTA.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class ChatUtil {
	public void sendChatFromConfig(String rawMessage, Player player) {
		String withColors = replaceColorCodes(rawMessage);

		ArrayList<String> finalMessage = prepareForSend(withColors);

		for(String message : finalMessage) player.sendMessage(message);
	}

	private String replaceColorCodes(String message) {
		System.out.print(message);
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	private ArrayList<String> prepareForSend(String message) {
		ArrayList<String> lines = new ArrayList<>();
		String[] lineBreakSections = message.split(Pattern.quote("\n"));

		Collections.addAll(lines, lineBreakSections);

		return lines;
	}
}
