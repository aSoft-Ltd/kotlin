echo "Publishing daos"
chmod +x gradlew || exit
echo "Publishing firestore-dao"
./gradlew :firestore-dao:publishToMavenLocal || exit
echo "Publishing neo4j-dao"
./gradlew :neo4j-dao:publishToMavenLocal || exit
echo "Publishing mongo-dao"
./gradlew :mongo-dao:publishToMavenLocal || exit
echo "Publishing inmemory-dao"
./gradlew :inmemory-dao:publishToMavenLocal || exit
echo "Publishing rest-dao"
./gradlew :rest-dao:publishToMavenLocal || exit
echo "Finished publishing daos"