echo "Building daos"
chmod +x gradlew || exit
echo "Running wrapper"
./gradlew wrapper || exit
echo "Building firestore-dao"
./gradlew :firestore-dao:build || exit
echo "Building neo4j-dao"
./gradlew :neo4j-dao:build || exit
echo "Building rest-dao"
./gradlew :rest-dao:build || exit
echo "Finished building daos"