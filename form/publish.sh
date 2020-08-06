echo "Publishing Form"
chmod +x gradlew
echo "Publishding form-core"
./gradlew :form-core:publishd || exit
echo "Publishing form-react"
./gradlew :form-react:publish || exit
echo "Finished publishing form"