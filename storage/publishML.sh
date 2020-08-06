echo "publishing storage"
chmod +x gradlew
./gradlew :publishToMavenLocal || exit
echo "Finished publishing storage"