echo "Publishing rest-api"
chmod +x gradlew
echo "Publishing rest-api-core"
./gradlew :rest-api-core:publishToMavenLocal || exit
echo "Publishing rest-api-ktor"
./gradlew :rest-api-ktor:publishToMavenLocal || exit
echo "Finished publishind rest-api"
