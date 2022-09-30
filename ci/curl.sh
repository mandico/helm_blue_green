

for i in {1..1000}; do
  curl -s http://localhost:1234/info | jq -r '[.hostname, .version] | @tsv'
done