echo "publishing logging"
chmod +x gradlew
./gradlew :publish || exit
echo "Finished publishing logging"