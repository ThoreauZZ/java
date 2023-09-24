#
* codeFileName:`L${question.frontendQuestionId}_$!velocityTool.camelCaseName(${question.titleSlug})`
* codeTemplate:
```
${question.content}
package com.thoreau.leetcode.leetcode.editor.cn;
public class L${question.frontendQuestionId}_$!velocityTool.camelCaseName(${question.titleSlug}){
  public static void main(String[] args) {
       Solution solution = new L${question.frontendQuestionId}_$!velocityTool.camelCaseName(${question.titleSlug})().new Solution();
  }
  ${question.code}
}
```