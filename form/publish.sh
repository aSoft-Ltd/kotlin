echo "Publishing Form"
chmod +x gradlew
echo "Publishding form-core"
./gradlew :form-core:publish || exit
echo "Publishing form-react"
./gradlew :form-react:publish || exit
echo "Finished publishing form"