echo "publishing test"
chmod +x gradlew
./gradlew :publishMavenLocal || exit
echo "Finished publishing test"
