echo "Publishing Email"
chmod +x gradlew || exit
./gradlew :publishToMavenLocal || exit
echo "Finished Publishing Email"