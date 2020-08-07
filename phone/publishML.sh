echo "Publishing phone"
chmod +x gradlew
./gradlew :publishToMavenLocal || exit
echo "Finished publishing phone"﻿
