echo "Publishing jwt"
chmod +x gradlew
echo "Publishing jwt-core"
./gradlew :jwt-core:publish || exit
echo "Publishing jwt-hs"
./gradlew :jwt-hs:publish || exit
echo "Finished publishing jwt"