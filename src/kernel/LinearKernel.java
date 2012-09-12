package kernel;

import datastruct.SparseVector;
import libsvm.svm_node;
import datastruct.SparseVector.Element;

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
                        
        return v1.dot(v2);
    }
    
}
