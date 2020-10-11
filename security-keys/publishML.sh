echo "publishing result"
chmod +x gradlew
./gradlew :publishToMavenLocal || exit
echo "Finished publishing result"