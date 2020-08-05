echo "Publishing Tools"
chmod +x gradlew
echo "Publishing tools-core"
./gradlew :tools-core:publish || exit
echo "Publishing tools-react"
./gradlew :tools-react:publish || exit
echo "Finished publishing tools"
