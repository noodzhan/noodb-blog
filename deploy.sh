#!/bin/bash
# echo 'ssh 1.15.231.74'
# ssh ubuntu@1.15.231.74 <./etc/backup.sh
# echo 'scp'
# cd nuxt
# npm run build
# pwd
# scp -r ./.nuxt nuxt.config.js package.json package-lock.json ./static ubuntu@1.15.231.74:/home/ubuntu/nblog/nuxtapp

ssh ubuntu@1.15.231.74 <./etc/startNuxtapp.sh
