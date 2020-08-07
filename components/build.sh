echo "Building components"
chmod +x gradlew
echo "Running wrapper"
./gradlew wrapper || exit
echo "Building components-react"
./gradlew :components-react:build || exit
echo "Finished building components"
