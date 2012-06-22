
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
        Instance[] trainingInstances = DataFileReader.readDataFile("a1a.train");
        KernelManager.setCustomKernel(new LinearKernel());
        svm_parameter param = new svm_parameter();
        
        System.out.println("Training started...");
        svm_model model = SVMTrainer.train(trainingInstances, param);
        System.out.println("Training completed.");
                
        //SVMTrainer.saveModel(model, "a1a.model");
        //model = SVMPredictor.load_model("a1a.model");
        
        Instance[] testingInstances = DataFileReader.readDataFile("a1a.test");
        SVMPredictor.predict(testingInstances, model);
        
        //SVMTrainer.doCrossValidation(trainingInstances, param, 10, true);
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        testLinearKernel();
    }
}
