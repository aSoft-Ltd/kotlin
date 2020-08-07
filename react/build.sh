echo "Building react"
chmod +x gradlew
./gradlew wrapper
echo "Building react-core"
./gradlew :react-core:build || exit
echo "Building react-buttons"
./gradlew :react-buttons:build || exit
echo "Building react-layouts"
./gradlew :react-layouts:build || exit
echo "Building react-tabs"
./gradlew :react-tabs:build || exit
echo "Building react-text"
./gradlew :react-text:build || exit
echo "Building react-feedback"
./gradlew :react-feedback:build || exit
echo "Building react-media"
./gradlew :react-media:build || exit
echo "Building react-inputs"
./gradlew :react-inputs:build || exit
echo "Building react-navigation"
./gradlew :react-navigations:build || exit
echo "Building react-tables"
./gradlew :react-tables:build || exit
echo "Building webcomposites"
./gradlew :react-webcomposites:build || exit
echo "Building webpages"
./gradlew :react-webpages:build || exit
