#!/bin/bash

# Check if JDK is installed
if ! command -v javac &> /dev/null; then
    echo "Error: JDK is not installed. Please install the Java Development Kit."
    exit 1
fi

# Compile Java code
javac MouseJiggler.java

# Create an executable JAR file
jar cfe MouseJiggler.jar MouseJiggler MouseJiggler.class

# Run the JAR file
java -jar MouseJiggler.jar