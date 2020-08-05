echo "Publishing daos"
chmod +x gradlew || exit
echo "Publishing firestore-dao"
./gradlew :firestore-dao:publish || exit
echo "Publishing neo4j-dao"
./gradlew :neo4j-dao:publish || exit
echo "Publishing rest-dao"
./gradlew :rest-dao:publish || exit
echo "Finished publishing daos"