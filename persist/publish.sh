echo "Publishing persist"
chmod +x gradlew
./gradlew :publish || exit
echo "Finished publishing persist"