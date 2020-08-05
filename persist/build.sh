echo "Building persist"
chmod +x gradlew
echo "Running wrapper"
./gradlew wrapper
./gradlew :build
echo "Finished building persist"