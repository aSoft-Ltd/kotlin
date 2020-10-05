echo "Publishing jwt"
chmod +x gradlew
echo "Publishing jwt-core"
./gradlew :jwt-core:publishToMavenLocal || exit
echo "Publishing jwt-hs"
./gradlew :jwt-hs:publishToMavenLocal || exit
echo "Finished publishing jwt"