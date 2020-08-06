echo "Publishing paging"
chmod +x gradlew || exit
echo "Publishing paging-core"
./gradlew :paging-core:publishToMavenLocal || exit
echo "Publishing paging-react"
./gradlew :paging-react:publishToMavenLocal || exit
echo "Finished publishing paging"