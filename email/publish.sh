echo "Publishing Email"
chmod +x gradlew || exit
./gradlew :publish || exit
echo "Finished Publishing Email"