echo "Building Form"
chmod +x gradlew
echo "Running Wrapper"
./gradlew wrapper
echo "Building form-react"
./gradlew :form-react:build
echo "Finished building form"