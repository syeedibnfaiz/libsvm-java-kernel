package kernel;

import kernel.ds.SparseVector;
import libsvm.svm_node;
import kernel.ds.SparseVector.Element;

/**
 *  <code>LinearKernel</code> implements a linear kernel function.
 * @author Syeed Ibn Faiz
 */
public class LinearKernel implements CustomKernel {

    @Override
    public double evaluate(svm_node x, svm_node y) {                        
        if (!(x.data instanceof SparseVector) || !(y.data instanceof SparseVector)) {
            throw new RuntimeException("svm_nodes should contain sparse vectors.");
        }
        
        SparseVector v1 = (SparseVector) x.data;
        SparseVector v2 = (SparseVector) y.data;
        double result = 0.0;
        int i = 0;
        int j = 0;        
        
        while (i < v1.size() && j < v2.size()) {
            Element e1 = v1.get(i);
            Element e2 = v2.get(j);
            
            if (e1.index == e2.index) {
                result += e1.value * e2.value;
                i++;
                j++;
            } else if (e1.index < e2.index) {
                i++;
            } else {
                j++;
            }            
        }
        
        return result;
    }
    
}
