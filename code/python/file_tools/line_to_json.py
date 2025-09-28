import csv

# 原始文本
text = """region: xxxxxxxxxx
instance_id: xxxxxxxx
instance_name: xxxxxx
super_user_name: xxxxxx
super_user_password: xxxxxxx
write_read_addresses:
domain: xxxxxx
port: 3306
read_only_addresses:
domain: xxxxxx
port: 3306"""

# 分割文本为多个对象
objects = text.strip().split('\n\n')

# 定义CSV文件的列名
fieldnames = ['region', 'instance_id', 'instance_name', 'super_user_name', 'super_user_password',
              'write_read_addresses_domain', 'write_read_addresses_port',
              'read_only_addresses_domain', 'read_only_addresses_port']

# 打开CSV文件以写入数据
with open('output.csv', 'w', newline='', encoding='utf-8') as csvfile:
    writer = csv.DictWriter(csvfile, fieldnames=fieldnames)

    # 写入CSV文件的表头
    writer.writeheader()

    # 遍历每个对象
    for obj in objects:
        lines = obj.strip().split('\n')
        data = {}
        current_section = None
        for line in lines:
            if ':' in line:
                key, value = line.split(': ', 1)
                if key in ['write_read_addresses', 'read_only_addresses']:
                    current_section = key
                elif current_section:
                    data[f'{current_section}_{key}'] = value
                else:
                    data[key] = value

        # 写入CSV文件的一行数据
        writer.writerow(data)

print("数据已成功转换为CSV格式并保存到output.csv文件中。")
