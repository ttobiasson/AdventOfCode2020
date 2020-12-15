#include<iostream>
#include<math.h>
#include<fstream>
#include<string>
#include<vector>
//https://adventofcode.com/2020/day/1
using namespace std;

int main(){
    ifstream input;
    int num, n, m, p;
    input.open("input.txt");
    vector<int> v1;

    while(input >> num){
        v1.push_back(num);
    }
    for(int i = 0; i < v1.size(); i++){
        for(int j = i; j < v1.size(); j++){
            for(int k = i; k < v1.size(); k++){
                if(v1[i] + v1[j] + v1[k] == 2020){
                    n = v1[i];
                    m = v1[j];
                    p = v1[k];
                    break;
                }
            }        
        }
    }
    cout << (n*m*p) << endl;
    return 0;
}