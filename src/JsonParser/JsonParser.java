package JsonParser;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Answer.Answer;
import Questions.FillInTheBlank;
import Questions.MultipleChoice;
import Questions.PictureResponse;
import Questions.Question;
import Questions.QuestionResponse;
import Quiz.Quiz;

public class JsonParser {

	public JsonParser() {

	}
	
	public int getQuizAutorId(String username) {
		
		return -1;
	}
	
	public Quiz getQuizObject(String jsonText) {
		JsonObject jobj = new Gson().fromJson(jsonText, JsonObject.class);
		List<Question> quests = new ArrayList<Question>();
		JsonArray arr = jobj.getAsJsonArray("questions");
		String desc = jobj.get("description").getAsString();
		String title = jobj.get("title").getAsString();
		for (int i = 0; i < arr.size(); i++) {
			JsonObject quest = arr.get(i).getAsJsonObject();
			int type = Integer.parseInt(quest.get("type").getAsString());
			Question q = parseQuestion(type, quest, i + 1);
			quests.add(q);
		}
		return new Quiz(title, desc, quests);
	}
	
	private Question parseQuestion(int type, JsonObject quest, int questNum) {
		if (type == 1) {
			String question = quest.get("question").getAsString();
			ArrayList<Answer> answers = new ArrayList<Answer>();
			for (int i = 0; i < 2; i++) {
				String tmp = quest.get("answer" + (i + 1)).getAsString();
				Answer tmpAnswer = new Answer(true, tmp);
				answers.add(tmpAnswer);
			}
			return new QuestionResponse(question, answers, questNum);
		} else if (type == 2) {
			String part1 = quest.get("beforeBlank").getAsString();
			String part2 = quest.get("afterBlank").getAsString();
			String partition = "_____";
			String tmp = quest.get("blank").getAsString();
			return new FillInTheBlank(part1 + partition + part2, new Answer(true, tmp), questNum);
		} else if (type == 3) {
			String question = quest.get("question").getAsString();
			ArrayList<Answer> answers = new ArrayList<Answer>();
			for (int i = 0; i < 4; i++) {
				boolean correct = false;
				String tmp = quest.get("answer" + (i + 1)).getAsString();
				if (i == 0) {
					correct = true;
				}else {
					correct = false;
				}
				answers.add(new Answer(correct, tmp));
			}
			return new MultipleChoice(question, answers, questNum);
		} else {
			String url = quest.get("url").getAsString();
			String answer = quest.get("answer").getAsString();
			PictureResponse p = new PictureResponse(url, new Answer(true, answer), questNum);
			return p;
		}
	}

}
