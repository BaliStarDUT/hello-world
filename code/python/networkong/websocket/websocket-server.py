#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'

# websocket server

import websockets
import asyncio

async def hello(websocket,path):
    name = await websocket.recv()
    print(f"< {name}")
    greeting = f"Hello {name}!"
    await websocket.send(greeting)
    print(f"> {greeting}")

start_server = websockets.serve(hello,"localhost",1030)
asyncio.get_event_loop().run_until_complete(start_server)
asyncio.get_event_loop().run_forever()

