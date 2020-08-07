echo "Publishing Icons"
echo "Publishing icons-react"
subprojects=("core" "ai" "bs" "di" "fa" "fc" "fi" "gi" "go" "gr" "io" "md" "ri" "ti" "wi")
chmod +x gradlew
for sp in ${subprojects[@]}; do
  echo "Publishing icons-react-${sp}"
  ./gradlew :icons-react-${sp}:publishToMavenLocal || exit
done
echo "Finished publishing icons-react"
echo "Finished Publishing Icons"
