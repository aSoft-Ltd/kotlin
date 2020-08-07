echo "Building Money"
chmod +x ./gradlew
echo "Running Wrapper"
./gradlew wrapper
./gradlew :build || exit
echo "Finished building money"
