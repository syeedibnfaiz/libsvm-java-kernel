#!/bin/bash
#This script checks the integrity of the library
#The output produced by the library is matched against
#that produced by the original tool

#generate output
#note that the Demo uses the linear kernel
java -cp dist/libsvm-java.jar Demo a1a.train a1a.test a1a.out

#download libsvm
wget http://www.csie.ntu.edu.tw/~cjlin/cgi-bin/libsvm.cgi?+http://www.csie.ntu.edu.tw/~cjlin/libsvm+tar.gz
mv libsvm*  libsvm.tar.gz 
tar xzf libsvm.tar.gz
rm libsvm.tar.gz
cd libsvm*
make
#generate output using the linear kernel
./svm-train -t 0 ../a1a.train a1a.train.model
./svm-predict ../a1a.test a1a.train.model a1a.out
cd ..
diff -w a1a.out ./libsvm*/a1a.out

 