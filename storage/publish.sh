echo "publishing storage"
chmod +x gradlew
./gradlew :publish || exit
echo "Finished publishing storage"