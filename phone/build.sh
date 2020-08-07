echo "Building phone"
chmod +x gradlew
echo "Running wrapper"
./gradlew wrapper
./gradlew :build || exit
echo "Finished building phone"﻿
