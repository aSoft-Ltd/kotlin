echo "Building storage"
chmod +x gradlew
./gradlew :build || exit
echo "Finished building storage"