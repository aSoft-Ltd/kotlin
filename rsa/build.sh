echo "Building result"
chmod +x gradlew
./gradlew wrapper
./gradlew :build || exit
echo "Finished building result"