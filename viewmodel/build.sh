echo "Building viemodel"
chmod +x gradlew
echo "Upgrading wrapper"
./gradlew wrapper || exit
echo "Building viewmode-core"
./gradlew :viewmodel-core:build || exit
echo "Building viewmodel-react"
./gradlew :viewmodel-react:build || exit
echo "Finished building viewmodel"
