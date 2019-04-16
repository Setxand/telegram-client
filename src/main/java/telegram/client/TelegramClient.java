package telegram.client;

import org.springframework.web.client.RestTemplate;
import telegram.Markup;
import telegram.Message;
import telegram.ReplyKeyboardRemove;
import telegram.TelegramRequest;
import telegram.button.InlineKeyboardButton;
import telegram.button.InlineKeyboardMarkup;
import telegram.button.KeyboardButton;
import telegram.button.KeyboardMarkup;

import java.util.*;

public abstract class TelegramClient {
	private final String SERVER_URL;
	private final String WEBHOOK;
	private final Map<String, String> urlMap;
	private RestTemplate restTemplate;

	public TelegramClient(String serverUrl, String webhook, String urls) {
		SERVER_URL = serverUrl;
		WEBHOOK = webhook;
		restTemplate = new RestTemplate();
		urlMap = processMap(urls);
	}

	/**
	 * @Example Webhooks in application.properties:
	 *			telegram.webhooks=/example1,/example2
	 */
	public void setWebHooks() {
		String[] webhooks = WEBHOOK.split(",");
		for (int i = 0; i < webhooks.length; i++) {
			String webhook = webhooks[i];
			List<String> strings = new ArrayList<>(urlMap.values());
			setWebHook(webhook, strings.get(i));
		}

	}

	protected void setWebHook(String webhook, String url){
		restTemplate.getForEntity(url + "/setWebhook?url=" + SERVER_URL + webhook, Object.class);
	}

	protected void sendMessage(TelegramRequest telegramRequest) {
		try {
			restTemplate.postForEntity(urlMap.get(telegramRequest.getPlatform().name()) + "/sendMessage",
																				telegramRequest, Void.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void helloMessage(Message message) {
		String helloMessage = ResourceBundle.getBundle("dictionary").getString("HELLO_MESSAGE");
		int chatId = message.getChat().getId();
		sendMessage(new TelegramRequest(helloMessage, chatId, message.getPlatform()));
	}

	public void simpleMessage(String message, Message m) {
		sendMessage(new TelegramRequest(message, m.getChat().getId(), m.getPlatform()));
	}

	public void errorMessage(Message message) {
		String text = "men, i don`t understand this command, try again)";
		sendMessage(new TelegramRequest(text, message.getChat().getId(), message.getPlatform()));
	}

	public void sendButtons(Markup markup, String text, Message message) {
		TelegramRequest telegramRequest = new TelegramRequest();
		telegramRequest.setChatId(message.getChat().getId());
		telegramRequest.setText(text);
		telegramRequest.setMarkup(markup);
		telegramRequest.setPlatform(message.getPlatform());
		sendMessage(telegramRequest);
	}

	public void sendInlineButtons(List<List<InlineKeyboardButton>> buttons, String text, Message message) {
		Markup markup = new InlineKeyboardMarkup(buttons);
		sendButtons(markup, text, message);
	}

	public void sendPhoto(String photo, String caption, Markup markup, Message message) {
		restTemplate.postForEntity(urlMap.get(message.getPlatform().name()) + "/sendPhoto",
								new TelegramRequest(message.getChat().getId(), markup, photo, caption), Void.class);
	}

	public abstract void sendActions(Message message);

	public void simpleQuestion(String splitter, String text, Message message){
		List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
		String yes = ResourceBundle.getBundle("dictionary").getString("YES");
		String no = ResourceBundle.getBundle("dictionary").getString("NO");
		inlineKeyboardButtons.add(new InlineKeyboardButton(yes, "Yes" + splitter + "QUESTION_YES"));
		inlineKeyboardButtons.add(new InlineKeyboardButton(no, "No" + splitter + "QUESTION_NO"));
		sendInlineButtons(new ArrayList<>(Collections.singletonList(inlineKeyboardButtons)), text, message);
	}

	public void noEnoughPermissions(Message message) {
		String text = "You have not enough permissions to make it!";
		simpleMessage(text, message);
	}

	public void sendKeyboardButtons(Message message, List<List<KeyboardButton>> buttons, String text) {
		sendButtons(new KeyboardMarkup(buttons), text, message);
	}

	public void removeKeyboardButtons(Message message) {
		TelegramRequest telegramRequest = new TelegramRequest();
		telegramRequest.setMarkup(new ReplyKeyboardRemove(true));
		telegramRequest.setText("Done");
		telegramRequest.setChatId(message.getChat().getId());
		sendMessage(telegramRequest);
	}

	private static Map<String, String> processMap(String urls){
		String[] urlss = urls.split(",");
		Map<String, String> map = new LinkedHashMap<>();
		for (int i = 0; i < urlss.length - 1; i += 2) map.put(urlss[i], urlss[i + 1]);
		return map;
	}

}
