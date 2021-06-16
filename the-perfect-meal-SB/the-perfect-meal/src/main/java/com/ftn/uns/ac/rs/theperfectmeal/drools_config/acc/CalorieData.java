package com.ftn.uns.ac.rs.theperfectmeal.drools_config.acc;

import java.io.Serializable;

public class CalorieData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1147924248917653843L;
	private double total;

	public CalorieData() {
		super();
	}

	public CalorieData(double total) {
		super();
		this.total = total;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
