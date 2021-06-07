package com.ftn.uns.ac.rs.theperfectmeal.templates;

import com.ftn.uns.ac.rs.theperfectmeal.util.CookingSkill;

public class DifficultyCategoryTemplateModel {

	private int minDifficultyScore;
	private int maxDifficultyScore;
	private CookingSkill difficulty;

	public DifficultyCategoryTemplateModel() {
		super();
	}

	public DifficultyCategoryTemplateModel(int minDifficultyScore, int maxDifficultyScore, CookingSkill difficulty) {
		super();
		this.minDifficultyScore = minDifficultyScore;
		this.maxDifficultyScore = maxDifficultyScore;
		this.difficulty = difficulty;
	}

	public int getMinDifficultyScore() {
		return minDifficultyScore;
	}

	public void setMinDifficultyScore(int minDifficultyScore) {
		this.minDifficultyScore = minDifficultyScore;
	}

	public int getMaxDifficultyScore() {
		return maxDifficultyScore;
	}

	public void setMaxDifficultyScore(int maxDifficultyScore) {
		this.maxDifficultyScore = maxDifficultyScore;
	}

	public CookingSkill getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(CookingSkill difficulty) {
		this.difficulty = difficulty;
	}

}
