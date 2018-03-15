# -*- coding: UTF-8 -*-
import math


# 从栈中读取完整操作数
def readNumber(l):
    i = 0
    sum = 0
    while (len(l) > 0):
        num1 = int(l.pop())
        num2 = math.pow(10,i)
        sum = sum + num1 * num2
        i = i + 1
    return sum


# 计算表达式:一个字符串
# 搞一个运算符栈,只有当'栈顶的运算'符级别高于'当前遇到的运算符'级别,才能运算栈顶的操作
def caculate(expression):
    # 运算符优先级
    priority = {'(': 99, ')': -1, '+': 1,
                '-': 1, '*': 2, '/': 2,
                '\100':-2}
    numberStack = []
    operatorStack = ['\100']
    numberTmp = []
    i = 0
    while len(operatorStack)!=1 or i<=len(expression)-1:
        # 如果是数字,就加入暂存栈中
        _char = expression[i]
        if (_char.isdigit()):
            numberTmp.append(expression[i]);i=i+1
        # 如果是操作符,证明操作数读取完毕,先进行完整操作数的组装,在进行后续计算
        else:
            number = readNumber(numberTmp)
            numberStack.append(number)
            print "%s 已经入栈"% number
            topOperator = operatorStack[-1]  # 栈顶操作符
            # 连续的两个左右括号,直接删除栈顶的左括号
            if (topOperator == "(" and _char == ")"):
                operatorStack.pop()
            if (priority[topOperator] > priority[_char]):
                result = compute(numberStack, topOperator)  # 引用传递,numberStack栈顶两个元素已被pop
                numberStack.append(result)
            if (priority[topOperator] <= priority[_char]):
                operatorStack.append(expression[i]);i=i+1
    # 最终操作数栈只有一个元素,就是计算后的结果
    return numberStack[0]


def compute(numberStack, topOperator):
    i = numberStack.pop()
    j = numberStack.pop()
    if topOperator=="+":
        return j+i
    if topOperator=="-":
        return j-i
    if topOperator=="*":
        return j-i
    if topOperator=="/":
        return j-i


if __name__ == "__main__":
    print '\100'
    expression = "99+1*2"
    print caculate(expression)