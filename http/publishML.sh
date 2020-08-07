echo "publishing http"
chmod +x gradlew
echo "Running Wrapper"
./gradlew wrapper || exit
./gradlew :publishToMavenLocal || exit
echo "Finished publishing http"