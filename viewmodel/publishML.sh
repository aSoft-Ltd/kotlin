echo "Publishing viemodel"
chmod +x gradlew
echo "Publishing viewmode-core"
./gradlew :viewmodel-core:publishToMavenLocal || exit
echo "Publishing viewmodel-react"
./gradlew :viewmodel-react:publishToMavenLocal || exit
echo "Finished publishing viewmodel"
