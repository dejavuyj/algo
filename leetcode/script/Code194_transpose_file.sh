# Read from the file file.txt and print its transposed content to stdout.
columns=$(cat file.txt | head -n 1 | wc -w)
for i in $(seq 1 $columns)
do
awk '{print $'''$i'''}' file.txt | xargs
done