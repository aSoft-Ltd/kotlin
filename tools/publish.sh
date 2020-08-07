echo "Publishing Tools"
chmod +x gradlew
./gradlew :publish || exit
echo "Finished publishing tools"
