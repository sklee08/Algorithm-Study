#include <string>
#include <vector>

using namespace std;

int isPrime(int n);

int solution(int n) {
    int answer = 0;
    int tmp = 2;
    int i = 0;
    for(i = 1; i <= n; i++){
        answer += isPrime(i);
    }
    
    
    return answer;
}

int isPrime(int n){
    int div = 2;
    if(n == 1) return 0;
    else if(n == 2) return 1;
    else{
        while(div*div <= n){
            if(n % div == 0){
                return 0;
            }else{
                div++;
            }
        }
        return 1;
    }
}