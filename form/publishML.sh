echo "Publishing Form"
chmod +x gradlew
echo "Publishding form-core"
./gradlew :form-core:publishToMavenLocal || exit
echo "Publishing form-react"
./gradlew :form-react:publishToMavenLocal || exit
echo "Finished publishing form"