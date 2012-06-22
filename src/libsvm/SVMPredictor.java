package libsvm;


import java.io.IOException;

/**
 *
 * @author Syeed Ibn Faiz
 */
public class SVMPredictor {


    public static double[] predict(Instance[] instances, svm_model model) {
        int total = 0;
        int correct = 0;
        
        int tp = 0;
        int fp = 0;
        int fn = 0;
        
        boolean binary = model.nr_class == 2;
        double[] predictions = new double[instances.length];
        int count = 0;
        
        for (Instance instance : instances) {
            double target = instance.getLabel();                                    
            double p = svm.svm_predict(model, new svm_node(instance.getData()));
            predictions[count++] = p;
            
            ++total;
            if (p == target) {
                correct++;
                if (target > 0) {
                    tp++;
                }
            } else if (target > 0) {
                fn++;
            } else {
                fp++;
            }
        }
        
        System.out.print("Accuracy = " + (double) correct / total * 100
                + "% (" + correct + "/" + total + ") (classification)\n");
        
        if (binary) {
            double precision = (double)tp/(tp+fp);
            double recall = (double)tp/(tp+fn);
            System.out.println("Precision: " + precision);
            System.out.println("Recall: " + recall);
            System.out.println("Fscore: " + 2*precision*recall/(precision + recall));
        }
        
        return predictions;
    }
    
    public static svm_model load_model(String filePath) throws IOException, ClassNotFoundException {
        return svm.svm_load_model(filePath);
    }
}
