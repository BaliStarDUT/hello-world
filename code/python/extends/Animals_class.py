#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
class Animal(object):
    def run(self):
        print('Animal is running...')
class Dog(Animal):
    def run(self):
        print('Dog is runnding...')
    def eat(self):
        print('Dog is eatting meat...')
class Cat(Animal):
    def run(self):
        print('Cat is running...')
class Tortoise(Animal):
    def run(self):
        print('Tortoise is running slowly...')
class Timer(object):
    def run(self):
        print('Timer Start...')
def run_twice(animal):
    animal.run()
    animal.run()
run_twice(Timer())
dog=Dog()
dog.run()
dog.eat()
cat=Cat()
cat.run()
a=list()
b=Animal()
c=Dog()
print(isinstance(a,list))
print(isinstance(b,Animal))
print(isinstance(c,Dog))
print(isinstance(c,Animal))
