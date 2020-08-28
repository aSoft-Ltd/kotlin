echo "publishing logging"
chmod +x gradlew
./gradlew :logging-core:publishToMavenLocal || exit
echo "Finished publishing logging"
