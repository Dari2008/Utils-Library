package javad.utils.ki.openAi;

import com.theokanning.openai.service.OpenAiService;

public class ChatGPT {

	private String token = "";
	private OpenAiService ai;
	
	public ChatGPT(String token) {
		this.token = token;
		ai = new OpenAiService(token);
	}
	
	public OpenAiService getOpenAiService() {
		return ai;
	}
	
	public String getToken() {
		return token;
	}

}
