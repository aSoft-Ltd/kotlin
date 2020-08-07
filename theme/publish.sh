echo "publishing theme"
chmod +x gradlew
echo "publishing theme-core"
./gradlew :theme-core:publish || exit
echo "publishing theme-react"
./gradlew :theme-react:publish || exit
echo "Finished publishing theme"
