echo "publishing logging"
chmod +x gradlew
./gradlew :logging-core:publish || exit
echo "Finished publishing logging"