echo "Building rest-controller"
chmod +x gradlew || exit
echo "Running wrapper"
./gradlew wrapper || exit
echo "Building rest-controller-core"
./gradlew :rest-controller-core:build || exit
echo "Building rest-controller-ktor"
./gradlew :rest-controller-ktor:build || exit
echo "Finished buildind rest-controller"