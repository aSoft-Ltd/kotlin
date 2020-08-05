echo "Building result"
chmod +x gradlew
./gradlew wrapper
./gradlew :build
echo "Finished building result"