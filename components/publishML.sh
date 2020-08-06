echo "Publishing components"
chmod +x gradlew
echo "Publishing components-react"
./gradlew :components-react:publishToMavenLocal || exit
echo "Finished publishing components"
