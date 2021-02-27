#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'

# websocket server that sends messages at random intervals

import websockets
import datetime
import random
import asyncio

async def time(websocket,path):
    while True:
        now = datetime.datetime.utcnow().isoformat()+'Z'
        print(f"< {now}")
        await websocket.send(now)
        await asyncio.sleep(random.random()*3)

start_server = websockets.serve(time,"localhost",1031)
asyncio.get_event_loop().run_until_complete(start_server)
asyncio.get_event_loop().run_forever()

