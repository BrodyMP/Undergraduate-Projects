#!/bin/bash

# File: test.sh
# Description: Does unit testing on satcom.

SRC="./src"     # Source code dir.
TEST="./test"   # Unit tests dir.

# Do Class Functionality Testing
for file in $TEST/0*.cpp; do
   prefix=${file%*.cpp}
   g++ --std=c++11 $file $SRC/point3d.cpp -o $prefix 2>/dev/null
   $prefix 2>/dev/null
   if [ $? -ne 0 ]; then
      echo "$prefix failed <---"
   else
      echo "$prefix passed"
   fi
   rm $prefix
done

# Do Input Testing
for f in $TEST/1*.in; do
   f=${f%*.in}
   ./bin/satcom < ${f}.in > ${f}.tmp
   DIFF=$(diff ${f}.ans ${f}.tmp)
   if [ "$DIFF" != "" ]; then
      echo "${f} failed <---"
   else
      echo "${f} passed"
   fi
   rm ${f}.tmp
done
