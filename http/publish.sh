echo "publishing http"
chmod +x gradlew
echo "Running Wrapper"
./gradlew wrapper || exit
./gradlew :publish || exit
echo "Finished publishing http"