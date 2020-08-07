echo "Publishing rest-controller"
chmod +x gradlew
echo "Publishing rest-controller-core"
./gradlew :rest-controller-core:publishToMavenLocal || exit
echo "Publishing rest-controller-ktor"
./gradlew :rest-controller-ktor:publishToMavenLocal || exit
echo "Finished publishind rest-controller"