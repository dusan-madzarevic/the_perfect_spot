package com.ftn.uns.ac.rs.theperfectmeal.templates;

import java.time.LocalDate;

public class DateRangeTemplateModel {
	private LocalDate dateFrom;
	private LocalDate dateTo;

	public DateRangeTemplateModel() {
		super();
	}

	public DateRangeTemplateModel(LocalDate dateFrom, LocalDate dateTo) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDate getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}

	


	

}
