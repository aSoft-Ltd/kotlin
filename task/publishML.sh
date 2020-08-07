echo "publishing task"
chmod +x gradlew
./gradlew :publishToMavenLocal || exit
echo "Finished publishing task"