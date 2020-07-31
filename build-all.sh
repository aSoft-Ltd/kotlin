cd ./components/components-react && ./gradlew :build || exit \

cd ../../daos/firestore-dao && ./gradlew :build || exit \

cd ../neo4j-dao && ./gradlew :build || exit \

cd ../rest-dao && ./gradlew :build || exit \

cd ../../email && ./gradlew :build || exit \

cd ../enterprise/enterprise-react && ./gradlew :build || exit \

cd ../../firebase/firebase-auth && ./gradlew :build || exit \

cd ../firebase-core && ./gradlew :build || exit \

cd ../firebase-firestore && cd ./gradlew :build || exit \
