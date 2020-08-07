echo "Building text-editor"
chmod +x gradlew
echo "Buidling text-editor-core"
./gradlew :text-editor-core:build || exit
echo "Building text-editor-react"
./gradlew :text-editor-react:build || exit
echo "Finished building text-editor"