echo "Publishing Firebase"
chmod +x gradlew || exit
echo "Running Wrapper"
./gradlew wrapper || exit
echo "Publishing firebase-core"
./gradlew :firebase-core:publishToMavenLocal || exit
echo "Publishing firebase-auth"
./gradlew :firebase-auth:publishToMavenLocal || exit
echo "Publishing firebase-firestore"
./gradlew :firebase-firestore:publishToMavenLocal || exit
echo "Publishing firebase-storage"
./gradlew :firebase-storage:publishToMavenLocal || exit
echo "Finished publishing firebase"