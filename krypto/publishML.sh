echo "publishing krypto"
chmod +x gradlew
./gradlew :publishToMavenLocal || exit
echo "Finished publishing krypto"