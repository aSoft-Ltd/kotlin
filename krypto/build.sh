echo "Building krypto"
chmod +x gradlew
echo "Running Wrapper"
./gradlew wrapper
./gradlew :build
echo "Finished building krypto"