package me.berry.riftSTA.utils;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;

public class KeywordUtil {
	public String findReply(String messageContent) {
		ConfigUtil configUtil = new ConfigUtil();

		Map<String, String> questionsAnswersMap;
		ArrayList<String> msgArray;
		ArrayList<String> keywordsArray;

		String probableAnswer = "";
		int mostMatched = 0;

		questionsAnswersMap = configUtil.deSerilize();

		msgArray = parseUserChatMessage(messageContent);

		if(msgArray == null) return null;

		for(Map.Entry<String, String> entry : questionsAnswersMap.entrySet()) {
			keywordsArray = parseConfigKeywords(entry.getKey());
			int matched = findKeywordsMatched(msgArray, keywordsArray);

			if(mostMatched == 0) {
				probableAnswer = entry.getValue();
				mostMatched = matched;
			}
			else if (mostMatched < matched) {
				probableAnswer = entry.getValue();
				mostMatched = matched;
			}
		}

		if(mostMatched == 0) return configUtil.noneMatched();

		return probableAnswer;
	}

	private int findKeywordsMatched(ArrayList<String> messageArray, ArrayList<String> keywords) {
		ConfigUtil configUtil = new ConfigUtil();
		int matched = 0;

		for(String messageSection : messageArray)
			for (String keyword : keywords) {
				double difference = StringUtils.getLevenshteinDistance(keyword, messageSection);

				if (configUtil.testSensitivity())
					System.out.println("Keyword: " + keyword + ", Message Section: " + messageSection + ", Difference Between Them:" + difference);

				if (difference < configUtil.findSensitivity()) matched++;
			}

		return matched;
	}

	private ArrayList<String> parseConfigKeywords(String keywords) {
		ArrayList<String> keywordsSplit = new ArrayList<>();

		if(keywords.contains("|")) {
			String[] splitArray = keywords.split(Pattern.quote("|"));

			for(String arrayKey : splitArray) keywordsSplit.add(arrayKey.toLowerCase());

		} else keywordsSplit.add(keywords.toLowerCase());

		return keywordsSplit;
	}

	private ArrayList<String> parseUserChatMessage(String message) {
		ConfigUtil configUtil = new ConfigUtil();
		ArrayList<String> messageSplit = new ArrayList<>();

		if(message.contains(" ")) {
			String[] splitArray = message.split(Pattern.quote(" "));

			for(String arrayKey : splitArray)
				if (!configUtil.ignoreWords().contains(arrayKey.toLowerCase())) {
					arrayKey.replaceAll("[^a-zA-Z0-9]", "");
					messageSplit.add(arrayKey.toLowerCase());
				}
		} else if (!configUtil.ignoreWords().contains(message.toLowerCase())) {
			message.replaceAll("[^a-zA-Z0-9]", "");
			messageSplit.add(message.toLowerCase());
		}

		if(messageSplit.isEmpty()) return null;

		return messageSplit;
	}
}
