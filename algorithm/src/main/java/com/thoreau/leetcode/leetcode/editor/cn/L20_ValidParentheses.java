 //给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 每个右括号都有一个对应的相同类型的左括号。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 仅由括号 '()[]{}' 组成 
// 
//
// Related Topics 栈 字符串 👍 4174 👎 0

  
  package com.thoreau.leetcode.leetcode.editor.cn;

 import java.util.HashMap;
 import java.util.Map;
 import java.util.Stack;

 public class L20_ValidParentheses{
      public static void main(String[] args) {
           Solution solution = new L20_ValidParentheses().new Solution();
          System.out.println(solution.isValid("(())"));
          System.out.println(solution.isValid("()[]{}"));
          System.out.println(solution.isValid("([{}])"));
          System.out.println(solution.isValid("([{})"));
          System.out.println(solution.isValid(")"));
          System.out.println(solution.isValid("("));
      }
      //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
          Map<Character, Character> map = new HashMap<Character,Character>(){{
              put(')','(');
              put('}','{');
              put(']','[');
          }};
    public boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            Character character = map.get(c);
            if (character == null) {
                //1.(
                stack.push(c);
            }else {
                //(
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (!character.equals(pop)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

  }