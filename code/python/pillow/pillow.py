#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
from PIL import Image
im = Image.open('Fiddlesticks_Splash_Tile_8.jpg')
print(im.format, im.size, im.mode)

region = (0,35,380,235)
cropImg = im.crop(region)
cropImg.save('crop.jpg','JPEG')

# im.thumbnail((200, 100))
# im.save('thumb.jpg', 'JPEG')
