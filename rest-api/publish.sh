echo "Publishing rest-api"
chmod +x gradlew
echo "Publishing rest-api-core"
./gradlew :rest-api-core:publish || exit
echo "Publishing rest-api-ktor"
./gradlew :rest-api-ktor:publish || exit
echo "Finished publishind rest-api"
