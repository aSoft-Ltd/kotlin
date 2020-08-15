echo "Building rest-api"
chmod +x gradlew || exit
echo "Running wrapper"
./gradlew wrapper || exit
echo "Building rest-api-core"
./gradlew :rest-api-core:build || exit
echo "Building rest-api-ktor"
./gradlew :rest-api-ktor:build || exit
echo "Finished buildind rest-api"
