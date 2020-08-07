echo "publishing Money"
chmod +x ./gradlew
./gradlew :publishToMavenLocal || exit
echo "Finished publishing money"
