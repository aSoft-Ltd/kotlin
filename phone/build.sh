echo "Building phone"
chmod +x gradlew
echo "Running wrapper"
./gradlew wrapper
./gradle :build
echo "Finished building phone"﻿
