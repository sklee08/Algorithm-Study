#include <string>
#include <vector>
#include <iostream>

using namespace std;


int solution(int n) {
    int answer = 0;
    int a =0;
    int b = 1;
    for(int i =0; i< n; i++){
        int c = (a+b) % 1234567;
        a = b % 1234567;
        b = c % 1234567;
    }
    
    return a;
}

// 피보나치 수열 1234567 로 나눈 값을 리턴하는 문제
