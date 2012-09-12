package utils;

import datastruct.SparseVector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import libsvm.Instance;
import libsvm.svm_node;
import libsvm.svm_problem;

/**
 * <code>DataFileReader</code> reads data files written in LibSVM format.
 * @author Syeed Ibn Faiz
 */
public class DataFileReader {
    
    public static Instance[] readDataFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        ArrayList<Double> labels = new ArrayList<Double>();
        ArrayList<SparseVector> vectors = new ArrayList<SparseVector>();
        
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split("\\s+");
            if (tokens.length < 2) {
                throw new RuntimeException("Inappropriate data file.");
            }
            
            labels.add(Double.parseDouble(tokens[0]));            
            SparseVector vector = new SparseVector(tokens.length - 1);
            
            for (int i = 1; i < tokens.length; i++) {
                String[] fields = tokens[i].split(":");
                if (fields.length < 2) {
                    throw new RuntimeException("Inappropriate data file.");
                }
                int index = Integer.parseInt(fields[0]);
                double value = Double.parseDouble(fields[1]);
                vector.add(index, value);
            }
            
            vectors.add(vector);
        }                
        
        Instance[] instances = new Instance[labels.size()];
        for (int i = 0; i < instances.length; i++) {
            instances[i] = new Instance(labels.get(i), vectors.get(i));
        }
        
        return instances;
    }
}
