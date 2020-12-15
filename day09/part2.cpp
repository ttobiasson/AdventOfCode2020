#include<iostream>
#include<fstream>
#include<algorithm>
#include<vector>
//https://adventofcode.com/2020/day/9
using namespace std;
 
int main(){
    ifstream input;
    long long max, min, sum, numbers, ans;
    input.open("input.txt");
    vector<long long> v1(1000);

    for(long long &x : v1) input >> x;

    for(int i = 0; i < v1.size(); i++){
        sum = v1[i];
        min = max;
        max = 0;
        for(int j = i+1; j < v1.size(); j++){
            sum += v1[j];
            max = max < v1[j] ? v1[j] : max;
            min = min > v1[j] ? v1[j] : min;
            if(sum == 85848519) 
                ans = min + max;
        }
    } 
    cout << ans << endl; 
}