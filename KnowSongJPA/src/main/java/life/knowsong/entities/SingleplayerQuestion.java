package life.knowsong.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SingleplayerQuestion {

	
	// SQL VALUES
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "fk_singleplayer_game_id")
	private SingleplayerGame game;
	
	@Column(name = "question_text")
	private String questionText;
	
	private String answer;
	
	private String option2;
	
	private String option3;
	
	private String option4;
	
	@Column(name = "user_response")
	private String userResponse;

	
	// GETTERS / SETTERS 
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SingleplayerGame getGame() {
		return game;
	}

	public void setGame(SingleplayerGame game) {
		this.game = game;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getUserResponse() {
		return userResponse;
	}

	public void setUserResponse(String userResponse) {
		this.userResponse = userResponse;
	}
	
}