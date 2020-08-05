echo "Building logging"
chmod +x gradlew
echo "Running Wrapper"
./gradlew wrapper || exit
./gradlew :build || exit
echo "Finished building logging"