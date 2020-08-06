echo "Building deployment"
chmod +x gradlew
./gradlew wrapper
./gradlew :jar || exit
echo "Finished building deployment"