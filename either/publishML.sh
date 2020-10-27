echo "publishing either"
chmod +x gradlew
./gradlew :publishToMavenLocal || exit
echo "Finished publishing either"