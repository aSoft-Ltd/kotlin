echo "Building task"
chmod +x gradlew
./gradlew wrapper
./gradlew :build
echo "Finished building task"