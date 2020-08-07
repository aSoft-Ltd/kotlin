echo "publishing io"
chmod +x gradlew
echo "Running Wrapper"
./gradlew wrapper
./gradlew :publishToMavenLocal || exit
echo "Finished publishing io"