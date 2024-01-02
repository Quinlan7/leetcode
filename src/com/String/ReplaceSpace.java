package com.String;/**
 * @author zhf
 * @date 2023/3/29 9:12
 * @version 1.0
 */

/**
 * @author zhf
 * 项目：leetcode
 * 描述：offer05
 * @date 2023/3/29 9:12
 **/
public class ReplaceSpace {
}
class SolutionOffer05 {
    public String replaceSpace(String s) {
        s = s.replace(" ","%20");
        return s;
    }
}