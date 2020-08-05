echo "Publishing viemodel"
chmod +x gradlew
echo "Publishing viewmode-core"
./gradlew :viewmodel-core:publish || exit
echo "Publishing viewmodel-react"
./gradlew :viewmodel-react:publish || exit
echo "Finished publishing viewmodel"
