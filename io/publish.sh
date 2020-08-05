echo "publishing io"
chmod +x gradlew
echo "Running Wrapper"
./gradlew wrapper
./gradlew :publish
echo "Finished publishing io"