echo "Building Tools"
chmod +x gradlew
echo "Running wrapper"
./gradlew wrapper
./gradlew :build || exit