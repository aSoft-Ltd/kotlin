echo "Building paging"
chmod +x gradlew || exit
echo "Building paging-core"
./gradlew :paging-core:build || exit
echo "Building paging-react"
./gradlew :paging-react:build || exit
echo "Finished building paging"