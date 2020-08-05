echo "publishing theme"
chmod +x gradlew
echo "publishing theme-core"
./gradlew :theme-core:publish
echo "publishing theme-react"
./gradlew :theme-react:publish
echo "Finished publishing theme"
