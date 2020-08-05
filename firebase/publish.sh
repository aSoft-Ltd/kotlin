echo "Publishing Firebase"
chmod +x gradlew || exit
echo "Running Wrapper"
./gradlew wrapper || exit
echo "Publishing firebase-core"
./gradlew :firebase-core:publish || exit
echo "Publishing firebase-auth"
./gradlew :firebase-auth:publish || exit
echo "Publishing firebase-firestore"
./gradlew :firebase-firestore:publish || exit
echo "Publishing firebase-storage"
./gradlew :firebase-storage:publish || exit
echo "Finished publishing firebase"