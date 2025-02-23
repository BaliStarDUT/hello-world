import json
import csv
import logging

# 输入vm.json的大json、输出到output.csv

logging.basicConfig(level=logging.INFO,format='%(asctime)s - %(name)s - %(levelname)s - %(message)s')

def loadJson():
    f = open("./vm.json", encoding='utf-8')
    obj = json.load(f)
    for item in obj:
        print(item)
    return obj


def write_to_csv(json):
    with open('output.csv', 'w', newline='') as csvfile:
        writer = csv.writer(csvfile)
         # 写入标题行
        writer.writerow(json[0].keys())
        for item in json:
            logging.info(item)
            writer.writerow(item.values())
    logging.info("写入完成")

if __name__ == '__main__':
    ob_list = loadJson()
    write_to_csv(ob_list)