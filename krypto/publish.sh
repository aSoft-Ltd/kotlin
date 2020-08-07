echo "publishing krypto"
chmod +x gradlew
echo "Running Wrapper"
./gradlew wrapper
./gradlew :publish || exit
echo "Finished publishing krypto"