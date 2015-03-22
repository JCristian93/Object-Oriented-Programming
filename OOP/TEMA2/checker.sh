#!/bin/bash

# compiling sources
find -name "*.java" > sources
rm -rf bin
mkdir bin
javac @sources -d bin

# cleanup
rm sources
rm -rf output_files
mkdir output_files

# testing
sum=0
fileList=`ls input_files`

for test in $fileList
do
	java -cp bin Main input_files/$test	
	testOut=$test"_out"
	mv input_files/$testOut output_files/$testOut
	diffOutput=`diff -Bw output_files/$testOut ref_files/$testOut &> /dev/null`
	errorCode=$?
	diffOutput=`echo $diffOutput | wc -c`
	if [ 1 -eq $diffOutput ] && [ $errorCode -eq 0 ]
	then
		sum=$(($sum+1))
		echo $test .................................... Passed
	else
		echo $test .................................... Failed
	fi

done

numberOfTests=`ls -l input_files | wc -l`
numberOfTests=$(($numberOfTests-1))
echo
echo
echo "Grade .................................... $sum/$numberOfTests"

# clean up
rm -rf bin
rm -rf "output_files"




