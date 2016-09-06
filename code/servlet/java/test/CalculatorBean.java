package yang.java.test;


import java.math.BigDecimal;

/**
 *  CalculatorBean用于接收输入参数和计算
 * @author yangzhen
 *
 */
public class CalculatorBean {

    //用户输入的第一个数
    private double firstNum;
    //用户输入的第二个数
    private double secondNum;
    //用户选择的操作运算符
    private char operator = '+';
    //运算结果
    private double result;

    public double getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(double firstNum) {
        this.firstNum = firstNum;
    }

    public double getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(double secondNum) {
        this.secondNum = secondNum;
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    /**
     * 用于计算
     */
    public void calculate() {

        switch (this.operator) {
            case '+': {
                this.result = this.firstNum + this.secondNum;
                break;
            }
            case '-': {
                this.result = this.firstNum - this.secondNum;
                break;
            }
            case '*': {
                this.result = this.firstNum * this.secondNum;
                break;
            }
            case '/': {
                if (this.secondNum == 0) {
                    throw new RuntimeException("被除数不能为0!!!");
                }
                this.result = this.firstNum / this.secondNum;
                // 四舍五入
                this.result = new BigDecimal(this.result).setScale(2,
                        BigDecimal.ROUND_HALF_UP).doubleValue();
                break;
            }
            default:
                throw new RuntimeException("对不起，传入的运算符非法！！");
        }
    }
}