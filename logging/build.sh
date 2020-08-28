echo "Building logging"
chmod +x gradlew
echo "Running Wrapper"
./gradlew wrapper || exit
./gradlew :logging-core:build || exit
echo "Finished building logging"