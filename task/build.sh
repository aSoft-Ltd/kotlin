echo "Building task"
chmod +x gradlew
./gradlew wrapper || exit
./gradlew :build || exit
echo "Finished building task"