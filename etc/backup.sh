#!/bin/bash
echo 'cd /home/ubuntu/nblog'
cd /home/ubuntu/nblog
echo 'backup nuxtapp'
rm -rf nuxtapp.bak
mkdir nuxtapp.bak
cp -r $(find nuxtapp/ -type d \( -path nuxtapp/node_modules -o -path nuxtapp/.nuxt \) -prune -o -print | sed 1d) nuxtapp.bak
