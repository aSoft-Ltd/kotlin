build(){
	cd $1 && echo "building $(pwd)"
	chmod +x gradlew && ./gradlew :build || exit
}

build ./components/components-react
build ../../daos/firestore-dao
build ../neo4j-dao
build ../rest-dao
build ../../email
build ../enterprise/enterprise-react
build ../../firebase/firebase-auth
build ../firebase-core
build ../firebase-firestore
build ../firebase-storage
build ../../form/form-react
build ../../geo
build ../http
build ../icons/icons-react
build ../../io
build ../klock
build ../krypto
build ../logging
build ../money
build ../paging/paging-core
build ../paging-enterprise-react
build ../../phone
build ../rest-controller/rest-controller-core
build ../rest-controller-ktor
build ../../result
build ../storage
build ../task
build ../theme/theme-core
build ../theme-react
build ../../tools/tools-core
build ../tools-react
build ../../viewmodel/viewmodel-core
build ../viewmodel-react






