publish(){
	cd $1 || exit
	chmod +x publish.sh || exit
	./publish.sh || exit
}

publish ./build-src
publish ../components
publish ../daos
publish ../deployment
publish ../email
publish ../firebase
publish ../form
publish ../frontend
publish ../geo
publish ../http
publish ../icons
publish ../io
publish ../klock
publish ../krypto
publish ../logging
publish ../money
publish ../paging
publish ../persist
publish ../phone
publish ../react
publish ../rest-api
publish ../result
publish ../storage
publish ../task
publish ../theme
publish ../tools
publish ../viewmodel
cd ..
