# numToChineseText
chinese_letter = ["零","一","二","三","四","五","六","七","八","九"]
chinese_unit = ["","十","百","千","万"]

def numToChineseText(num):
    if num == 0:
        return "零"
    text = ""
    len_num = len(str(num))
    if len_num > 8:
        return "超出范围"    
    ten_thousand = num//10000
    remainder = num%10000

    def convert_four_digits(anum):
        result = ""
        # last_zero = True
        for i in range(3,-1,-1):
            digit = (anum//(10**i)) % 10
            if digit !=0:
                result += chinese_letter[digit] + chinese_unit[i]
                # last_zero = False
            else:
                # if not last_zero:
                result += "零"
                    # last_zero = True
        result = result.rstrip("零")
        result = result.lstrip("零")
        return result

    ten_thousand_part = convert_four_digits(ten_thousand)
    remainder_part = convert_four_digits(remainder)
    if ten_thousand_part:
        text += ten_thousand_part + "万"

    if ten_thousand_part and remainder<1000 and remainder != 0:
        text += "零"
    if remainder_part:
        text += remainder_part
  # 特殊处理"一十" -> "十"
    if text.startswith('一十'):
        text = text[1:]
    return text 



def toChineseNum(num):
    # 验证输入是否为有效数字
    if not isinstance(num, int) or num < 0 or num >= 100000:
        raise ValueError("请输入0到99999之间的整数")
    
    # 基本数字对应的中文
    digits = ['零', '一', '二', '三', '四', '五', '六', '七', '八', '九']
    # 位数对应的中文
    units = ['', '十', '百', '千']
    
    # 处理0的情况
    if num == 0:
        return '零'
    
    # 分解万位和个位部分
    ten_thousand = num // 10000  # 万位部分
    remainder = num % 10000      # 个位部分(0-9999)
    
    # 处理一个数的中文表示(最多四位)
    def convert_four_digits(n):
        if n == 0:
            return ''
        
        result = ''
        has_non_zero = False  # 标记是否有非零数字
        
        for i in range(3, -1, -1):  # 从千位到个位
            divisor = 10 **i  # 1000, 100, 10, 1
            digit = (n // divisor) % 10
            
            if digit != 0:
                result += digits[digit] + units[i]
                has_non_zero = True
            else:
                # 只有前面有非零数字且结果最后不是零才加零
                if has_non_zero and not result.endswith('零'):
                    result += '零'
        
        # 处理十位数的特殊情况(一十 -> 十)
        if result.startswith('一十'):
            result = result[1:]
            
        return result
    
    # 转换万位和个位部分
    part_ten_thousand = convert_four_digits(ten_thousand)
    part_remainder = convert_four_digits(remainder)
    
    # 组合结果
    if part_ten_thousand:
        part_ten_thousand += '万'
        # 处理万位有值但个位部分为零的情况
        if not part_remainder:
            return part_ten_thousand
        # 处理万位和个位之间是否需要加零
        if remainder < 1000 and ten_thousand > 0:
            return part_ten_thousand + '零' + part_remainder
        return part_ten_thousand + part_remainder
    else:
        return part_remainder


# 测试案例
if __name__ == "__main__":
    test_cases = [
        0, 1, 10, 12, 103, 1234, 
        10000, 10001, 10010, 10100, 10103,
        12345, 99999, 50000, 50005, 50500
    ]
    
    for num in test_cases:
        print(f"{num} -> {numToChineseText(num)}")
    # print(f"{convert_four_digits(1234)}")

    # numToChineseText(1234567890)
    