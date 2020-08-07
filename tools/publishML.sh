echo "Publishing Tools"
chmod +x gradlew
./gradlew :publishToMavenLocal || exit
echo "Finished publishing tools"
