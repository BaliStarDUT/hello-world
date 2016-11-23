#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
class Animal(object):
    def run(self):
        print('Animal is running.....')

class Dog(Animal):
    def run(self):
        print('Dog is running...')
    def eat(self):
        print('Eating meat....')

class Cat(Animal):
    def run(self):
        print('Cat is running...')

class Timer(object):
    def run(self):
        print('Timer Start....')

dog = Dog()
dog.run()
dog.eat()
cat = Cat()
cat.run()
timer = Timer()
timer.run()
#cat2 = Cat(timer)
#cat2.run()
