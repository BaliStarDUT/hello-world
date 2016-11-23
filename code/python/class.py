#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
class Student(object):
    def __init__(self,name,score):
        self.name = name
        self.score = score
bart = Student('yang',44)
print(bart.name)
print(bart.score)
