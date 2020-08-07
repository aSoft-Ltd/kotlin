echo "Publishing react"
chmod +x gradlew
echo "Publishing react-core"
./gradlew :react-core:publishToMavenLocal || exit
echo "Publishing react-buttons"
./gradlew :react-buttons:publishToMavenLocal || exit
echo "Publishing react-layouts"
./gradlew :react-layouts:publishToMavenLocal || exit
echo "Publishing react-tabs"
./gradlew :react-tabs:publishToMavenLocal || exit
echo "Publishing react-text"
./gradlew :react-text:publishToMavenLocal || exit
echo "Publishing react-feedback"
./gradlew :react-feedback:publishToMavenLocal || exit
echo "Publishing react-media"
./gradlew :react-media:publishToMavenLocal || exit
echo "Publishing react-inputs"
./gradlew :react-inputs:publishToMavenLocal || exit
echo "Publishing react-navigation"
./gradlew :react-navigations:publishToMavenLocal || exit
echo "Publishing react-tables"
./gradlew :react-tables:publishToMavenLocal || exit
echo "Publishing webcomposites"
./gradlew :react-webcomposites:publishToMavenLocal || exit
echo "Publishing webpages"
./gradlew :react-webpages:publishToMavenLocal || exit
