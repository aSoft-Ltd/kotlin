echo "publishing theme"
chmod +x gradlew
echo "publishing theme-core"
./gradlew :theme-core:publishToMavenLocal || exit
echo "publishing theme-react"
./gradlew :theme-react:publishToMavenLocal || exit
echo "Finished publishing theme"
