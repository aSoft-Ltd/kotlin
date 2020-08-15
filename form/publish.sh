echo "Publishing Form"
chmod +x gradlew
echo "Publishing form-http"
./gradlew :form-http:publish || exit
echo "Publishing form-html"
./gradlew :form-html:publish || exit
echo "Publishing form-react"
./gradlew :form-react:publish || exit
echo "Finished publishing form"