package me.berry.riftSTA.utils;

import me.berry.riftSTA.RiftSTA;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConfigUtil {
	RiftSTA terminalPlugin = RiftSTA.getInstance();

	public void init() {
		terminalPlugin.saveDefaultConfig();
	}

	public Map<String, String> deSerilize() {
		Map<String, String> questionAnswerArray = new HashMap<>();
		FileConfiguration mainConfig = terminalPlugin.getConfig();

		for(String question : mainConfig.getConfigurationSection("Questions").getKeys(false)) {
			String keywords = mainConfig.getString("Questions." + question + ".Keywords");
			String answer = mainConfig.getString("Questions." + question + ".Answer");

			if (question != null && keywords != null && answer != null) questionAnswerArray.put(keywords, answer);
		}

		return questionAnswerArray;
	}

	public int findSensitivity() {
		FileConfiguration mainConfig = terminalPlugin.getConfig();

		return mainConfig.getInt("Settings.Sensitivity");
	}

	public boolean testSensitivity() {
		FileConfiguration mainConfig = terminalPlugin.getConfig();

		return mainConfig.getBoolean("Settings.Test Sensitivity");
	}

	public ArrayList<String> ignoreWords() {
		FileConfiguration mainConfig = terminalPlugin.getConfig();
		ArrayList<String> ignoredWords = new ArrayList<>();

		ignoredWords.addAll(mainConfig.getStringList("Ignore Words"));

		return ignoredWords;
	}

	public String noneMatched() {
		FileConfiguration mainConfig = terminalPlugin.getConfig();
		String noMatches = mainConfig.getString("No Matches");

		return noMatches;
	}

	public String askQuestionMessage() {
		FileConfiguration mainConfig = terminalPlugin.getConfig();
		String askMessage = mainConfig.getString("Ask Question Message");

		return askMessage;
	}
}
