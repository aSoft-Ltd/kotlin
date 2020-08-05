echo "Building Tools"
chmod +x gradlew
echo "Running wrapper"
./gradlew wrapper
echo "Building tools-core"
./gradlew :tools-core:build || exit
echo "Building tools-react"
./gradlew :tools-react:build || exit
echo "Finished building tools"
