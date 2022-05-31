#!/bin/bash
cd /home/ubuntu/nblog/nuxtapp
npm install
echo "node pid:" $(lsof -i:3000 | awk '{print $2}' | sed 1d)
kill -9 $(lsof -i:3000 | awk '{print $2}' | sed 1d)
echo 'start nuxt ...'
nohup npm run start &
echo 'sleep 30s ...'
sleep 30s
echo "node pid:" $(lsof -i:3000 | awk '{print $2}' | sed 1d)
echo "curl http://localhost:3000"
curl http://localhost:3000 | tail -n 5
