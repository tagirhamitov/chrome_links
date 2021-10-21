cd ~/code/chrome-links/chrome-links-server
./mvnw clean package
cp ./target/chrome-links-server-0.0.1-SNAPSHOT.jar ../
cd ..
mv chrome-links-server-0.0.1-SNAPSHOT.jar chrome-links-server.jar
