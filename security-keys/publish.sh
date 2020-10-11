echo "publishing result"
chmod +x gradlew
./gradlew :publish || exit
echo "Finished publishing result"