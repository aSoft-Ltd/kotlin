echo "Building theme"
chmod +x gradlew
./gradlew wrapper
echo "Building theme-core"
./gradlew :theme-core:build
echo "Building theme-react"
./gradlew :theme-react:build
echo "Finished building theme"
