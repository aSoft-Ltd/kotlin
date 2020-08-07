echo "publishing Money"
chmod +x ./gradlew
./gradlew :publish || exit
echo "Finished publishing money"
