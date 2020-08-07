echo "Publishing paging"
chmod +x gradlew || exit
echo "Publishing paging-core"
./gradlew :paging-core:publish || exit
echo "Publishing paging-react"
./gradlew :paging-react:publish || exit
echo "Finished publishing paging"