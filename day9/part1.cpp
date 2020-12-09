#include<iostream>
#include<fstream>
#include<algorithm>
#include<vector>

using namespace std;

int main(){
    ifstream input;
    long long sum, numbers;
    input.open("input.txt");
    vector<long long> v1(1000);
    vector<long long> v2;

    for(long long &x : v1) input >> x;

    for(int i = 0; i < v1.size(); i++){
        for(int k = i; k < v1.size(); k++){
            if(v1[i] != v1[k]){
                sum = v1[i]+v1[k];
                v2.push_back(sum);
            }
        }
    }    
    for(int i = 25; i < v1.size(); i++){
        if(find(v2.begin(), v2.end(),v1[i]) == v2.end()){
            cout << v1[i] << endl;
            return 0;
        }
    }
}