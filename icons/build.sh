echo "Building Icons"
echo "Building icons-react"
subprojects=("core" "ai" "bs" "di" "fa" "fc" "fi" "gi" "go" "gr" "io" "md" "ri" "ti" "wi")
chmod +x gradlew
for sp in ${subprojects[@]}; do
  echo "Building icons-react-${sp}"
  ./gradlew :icons-react-${sp}:build || exit
done
echo "Finished building icons-react"
echo "Finished Building Icons"
