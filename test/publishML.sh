echo "publishing test"
chmod +x gradlew
./gradlew :publishToMavenLocal || exit
echo "Finished publishing test"
