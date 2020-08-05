echo "Building test"
chmod +x gradlew
./gradlew wrapper
./gradlew :build
echo "Finished building test"