#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
# __author__='drawnkid@gmail.com'
import json
import logging
import csv

logging.basicConfig(level=logging.INFO,format='%(asctime)s - %(name)s - %(levelname)s - %(message)s')

# 
def loadJson():
    f = open("./vm.json", encoding='utf-8')

# 打开CSV文件写入器
    with open('output.csv', 'w', newline='') as csvfile:
        writer = csv.writer(csvfile)

        for line in f:
            logging.info(line[0:100])
            json_s = line.replace("'",'"')
            logging.info(json_s[0:100])
            json_s2 = json.loads(json_s)

            logging.info(json_s2[0].keys())
            logging.info(json_s2[1].keys())
            logging.info(json_s2[2].keys())

             # 写入标题行
            writer.writerow(json_s2[0].keys())
            for item in json_s2:
                # logging.info(item)
                writer.writerow(item.values())

loadJson()

