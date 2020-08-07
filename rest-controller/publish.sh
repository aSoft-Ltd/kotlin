echo "Publishing rest-controller"
chmod +x gradlew
echo "Publishing rest-controller-core"
./gradlew :rest-controller-core:publish || exit
echo "Publishing rest-controller-ktor"
./gradlew :rest-controller-ktor:publish || exit
echo "Finished publishind rest-controller"