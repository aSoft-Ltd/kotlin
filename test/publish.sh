echo "publishing test"
chmod +x gradlew
./gradlew :publish || exit
echo "Finished publishing test"
