echo "Building jwt"
chmod +x gradlew
./gradlew wrapper || exit
echo "Building jwt-core"
./gradlew :jwt-core:build || exit
echo "Building jwt-hs"
./gradlew :jwt-hs:build || exit
echo "Building jwt-rs"
./gradlew :jwt-rs:build || exit
echo "Finished building jwt"