echo "publishing task"
chmod +x gradlew
./gradlew :publish || exit
echo "Finished publishing task"