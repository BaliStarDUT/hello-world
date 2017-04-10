#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
str1="acbac"
str2="acaccbacc"
len_str1=len(str1)
len_str2=len(str2)
print("len_str1:"+str(len_str1))
print("len_str2:"+str(len_str2))
list=[[0 for col in range(len_str2)] for row in range(len_str1)]
print(list)
cLen=1;
s1_Mindex=1;
for i in range(len_str1):
    print(str1[i])
    for j in range(len_str2):
        print(str2[j])
        if(i==0 or j==0):
            if(str1[i]==str2[j]):
                list[i][j]=1
            else:
                list[i][j]=0
        else:
            if(str1[i]==str2[j]):
                list[i][j]=list[i-1][j-1]+1
                if(cLen<list[i][j]):
                    cLen=list[i][j]
                    s1_Mindex=i;

print(list)
print(cLen)
print(s1_Mindex)
for i in range(cLen):
    print(str1[s1_Mindex+1-cLen+i])
