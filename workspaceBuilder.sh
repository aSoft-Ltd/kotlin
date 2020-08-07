generate(){
  echo "" >> publishs.yml
  echo "      - name: publishing $1" >> publishs.yml
  echo "        run: |" >> publishs.yml
  echo "          chmod +x publish.sh" >> publishs.yml
  echo "          ./publish.sh" >> publishs.yml
  echo "        env:" >> publishs.yml
  echo "          SPACE_USERNAME: \${{ secrets.SPACE_USERNAME }}" >> publishs.yml
  echo "          SPACE_PASSWORD: \${{ secrets.SPACE_PASSWORD }}" >> publishs.yml
  echo "        working-directory: ./$1" >> publishs.yml
  echo "" >> publishs.yml
}

generate build-src
generate components
generate daos
generate deployment
generate email
generate firebase
generate form
generate frontend
generate geo
generate http
generate icons
generate io
generate klock
generate krypto
generate logging
generate money
generate paging
generate persist
generate phone
generate react
generate rest-controller
generate result
generate storage
generate task
generate theme
generate tools
generate viewmodel