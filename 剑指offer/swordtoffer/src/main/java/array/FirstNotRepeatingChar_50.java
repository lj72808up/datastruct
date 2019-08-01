package array;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目描述
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（
 * 需要区分大小写）.
 */
public class FirstNotRepeatingChar_50 {
    public int FirstNotRepeatingChar(String str) {
        int tableSize = 256;
        int[] hashTable = new int[tableSize];
        for (int i = 0; i < tableSize; i++) {
            hashTable[i] = 0;
        }
        for (int i = 0; i < str.length(); i++) {
            hashTable[(int)str.charAt(i)]++;
        }
        int first = -1;
        for (int i = 0; i < str.length(); i++){
            if (hashTable[(int)str.charAt(i)] ==1) {
                first = i;
                break;
            }
        }
        return first;
    }

    public static void main(String[] args) {
        int first = new FirstNotRepeatingChar_50().FirstNotRepeatingChar("google");
        System.out.println(first);
    }
}

/**
 * 思路:
 *  可以用哈希表(HashMap)存储每个字符出现的次数, 便利一次数组, 每次把哈希表对应项的次数加1
 *  再遍历一次数组, 从哈希表中查找每个数字出现的次数是否是1, 返回第一次是1的数字
 * */