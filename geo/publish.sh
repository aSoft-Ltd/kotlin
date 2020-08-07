echo "publishing geo"
chmod +x gradlew || exit
./gradlew :publish || exit
echo "Finished publishing geo"
