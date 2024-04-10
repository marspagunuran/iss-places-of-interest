#!/bin/bash

# Check if an image name is provided as an argument
if [ $# -ne 1 ]; then
    echo "Usage: $0 <your-image-name>"
    exit 1
fi

# Assign the first argument to the variable image_name
image_name=$1

# Step 1: Build the Docker Image
docker build -t $image_name .

# Step 2: Run Docker image/container
docker run --rm -p 8080:8080 $image_name
