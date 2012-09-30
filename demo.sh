#!/bin/bash
#build
ant
java -cp dist/libsvm-java.jar Demo a1a.train a1a.test a1a.out

