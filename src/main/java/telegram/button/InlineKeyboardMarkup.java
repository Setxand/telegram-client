package telegram.button;

import com.fasterxml.jackson.annotation.JsonProperty;
import telegram.Markup;


import java.util.List;

public class InlineKeyboardMarkup implements Markup {
	@JsonProperty("inline_keyboard")
	private List<List<InlineKeyboardButton>> inlineKeyBoard;

	public InlineKeyboardMarkup(List<List<InlineKeyboardButton>> inlineKeyBoard) {
		this.inlineKeyBoard = inlineKeyBoard;
	}

	public InlineKeyboardMarkup() {
	}

	public List<List<InlineKeyboardButton>> getInlineKeyBoard() {
		return inlineKeyBoard;
	}

	public void setInlineKeyBoard(List<List<InlineKeyboardButton>> inlineKeyBoard) {
		this.inlineKeyBoard = inlineKeyBoard;
	}
}
