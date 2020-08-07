echo "Building persist"
chmod +x gradlew
echo "Running wrapper"
./gradlew wrapper || exit
./gradlew :build || exit
echo "Finished building persist"