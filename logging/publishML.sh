echo "publishing logging"
chmod +x gradlew
./gradlew :publishToMavenLocal || exit
echo "Finished publishing logging"