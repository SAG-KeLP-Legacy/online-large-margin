online-large-margin
=========

[**KeLP**][kelp-site] is the Kernel-based Learning Platform developed in the [Semantic Analytics Group][sag-site] of
the [University of Roma Tor Vergata][uniroma2-site].

This is the online-large-margin module of KeLP. 
It contains online learning algorithms for both classification and regression tasks. The following algorithms are currently implemented:

**CLASSIFICATION ALGORITHMS:**

* _LinearPassiveAggressiveClassification_: linear version of the Passive Aggressive learning algorithm for classification (Crammer '06) 
* _KernelizedPassiveAggressiveClassification_: kernel-based version of the Passive Aggressive learning algorithm for classification (Crammer '06)
* _LinearPerceptron_: linear version of the Perceptron learning algorithm for classification (Rosenblatt '57)
* _KernelizedPerceptron_: kernel-based version of the Perceptron learning algorithm for classification (Rosenblatt '57)
* _RandomizedBudgetPerceptron_: an extention of the Randomized Budget Perceptron proposed in (Cavallanti '06)

**REGRESSION ALGORITHMS:**

* _LinearPassiveAggressiveRegression_: linear version of the Passive Aggressive learning algorithm for regression (Crammer '06)
* _KernelizedPassiveAggressiveRegression_: kernel-based version of the Passive Aggressive learning algorithm for regression (Crammer '06)

**META-LEARNING ALGORITHMS:**

* _MultiEpochLearning_: a meta algorithm for performing multiple iterations on a training data
* _Stoptron_: an extention of the Stoptron algorithm proposed in (Orabona '08)


==============
**REFERENCES:**

(Cavallanti '06) G. Cavallanti, N. Cesa-Bianchi, C. Gentile. _Tracking the best hyperplane with a simple budget Perceptron. In proc. of the 19-th annual conference on Computational Learning Theory_. (2006)

(Crammer '06) K. Crammer, O. Dekel, J. Keshet, S. Shalev-Shwartz and Y. Singer. _Online Passive-Aggressive Algorithms_. Journal of Machine Learning Research (2006)

(Orabona '08) Francesco Orabona, Joseph Keshet, and Barbara Caputo. _The projectron: a bounded kernel-based perceptron_. In Int. Conf. on Machine Learning (2008)

(Rosenblatt '57) F. Rosenblatt. _The Perceptron – a perceiving and recognizing automaton_. Report 85-460-1, Cornell Aeronautical Laboratory (1957)



[sag-site]: http://sag.art.uniroma2.it "SAG site"
[uniroma2-site]: http://www.uniroma2.it "University of Roma Tor Vergata"
[kelp-site]: http://sag.art.uniroma2.it/demo-software/kelp/