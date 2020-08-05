echo "Building geo"
chmod +x gradlew || exit
echo "Running Wrapper"
./gradlew wrapper || exit
./gradlew :build || exit
echo "Finished building geo"
