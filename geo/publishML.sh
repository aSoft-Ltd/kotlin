echo "publishing geo"
chmod +x gradlew || exit
./gradlew :publishToMavenLocal || exit
echo "Finished publishing geo"
