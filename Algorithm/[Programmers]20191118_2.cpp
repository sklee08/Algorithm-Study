#include <string>
#include <vector>

using namespace std;

string solution(vector<string> seoul) {
    string answer = "";
    int len = seoul.size();
    int i;
    int where = 0;
    for(i = 0; i<len; i++){
        if(seoul.at(i).compare("Kim") == 0){
            where = i;
            break;
        }
    }
    answer = "김서방은 "+to_string(where)+"에 있다";
    return answer;
}
