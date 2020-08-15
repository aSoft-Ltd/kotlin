echo "Publishing Form"
chmod +x gradlew
echo "Publishding form-http"
./gradlew :form-http:publishToMavenLocal || exit
echo "Publishing form-html"
./gradlew :form-html:publishToMavenLocal || exit
echo "Publishing form-react"
./gradlew :form-react:publishToMavenLocal || exit
echo "Finished publishing form"
