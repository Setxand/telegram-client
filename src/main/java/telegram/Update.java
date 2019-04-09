package telegram;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Update {
	@JsonProperty("update_id")
	private Integer updateId;
	@JsonProperty("callback_query")
	private CallBackQuery callBackQuery;
	private Message message;

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

	public CallBackQuery getCallBackQuery() {
		return callBackQuery;
	}

	public void setCallBackQuery(CallBackQuery callBackQuery) {
		this.callBackQuery = callBackQuery;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
}
