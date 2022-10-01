#!/bin/sh

while(true); do
  curl http://localhost/info #| jq -r '[.hostname, .version] | @tsv'
  echo ""
  #sleep 1
done
