for i in {1..8}
do
echo -e "[test case $i]\n=========="
java FileManager <../input/file$i.in> file$i.temp
diff ../output/file$i.out  file$i.temp
echo -e "\n"
done
