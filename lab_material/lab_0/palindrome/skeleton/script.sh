for i in {1,2,3,4,5,6,7,8}
 do 
   java Palindrome <../input/palindrome$i.in > test$i.tmp
   diff test$i.tmp ../output/palindrome$i.out
 done
