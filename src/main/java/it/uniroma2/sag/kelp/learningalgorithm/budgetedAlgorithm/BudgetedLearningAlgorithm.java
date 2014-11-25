/*
 * Copyright 2014 Simone Filice and Giuseppe Castellucci and Danilo Croce and Roberto Basili
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.uniroma2.sag.kelp.learningalgorithm.budgetedAlgorithm;


import java.util.Arrays;
import java.util.List;

import it.uniroma2.sag.kelp.data.dataset.Dataset;
import it.uniroma2.sag.kelp.data.example.Example;
import it.uniroma2.sag.kelp.data.label.Label;
import it.uniroma2.sag.kelp.learningalgorithm.BinaryLearningAlgorithm;
import it.uniroma2.sag.kelp.learningalgorithm.KernelMethod;
import it.uniroma2.sag.kelp.learningalgorithm.LearningAlgorithm;
import it.uniroma2.sag.kelp.learningalgorithm.MetaLearningAlgorithm;
import it.uniroma2.sag.kelp.learningalgorithm.OnlineLearningAlgorithm;
import it.uniroma2.sag.kelp.predictionfunction.Prediction;
import it.uniroma2.sag.kelp.predictionfunction.PredictionFunction;
import it.uniroma2.sag.kelp.predictionfunction.model.BinaryKernelMachineModel;

/**
 * It is binary kernel-based online learning method that binds the number of support vector to a fix number (i.e. the budget)
 * Until the budget is not reached the online learning updating policy is the one of the baseAlgorithm that this
 * metaalgorithm is exploiting. When the budget is full, a new updating policy (that must be specified by extending classes)
 * is adopted
 * 
 * @author Simone Filice
 *
 */
public abstract class BudgetedLearningAlgorithm implements MetaLearningAlgorithm, OnlineLearningAlgorithm, BinaryLearningAlgorithm{

	protected int budget;
	protected OnlineLearningAlgorithm baseAlgorithm;
	protected Label label;

	/**
	 * Returns the budget, i.e. the maximum number of support vectors 
	 * 
	 * @return the budget
	 */
	public int getBudget() {
		return budget;
	}

	/**
	 * Sets the budget, i.e. the maximum number of support vectors 
	 * 
	 * @param budget the budget to set
	 */
	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	@Override
	public void setBaseAlgorithm(LearningAlgorithm baseAlgorithm) {
		if(baseAlgorithm instanceof OnlineLearningAlgorithm && baseAlgorithm instanceof KernelMethod && baseAlgorithm instanceof BinaryLearningAlgorithm){
			this.baseAlgorithm = (OnlineLearningAlgorithm) baseAlgorithm;
		}else{
			throw new IllegalArgumentException("a valid baseAlgorithm for a BudgetedLearningAlgorithm must implement OnlineLearningAlgorithm, BinaryLeaningAlgorithm and KernelMethod");
		}
	}

	@Override
	public OnlineLearningAlgorithm getBaseAlgorithm() {
		return this.baseAlgorithm;
	}
	
	@Override
	public PredictionFunction getPredictionFunction() {
		return this.baseAlgorithm.getPredictionFunction();
	}
	
	@Override
	public void setLabels(List<Label> labels){
		this.baseAlgorithm.setLabels(labels);
		
	}

	@Override
	public List<Label> getLabels() {
		return this.baseAlgorithm.getLabels();
	}
	
	@Override
	public void learn(Dataset dataset){
		while(dataset.hasNextExample()){
			this.learn(dataset.getNextExample());
		}
		dataset.reset();
	}
	
	@Override
	public Prediction learn(Example example){
		BinaryKernelMachineModel model = (BinaryKernelMachineModel) this.baseAlgorithm.getPredictionFunction().getModel();
		if(model.getSupportVectors().size()<this.budget || model.isSupportVector(example)){
			return ((OnlineLearningAlgorithm)this.baseAlgorithm).learn(example);
		}
		return predictAndLearnWithFullBudget(example);		
	}
	
	
	/**
	 * Learns from a single example applying a specific policy that must be adopted when the budget is reached 
	 * 
	 * @param example the example to be exploited in the learning process
	 * @return the prediction on the given example, using the old classification function (before any possible update)
	 */
	public abstract Prediction predictAndLearnWithFullBudget(Example example);
	
	@Override
	public Label getLabel(){
		return label;
	}
	
	@Override
	public void setLabel(Label label){
		this.setLabels(Arrays.asList(label));
	}
	
	
}
