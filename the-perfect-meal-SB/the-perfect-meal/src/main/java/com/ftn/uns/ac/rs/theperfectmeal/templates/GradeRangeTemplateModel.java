package com.ftn.uns.ac.rs.theperfectmeal.templates;

public class GradeRangeTemplateModel {
	private double minGrade;
	private double maxGrade;

	public GradeRangeTemplateModel() {
		super();
	}

	public GradeRangeTemplateModel(double minGrade, double maxGrade) {
		super();
		this.minGrade = minGrade;
		this.maxGrade = maxGrade;
	}

	public double getMinGrade() {
		return minGrade;
	}

	public void setMinGrade(double minGrade) {
		this.minGrade = minGrade;
	}

	public double getMaxGrade() {
		return maxGrade;
	}

	public void setMaxGrade(double maxGrade) {
		this.maxGrade = maxGrade;
	}

}
