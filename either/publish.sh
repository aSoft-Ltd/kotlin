echo "publishing either"
chmod +x gradlew
./gradlew :publish || exit
echo "Finished publishing either"