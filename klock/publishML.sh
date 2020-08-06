echo "publishing klock"
chmod +x gradlew
./gradlew :publishToMavenLocal || exit
echo "Finished publishing klock"