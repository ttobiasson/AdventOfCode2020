#include<iostream>
#include<fstream>
#include<algorithm>
#include<vector>
#include<sstream>
#include <boost/algorithm/string/classification.hpp> 
#include <boost/algorithm/string/split.hpp> 

using namespace std;

//https://www.dcode.fr/chinese-remainder if you want it solved in resonable time

int main(){
    vector<long long>v2;
    vector<string>v3;
    long long minutes = 0, x1 = 0, timestamp = 1000000000000-760;
    ifstream in;
    in.open("input.txt");
    string x;
    in >> x;
    in >> x;
    std::vector<std::string> words;
    std::string s;
    boost::split(v3, x, boost::is_any_of(","), boost::token_compress_on);

    for(string a : v3){
        if( (a != "x") && (a != ",") ){
            stringstream ss(a);
            ss >> x1;
            v2.push_back(minutes);
            v2.push_back(x1);
            cout << minutes << "|"<< x1 << endl;
            minutes++;
        }
        else
            minutes++;
    }

    while(timestamp+=911>0){
        bool allgood = true;
        for(int j = 0; j < v2.size(); j+=2){
            long long timeto = v2.at(j);
            long long id = v2.at(j+1);
            
            if((timestamp+timeto)%id!=0){
                allgood = false;
            }
        }
        if(allgood){
            cout << timestamp << endl;
            exit(0);
        }
    }
}