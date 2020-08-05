echo "Publishing persist"
chmod +x gradlew
echo "Running wrapper"
./gradlew wrapper
./gradlew :publish
echo "Finished publishing persist"