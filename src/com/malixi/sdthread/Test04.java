package com.malixi.sdthread;

public class Test04 {
	public static void main(String[] args) {
		char da = 'A';
		char xiao = 'a';
		for (int i = 0; i < 26; i++) {
			System.out.println("大写字母 "+da+" ,小写字母 "+xiao);
            //char 直接参与运算
			da++; //更新大写字母值
			xiao++; //更新小写字母值
		}
	}
}
 