echo "Publishing components"
chmod +x gradlew
echo "Publishing components-react"
./gradlew :components-react:publish || exit
echo "Finished publishing components"
