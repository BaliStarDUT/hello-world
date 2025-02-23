#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import json
import logging
from openpyxl import Workbook
import csv


logging.basicConfig(level=logging.INFO)

def open_file():
    with open('./output.txt', 'r') as f:
        for line in f:
            logging.info(line[0:100])
            json_s = line.replace("'",'"')
            logging.info(json_s[0:100])
            json_s2 = json.loads(json_s)

            logging.info(json_s2[0].keys())
            return json_s2

def save_to_excle(json_s2):
    # logging.info(data)
    wb = Workbook()
    ws = wb.active

    header = ["AccountID", "RemoteService", "Region", "Method", "ErrorCode", "StatusCode"]
    ws.append(header)

    for row in json_s2:
        AccountID = row['AccountID']
        RemoteService = row['RemoteService']
        Region = row['Region']
        Method = row['Method']
        ErrorCode = row['ErrorCode']
        StatusCode = row['StatusCode']

        ws.append([AccountID, RemoteService, Region, Method, ErrorCode, StatusCode])

    wb.save('./Result_2.xlsx')


def save_to_csv():
    with open('./response.json', 'r') as f:
        for line in f:
            logging.info(line[0:100])
            json_s = line.replace("'",'"')
            logging.info(json_s[0:100])
            json_s2 = json.loads(json_s)

            logging.info(json_s2[0].keys())

    with open('./Data_All.csv', 'w', newline='', encoding='utf-8') as csvfile:
        csvwriter = csv.writer(csvfile)

        header = ["xkzh", "qymc", "gmpZsh", "cym", "shren", "shrq"]
        csvwriter.writerow(header)

        for row in data['rows']:
            xkzh = row['xkzh']
            qymc = row['qymc']
            gmpZsh = row['gmpZsh']
            cym = row['cym']
            shren = row['shren']
            shrq = row['shrq']

            csvwriter.writerow([xkzh, qymc, gmpZsh, cym, shren, shrq])



json_s2 = open_file()
save_to_excle(json_s2)
