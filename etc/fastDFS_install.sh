#!/bin/bash

function fastDFS_install() {
    # yum install gcc libevent libevent-devel -y
    sudo apt install gcc
    sudo apt install libevent-devel
    wget https://github.com/happyfish100/libfastcommon/archive/V1.0.66.tar.gz
    tar -zxvf V1.0.7.tar.gz
    cd libfastcommon-1.0.7/
    ./make.sh
    ./make.sh install

    cd ..

    wget https://github.com/happyfish100/fastdfs/archive/V5.11.tar.gz

    tar -zxvf V5.11.tar.gz

    cd fastdfs*

    ./make.sh

    ./make.sh install

}
