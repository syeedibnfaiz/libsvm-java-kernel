
import java.io.IOException;
import kernel.KernelManager;
import kernel.LinearKernel;
import libsvm.Instance;
import libsvm.SVMPredictor;
import libsvm.SVMTrainer;
import libsvm.svm_model;
import libsvm.svm_parameter;
import utils.DataFileReader;

/**
 * <code>Main</code> shows how to use the library
 * @author Syeed Ibn Faiz
 */
public class Main {
    
    public static void testLinearKernel() throws IOException, ClassNotFoundException {
        //Read training file
        Instance[] trainingInstances = DataFileReader.readDataFile("a1a.train");        
        
        //Register kernel function
        KernelManager.setCustomKernel(new LinearKernel());        
        
        //Setup parameters
        svm_parameter param = new svm_parameter();                
        
        //Train the model
        System.out.println("Training started...");
        svm_model model = SVMTrainer.train(trainingInstances, param);
        System.out.println("Training completed.");
                
        //Save the trained model
        //SVMTrainer.saveModel(model, "a1a.model");
        //model = SVMPredictor.load_model("a1a.model");
        
        //Read test file
        Instance[] testingInstances = DataFileReader.readDataFile("a1a.test");
        //Predict results
        SVMPredictor.predict(testingInstances, model, true);
        
        //SVMTrainer.doCrossValidation(trainingInstances, param, 10, true);
        //SVMTrainer.doInOrderCrossValidation(trainingInstances, param, 10, true);
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        testLinearKernel();
    }
}
