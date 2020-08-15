echo "Building Form"
chmod +x gradlew

echo "Running Wrapper"
./gradlew wrapper || exit

echo "Building form-http"
./gradlew :form-http:build || exit

echo "Building from-html"
./gradlew :form-html:build || exit

echo "Building form-react"
./gradlew :form-react:build || exit
echo "Finished building form"