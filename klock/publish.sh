echo "publishing klock"
chmod +x gradlew
./gradlew :publish || exit
echo "Finished publishing klock"