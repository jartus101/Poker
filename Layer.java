import java.util.ArrayList;
import java.util.List;
public class Layer {
    private int numNodesIn;
    private int numNodesOut;
    private Matrix weights;
    private Matrix inputs;
    private Matrix biases;
    public Layer(int numNodesIn, int numNodesOut){
        this.numNodesIn = numNodesIn;
        this.numNodesOut = numNodesOut;
        this.weights = new Matrix(numNodesOut, numNodesIn);
        this.biases = new Matrix(numNodesOut, 1);
    }
    public Matrix getWeights(){
        return this.weights;
    }
    public void setWeights(Matrix weights){
        this.weights = weights;
    }
    public void setBiases(Matrix biases){
        this.biases = biases;
    }
    public Matrix getInputs(){
        return this.inputs;
    }
    public void setInputs(double[] inputs){
        this.inputs = Matrix.fromArray(inputs);
    }
    public void setInputs(Matrix inputs){
        this.inputs = inputs;
    }
    public Matrix getBiases(){
        return this.biases;
    }
    
    public Matrix calculateOutput(){
        Matrix a = Matrix.multiply(this.weights, this.inputs);
        a.add(this.biases);
        a.sigmoid();
        return a;
    }
}
