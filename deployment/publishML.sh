echo "Publishing deployment"
chmod +x gradlew
./gradlew wrapper
./gradlew :publishToMavenLocal || exit
echo "Finished publishing deployment"
