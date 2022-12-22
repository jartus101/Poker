import java.util.ArrayList;
import java.util.List;
public class NeuralNetwork {
    ArrayList<Integer> layerSizes;
    ArrayList<Layer> layers;
    Matrix nextInputs;
    Layer a;
    public NeuralNetwork(ArrayList<Integer> layerSizes){
        ArrayList<Layer> layers = new ArrayList<>();
        for(int i = 0; i<layerSizes.size(); i++){
            if(i!=layerSizes.size()-1){
                this.a  = new Layer(layerSizes.get(i), layerSizes.get(i+1));
            } else {
                this.a  = new Layer(layerSizes.get(i), 1);
            }
            layers.add(this.a);
        }
        this.layers = layers;
    }
    public Layer getLayer(int index){
        return (this.layers).get(index);
    }
    public List<Double> calculateTotalOutput(double[] inputs){
        List<Double> a = new ArrayList<>();
        for(int i = 0; i<this.layers.size(); i++){
            if(i==0){
                this.layers.get(i).setInputs(inputs);
                nextInputs = this.layers.get(i).calculateOutput();
            } else if(i!=this.layers.size()-1){
                this.layers.get(i).setInputs(nextInputs);
                nextInputs = this.layers.get(i).calculateOutput();
            } else {
                this.layers.get(i).setInputs(nextInputs);
                a = this.layers.get(i).getInputs().toArray();
            }
        }
        return a;
    }
    public Integer classify(double[] inputs){
        int maxAt = 0;
        List<Double> a = calculateTotalOutput(inputs);
        for (int i = 0; i < a.size(); i++) {
            if(a.get(i)>a.get(maxAt)){
                maxAt = i;
            }
        }
        return maxAt;
    }
    public double nodeCost(double expected, double actual){
        double a = expected - actual;
        return a * a;
    }
    public static ArrayList<Double> correctCostList(int correctNumber){
        ArrayList<Double> a = new ArrayList<>();
        for(int i = 0; i<10; i++){
            if(i==correctNumber){
                a.add(1.0);
            } else {
                a.add(0.0);
            }
        }
        return a;
    }
    public static Matrix costLists(ArrayList<Double> correctCosts, List<Double> outputs){
        ArrayList<Double> costList = new ArrayList<>();
        for(int i = 0; i<correctCosts.size(); i++){
            costList.add(Math.pow(correctCosts.get(i)-outputs.get(i), 2));
        }
        double[] arr = new double[costList.size()];
        for (int i = 0; i < costList.size(); i++)
            arr[i] = costList.get(i);
        Matrix b = Matrix.fromArray(arr);
        return b;
    }
    public static double cost(Matrix a){
        List<Double> b = a.toArray();
        double c = 0;
        for(int i = 0; i<b.size(); i++){
            c+=b.get(i);
        }
        return c;
    }
    /* 
    public double cost(List<Double> trainingData){
        double cost = 0;
        for(int i = 0; i<trainingData.size(); i++){

        }
    }
    */
    public List<Double> weightMatrixesToList(){
        List<Double> newList = new ArrayList<>();
        for(Layer layer: this.layers){
            Matrix weights = layer.getWeights();
            List<Double> weightsList = weights.toArray();
            for(int i = 0; i < weightsList.size(); i++){
                newList.add(weightsList.get(i));
            }
        }
        return newList;
    }
    public List<Double> biasMatrixesToList(){
        List<Double> newList = new ArrayList<>();
        for(Layer layer: this.layers){
            Matrix biases = layer.getBiases();
            List<Double> biasesList = biases.toArray();
            for(int i = 0; i < biasesList.size(); i++){
                newList.add(biasesList.get(i));
            }
        }
        newList.remove(newList.get(newList.size()-1));
        return newList;
    }
    public Matrix newGradientMatrix(Matrix a, Matrix b){
        Matrix c = Matrix.subtract(a, b);
        return c;
    } 
    public void applyNewWeightGradients(ArrayList<Matrix> a){
        for(int i = 0; i < this.layers.size(); i++){
            Layer c = this.layers.get(i);
            c.setWeights(a.get(i));
        }
    }
    public void applyNewBiasGradients(ArrayList<Matrix> a){
        for(int i = 0; i < this.layers.size(); i++){
            Layer c = this.layers.get(i);
            c.setBiases(a.get(i));
        }
    }
}
