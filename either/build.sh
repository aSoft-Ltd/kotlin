echo "Building either"
chmod +x gradlew
./gradlew wrapper
./gradlew :build || exit
echo "Finished building either"