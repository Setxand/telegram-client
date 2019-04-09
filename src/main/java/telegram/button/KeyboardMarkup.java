package telegram.button;

import telegram.Markup;

import java.util.List;

public class KeyboardMarkup implements Markup {
	private List<List<KeyboardButton>> keyboard;

	public KeyboardMarkup(List<List<KeyboardButton>> keyboard) {
		this.keyboard = keyboard;
	}

	public KeyboardMarkup() {
	}

	public List<List<KeyboardButton>> getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(List<List<KeyboardButton>> keyboard) {
		this.keyboard = keyboard;
	}
}

