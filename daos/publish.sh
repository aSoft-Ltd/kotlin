echo "Publishing daos"
chmod +x gradlew || exit
echo "Publishing firestore-dao"
./gradlew :firestore-dao:publish || exit
echo "Publishing neo4j-dao"
./gradlew :neo4j-dao:publish || exit
echo "Publishing mongo-dao"
./gradlew :mongo-dao:publish || exit
echo "Publishing inmemory-dao"
./gradlew :inmemory-dao:publish || exit
echo "Publishing restful-dao"
./gradlew :restful-dao:publish || exit
echo "Finished publishing daos"