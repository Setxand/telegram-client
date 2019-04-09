package telegram;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TelegramRequest {
	String photo;
	String caption;
	private String url;
	private String text;
	@JsonProperty("chat_id")
	private Integer chatId;
	@JsonProperty("reply_markup")
	private Markup markup;

	public TelegramRequest(String text, Integer chatId) {
		this.text = text;
		this.chatId = chatId;
	}

	public TelegramRequest() {
	}

	public TelegramRequest(Integer chatId, Markup markup, String photo, String caption) {
		this.chatId = chatId;
		this.markup = markup;
		this.photo = photo;
		this.caption = caption;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getChatId() {
		return chatId;
	}

	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}

	public Markup getMarkup() {
		return markup;
	}

	public void setMarkup(Markup markup) {
		this.markup = markup;
	}
}
