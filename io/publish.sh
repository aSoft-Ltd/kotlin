echo "publishing io"
chmod +x gradlew
echo "Running Wrapper"
./gradlew wrapper
./gradlew :publish || exit
echo "Finished publishing io"