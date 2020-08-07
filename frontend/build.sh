echo "Building frontend"
chmod +x gradlew
echo "Running wrapper"
./gradlew wrapper || exit
./gradlew :jar || exit
echo "Finished building frontend"