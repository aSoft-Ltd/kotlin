build(){
  cd $1 || exit
  chmod +x build.sh || exit
	./build.sh || exit
}

build ./build-src
build ../components
build ../daos
build ../deployment
build ../email
build ../firebase
build ../form
build ../frontend
build ../geo
build ../http
build ../icons
build ../io
build ../klock
build ../krypto
build ../logging
build ../money
build ../paging
build ../persist
build ../phone
build ../react
build ../rest-controller
build ../result
build ../storage
build ../task
build ../theme
build ../tools
build ../viewmodel
cd ..