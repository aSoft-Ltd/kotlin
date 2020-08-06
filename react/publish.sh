echo "Publishing react"
chmod +x gradlew
echo "Publishing react-core"
./gradlew :react-core:publish || exit
echo "Publishing react-buttons"
./gradlew :react-buttons:publish || exit
echo "Publishing react-layouts"
./gradlew :react-layouts:publish || exit
echo "Publishing react-tabs"
./gradlew :react-tabs:publish || exit
echo "Publishing react-text"
./gradlew :react-text:publish || exit
echo "Publishing react-feedback"
./gradlew :react-feedback:publish || exit
echo "Publishing react-media"
./gradlew :react-media:publish || exit
echo "Publishing react-inputs"
./gradlew :react-inputs:publish || exit
echo "Publishing react-navigation"
./gradlew :react-navigations:publish || exit
echo "Publishing react-tables"
./gradlew :react-tables:publish || exit
echo "Publishing webcomposites"
./gradlew :react-webcomposites:publish || exit
echo "Publishing webpages"
./gradlew :react-webpages:publish || exit
