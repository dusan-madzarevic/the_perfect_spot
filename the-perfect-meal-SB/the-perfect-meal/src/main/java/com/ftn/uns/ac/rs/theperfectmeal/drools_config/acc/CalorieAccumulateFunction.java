package com.ftn.uns.ac.rs.theperfectmeal.drools_config.acc;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.kie.api.runtime.rule.AccumulateFunction;

import com.ftn.uns.ac.rs.theperfectmeal.model.RecipeIngredient;

public class CalorieAccumulateFunction implements AccumulateFunction<CalorieData>{

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CalorieData createContext() {
		return new CalorieData();
	}

	@Override
	public void init(CalorieData context) throws Exception {
	
		context.setTotal(0);
		
	}

	@Override
	public void accumulate(CalorieData context, Object value) {
		
		RecipeIngredient ing = (RecipeIngredient) value;
		context.setTotal(context.getTotal() + ((ing.getIngredient().getCaloricValue() * ing.getAmount())/100));
		
	}

	@Override
	public void reverse(CalorieData context, Object value) throws Exception {
		
		RecipeIngredient ing = (RecipeIngredient) value;
		context.setTotal(context.getTotal() - ((ing.getIngredient().getCaloricValue() * ing.getAmount())/100));
		
	}

	@Override
	public Object getResult(CalorieData context) throws Exception {
		return context.getTotal();
	}

	@Override
	public boolean supportsReverse() {
		return true;
	}

	@Override
	public Class<?> getResultType() {
		return Double.class;
	}





}
