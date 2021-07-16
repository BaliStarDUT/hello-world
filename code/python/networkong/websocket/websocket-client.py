#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'

# websocket client

import websockets
import asyncio

async def hello():
    async with websockets.connect("ws://localhost:1030") as websocket:
        name = input("what's your name?")
        await websocket.send(name)
        print(f"> {name}")

        greeting = await websocket.recv()
        print(f"< {greeting}")

asyncio.get_event_loop().run_until_complete(hello())
