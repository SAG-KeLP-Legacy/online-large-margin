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

import it.uniroma2.sag.kelp.data.example.Example;
import it.uniroma2.sag.kelp.data.label.Label;
import it.uniroma2.sag.kelp.learningalgorithm.OnlineLearningAlgorithm;
import it.uniroma2.sag.kelp.predictionfunction.Prediction;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * It is a variation of the Stoptron proposed in <p>
 * [OrabonaICML2008] Francesco Orabona, Joseph Keshet, and Barbara Caputo. The projectron: a bounded kernel-based perceptron. In Int. Conf. on Machine Learning (2008)
 * <p>
 * Until the budget is not reached the online learning updating policy is the one of the baseAlgorithm that this
 * meta-algorithm is exploiting. When the budget is full, the learning process ends
 * 
 * @author Simone Filice
 *
 */
@JsonTypeName("stoptron")
public class Stoptron extends BudgetedLearningAlgorithm{

	public Stoptron(){
		
	}
	
	public Stoptron(int budget, OnlineLearningAlgorithm baseAlgorithm, List<Label> labels){
		this.setBudget(budget);
		this.setBaseAlgorithm(baseAlgorithm);
		this.setLabels(labels);
	}
	
	@Override
	public Stoptron duplicate() {
		Stoptron copy = new Stoptron();
		copy.setBudget(budget);
		copy.setBaseAlgorithm(baseAlgorithm.duplicate());
		return copy;
	}

	@Override
	public void reset() {
		this.baseAlgorithm.reset();		
	}

	@Override
	public Prediction predictAndLearnWithFullBudget(Example example) {
		return this.baseAlgorithm.getPredictionFunction().predict(example);
	}

}
