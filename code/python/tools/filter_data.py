#!/usr/bin/env/python3
# -*- coding: utf-8 -*-

import logging

logging.basicConfig(level=logging.INFO,
                    format="%(asctime)s - %(name)s - %(levelname)-9s - %(filename)-8s:%(lineno)s - %(message)s")

def read_file():
    name_dic = {}
    with open('filter_data.txt',"rt") as f:
        i=1
        for line in f:
    # with open('filter_data.txt',"r") as f:
    #     lines = f.readlines()
            logging.info("=======:%s",i)
            i=i+1
    #     for line in lines:

            name = line.rsplit(",")
            # logging.info((name,name[-1]))
            for name_K in name:
                logging.info(name_K)
                if name_K == "\n":
                    continue  
                if name_K.endswith("\n"):
                    name_K = name_K[0:-1]  
                if name_dic.get(name_K) == None:
                     name_dic[name_K] = 1
                else :
                    name_dic[name_K] = name_dic[name_K]+1
    logging.info(name_dic)    
    return name_dic

def save_file(name_dic):
    with open("output.txt","w") as s:
        for key in name_dic:
            logging.info(str.format("{}={}",key,name_dic[key]))
            s.write(str.format("{}={}",key,name_dic[key]))
            s.write('\n')


logging.info(__name__)
if __name__ == "__main__":
    name_dic = read_file()
    save_file(name_dic)