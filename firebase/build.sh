echo "Building Firebase"
chmod +x gradlew || exit
echo "Running Wrapper"
./gradlew wrapper || exit
echo "Building firebase-core"
./gradlew :firebase-core:build || exit
echo "Building firebase-auth"
./gradlew :firebase-auth:build || exit
echo "Building firebase-firestore"
./gradlew :firebase-firestore:build || exit
echo "Building firebase-storage"
./gradlew :firebase-storage:build || exit
echo "Finished building firebase"