echo "Publishing phone"
chmod +x gradlew
echo "Running wrapper"
./gradlew wrapper
./gradle :publish
echo "Finished publishing phone"﻿
