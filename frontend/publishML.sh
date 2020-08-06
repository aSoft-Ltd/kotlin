echo "Publishing frontend"
chmod +x gradlew
echo "Running wrapper"
./gradlew wrapper || exit
./gradlew :publishToMavenLocal || exit
echo "Finished publishing frontend"