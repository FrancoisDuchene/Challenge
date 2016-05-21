#!/bin/bash

echo "This script will install the challenge program"
echo "Utilisation of the ANT building script"

E_JAVAC_DONTEXIST=3
E_ANT_DONTEXIST=4

if [[ -f "/usr/bin/javac" ]]; then

  if [[ -f "/usr/bin/ant" ]]; then

    echo "Compiling source file"
    ant build
    echo "Build Succeded"
    echo "Creating a .jar file"
    ant clean
    echo "Jar created"

    echo
    echo "Do you want to run the program now ? (y/n)"

    read rep
    while [[ "$rep" != "y" && "$rep" != "n" ]]; do
      echo "Please indicate y for yes or n for no"
      read rep
    done
    if [[ "$rep" == "y" ]]; then
      java -jar Challenge.jar
    fi
    echo "You still can run the program or in running the command"
    echo "$ java -jar Challenge.jar"
    echo "Or in execute the jar with the JVM itself"
    exit 0
  else
    echo "Compiling fail"
    echo "Error $E_ANT_DONTEXIST E_ANT_DONTEXIST"
    echo "The ant program is not installed on this device"
    echo "To compile correctly the source, this program need ant"
    echo
    echo "You can dowload it on the Apache Foundation webiste"
    echo http://ant.apache.org/index.html
    echo
    echo "BUILD FAILED"

    exit $E_ANT_DONTEXIST
  fi

else
  echo "Compiling fail"
  echo "Error $E_JAVAC_DONTEXIST E_JAVAC_DONTEXIST"
  echo "Javac is not installed on this device"
  echo
  echo "You can dowload the JDK (Java Developpement kit) on the Oracle website"
  echo https://www.java.com/en/download/faq/develop.xml
  echo
  echo "BUILD FAILED"

  exit $E_JAVAC_DONTEXIST
fi
