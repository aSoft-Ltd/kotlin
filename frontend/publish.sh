echo "Publishing frontend"
chmod +x gradlew
echo "Running wrapper"
./gradlew wrapper || exit
./gradlew :publish || exit
echo "Finished publishing frontend"