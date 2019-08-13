package string;

/**
 * 题目描述
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指整个字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */
public class RegexMatch_19 {
    public boolean match(char[] str, char[] pattern) {
        if(str==null || pattern==null)
            return false;
        return matchRec(str,pattern,0,0);
    }

    /**
     * @param i: str的长度
     * @param j: 模式串的长度
     */
    public boolean matchRec(char[] str, char[] pattern, int i , int j) {
        if(i>=str.length && j>=pattern.length)
            return true;
        if(i<str.length && j>=pattern.length)  // 只匹配了部分
            return false;
        if(j+1<pattern.length  && pattern[j+1]=='*'){  // *号问题
            if(matchChar(str,pattern,i,j)) // 该字符和(char*)匹配上了
                return matchRec(str,pattern,i,j+2)
                || matchRec(str,pattern,i+1,j+2)
                || matchRec(str,pattern,i+1,j);
            else
                return matchRec(str,pattern,i,j+2); // 该字符没和(char*)匹配上
        }
        if(matchChar(str,pattern,i,j))
            return matchRec(str,pattern,i+1,j+1);
        else
            return false;

    }
    private boolean matchChar(char[] str, char[] pattern, int i , int j){
        if(i<str.length && j< pattern.length) {
            if (pattern[j] == '.')
                return true;
            else
                return str[i] == pattern[j];
        }else if(i>=str.length && j+1<pattern.length && pattern[j+1]=='*'){
            return true;
        }else{
            return false;
        }

    }

    public static void main(String[] args) {
        String str = "aabaa";
        String pattern = ".*";
        System.out.println(new RegexMatch_19().match(str.toCharArray(),pattern.toCharArray()));
    }
}

/**
 * 思路: (1)"."很好处理, 只要当做正常字符处理即可, 只是每次比较返回true
 *       (2)"*"不好处理, 需要分情况讨论
 *          a. "*"表示0次匹配: 比较str[i]和pattern[j+2]
 *          b. "*"表示1次匹配: 比较str[i+1]和patter[j+2]
 *          c. "*"表示多次匹配: 比较str[i+1]和pattern[j]
 * */