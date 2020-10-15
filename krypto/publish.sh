echo "publishing krypto"
chmod +x gradlew
./gradlew :publish || exit
echo "Finished publishing krypto"