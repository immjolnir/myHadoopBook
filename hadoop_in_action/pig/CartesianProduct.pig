%default Ainput=/home/zhishan/workspace/git/myHadoopBook/hadoop_in_action/src/main/resources/data1
%default Binput=/home/zhishan/workspace/git/myHadoopBook/hadoop_in_action/src/main/resources/data2
A = load '$Ainput'  using PigStorage(',') AS (a1, a2, a3);
B = load '$Binput' using PigStorage(' ') AS (b1, b2);
C = CROSS A, B;

/*
$ Dump A
(1,2,3)
(4,5,6)
$ Dump B
(1,2)
(3,4)
(5,6)

$ DUMP C
(1,2,3,1,2)
(1,2,3,3,4)
(1,2,3,5,6)
(4,5,6,1,2)
(4,5,6,3,4)
(4,5,6,5,6)
*/
