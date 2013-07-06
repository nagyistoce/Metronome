package tv.floe.metronome.classification.neuralnetworks.learning;

import tv.floe.metronome.classification.neuralnetworks.core.neurons.Neuron;
import tv.floe.metronome.classification.neuralnetworks.transfer.TransferFunction;

public class SigmoidDeltaLearningAlgorithm extends LeastMeanSquaresLearningAlgorithm {

	@Override
	protected void updateNetworkWeights(double[] outputError) {
				
		this.calculateErrorAndUpdateOutputNeurons(outputError);
		
	}

	protected void calculateErrorAndUpdateOutputNeurons(double[] outputError) {
		int i = 0;
		for(Neuron neuron : nn.getOutputNeurons()) {

			if (outputError[i] == 0) {
				neuron.setError(0);
                i++;
				continue;
			}
			
			TransferFunction transferFunction = neuron.getTransferFunction();
			double neuronInput = neuron.getNetInput();
			double delta = outputError[i] * transferFunction.getDerivative(neuronInput); // delta = (d-y)*df(net)
			neuron.setError(delta);
                        
			this.updateNeuronWeights(neuron);				
			i++;
		} // for				
	}	
	
}
