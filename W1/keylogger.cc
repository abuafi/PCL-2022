#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <string.h>
#include <map>
#include <tuple>
#include <string>
#include <list>

// Team Lian

using namespace std;

list<string> letters = {
    "clank", "bong", "click", "tap", "poing", "clonk", "clack", "ping", "tip", 
    "cloing", "tic", "cling", "bing", "pong", "clang", "pang", "clong", "tac", 
    "boing", "boink", "cloink", "rattle", "clock", "toc", "clink", "tuc"
};

string out;

map<string, int> special = {
    {"whack", 32}
};

typedef void (*FnPtr)(void);

bool caps = false;
bool shift = false;

void capslock() {
    caps = !caps;
}

void shiftdown() {
    if (!shift) {
        shift = true;
        caps = !caps;
    }
}

void shiftup() {
    if (shift) {
        shift = false;
        caps = !caps;
    }
}

void pop() {
    out.pop_back();
}

map<string, FnPtr> capsmap = {
    {"bump", capslock},
    {"dink", shiftdown},
    {"thumb", shiftup},
    {"pop", pop}
};

int indexOf(list<string> list, string s) {
    auto itr = list.begin();
    while (itr != list.end()) {
        if (*itr == s) {
            return distance(list.begin(), itr);
        }
        ++itr;
    }
    return -1;
}

int main() {
    int l;
    string in;
    cin >> l;
    while (l > 0) {
        cin >> in;
        int n = indexOf(letters, in);
        if (n >= 0 ) {
            n += 97;
            if (caps) {n -= 32;}
            out.push_back(char(n));
        } else {
            n = special[in];
            if (n == 0) {
                capsmap[in]();
            } else {
                out.push_back(char(n));
            }
        }
        l--;
    }
    cout << out;
}