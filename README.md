Refactored LibSVM in Java making it easy to plug in a custom kernel.

Use demo.sh to run the demo program.
To learn how you can use the library see src/Demo.java.

To write a kernel all you have to do is to implement kernel.CustomKernel and
then register your kernel with the kernel.KernelManager.

For example, the following code snippet registers an anonymous custom kernel:
KernelManager.setCustomKernel(new CustomKernel() {
            @Override
            public double evaluate(svm_node x, svm_node y) {
                //do something fancy
                return 0.3141592654;
            }
        });


Syeed Ibn Faiz
University of Western Ontario
syeedibnfaiz@gmail.com
