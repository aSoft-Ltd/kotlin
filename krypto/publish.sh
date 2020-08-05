echo "publishing krypto"
chmod +x gradlew
echo "Running Wrapper"
./gradlew wrapper
./gradlew :publish
echo "Finished publishing krypto"