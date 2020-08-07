echo "Building theme"
chmod +x gradlew
./gradlew wrapper || exit
echo "Building theme-core"
./gradlew :theme-core:build || exit
echo "Building theme-react"
./gradlew :theme-react:build || exit
echo "Finished building theme"
