echo "Building test"
chmod +x gradlew
./gradlew wrapper
./gradlew :build || exit
echo "Finished building test"