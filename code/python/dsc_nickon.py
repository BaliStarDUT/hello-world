#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
from PIL import Image
im = Image.open('DSC.jpg')
im.load()
print(im.format, im.size, im.mode)
im.show()
im.thumbnail((600, 400))
im.save('dsc_l.jpg', 'JPEG')