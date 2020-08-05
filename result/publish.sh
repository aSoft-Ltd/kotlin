echo "publishing result"
chmod +x gradlew
./gradlew wrapper
./gradlew :publish
echo "Finished publishing result"