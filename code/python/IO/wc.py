#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
fileOpen = open("Animal.py","r")
print("file name:"+fileOpen.name)
print("file closed:",fileOpen.closed)
print("file mode:"+fileOpen.mode)
if(hasattr(fileOpen,'softspace')):
    print("file softspace:"+fileOpen.softspace)

fileOpen.close()
print("file closed:",fileOpen.closed)
with open("test.txt","r") as fileToRead:
    print("Opened a new file:",fileToRead.closed)
    print("file name:"+fileToRead.name)
    charCount = 0;
    for readOne in fileToRead.read():
        charCount+=1
        print(readOne)
print("CharCount:",charCount)
with open("test.txt","r") as fileToRead:
    print("file name:"+fileToRead.name)
    wordCount = 0;
    charCount = 0;
    for readOne in fileToRead.read():
        charCount+=1
        if(readOne==" "):
            wordCount+=1
        print(readOne)
print("CharCount:",charCount)
print("WordCount:",wordCount+1)
with open("test.txt","r") as fileToRead:
    print("file name:"+fileToRead.name)
    lineCount = 0;
    while(fileToRead.readline()):
        lineCount+=1
print("lineCount:",lineCount)

