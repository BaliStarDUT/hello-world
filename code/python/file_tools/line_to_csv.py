import csv
import logging

logging.basicConfig(level=logging.INFO,
                    format="%(asctime)s - %(name)s - %(levelname)-9s - %(filename)-8s:%(lineno)s - %(message)s")

# 原始文本
text = """region: xxxxxxx
instance_id: xxxxxxxx
instance_name: xxxxxxxx
super_user_name: xxxxxxxx
super_user_password: xxxxxxxxx
write_read_addresses:
domain: xxxxxxxxxx
port: 3306
read_only_addresses:
domain: xxxxxxxxxxxx
port: 3306"""

# 定义CSV文件的列名
fieldnames = ['region', 'instance_id', 'instance_name', 'super_user_name', 'super_user_password',
              'write_read_addresses_domain', 'write_read_addresses_port',
              'read_only_addresses_domain', 'read_only_addresses_port']


def convert_to_csv(text,fieldnames,objects):
    logging.info("开始转换文本为CSV格式...")
    # 打开CSV文件以写入数据
    with open('output3.csv', 'w', newline='', encoding='utf-8') as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)

        # 写入CSV文件的表头
        writer.writeheader()

        # 遍历每个对象
        # for obj in objects:
        #     lines = obj.strip().split('\n')
        for line in objects:
            
            if ':' in line:
                logging.info("line:{}".format(line))
                key, value = line.split(':')
                key = key.strip()
                value = value.strip()
                logging.info(str.format("key:{} value:{}",key,value))
                if key in ['region']:
                    data = {}
                    current_section = None
                    data[key] = value
                elif key in ['write_read_addresses', 'read_only_addresses']:
                    current_section = key
                elif current_section:
                    data[f'{current_section}_{key}'] = value
                else:
                    data[key] = value

                if len(data) >=9:
                    logging.info("data:{}".format(data))
                    # 写入CSV文件的一行数据
                    writer.writerow(data)

    print("数据已成功转换为CSV格式并保存到output3.csv文件中。")

def read_file(file_path):
    logging.info("开始读取文件...")
    try:
        with open(file_path, 'r', encoding='utf-8') as file:
            content = file.read()
            logging.info("文件读取成功。")
            return content
    except FileNotFoundError:
        logging.error("文件不存在。")
        return None
    except Exception as e:
        logging.error(f"读取文件时发生错误：{str(e)}")


# 从文件中读取文本
file_path = 'line_text.txt'
text = read_file(file_path)

logging.info(text)
# 分割文本为多个对象
objects = text.strip().split('\n')
logging.info(objects)
convert_to_csv(text,fieldnames,objects)

