echo "Building Form"
chmod +x gradlew
echo "Running Wrapper"
./gradlew wrapper || exit
echo "Building form-core"
./gradlew :form-core:build || exit
echo "Building form-react"
./gradlew :form-react:build || exit
echo "Finished building form"