echo "Publishing deployment"
chmod +x gradlew
./gradlew wrapper
./gradlew :publish || exit
echo "Finished publishing deployment"