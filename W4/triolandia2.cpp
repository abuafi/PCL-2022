#include <iostream>
#include <map>
#include <string>
#include <vector>

using namespace std;

class city {
    public:
    string name;
    long long x, y;
    int highway_neighbours = 0, highway[2];

    void add_highway_neighbour(int i) {
        highway[highway_neighbours++] = i;
    }

    int next(int prev) {
        return highway[0] == prev ? highway[1] : highway[0];
    }
};

long long orientationTest(city a, city b, city c) {
    // return (a.x * (b.y - c.y)) + (b.x * (c.y - a.y)) + (c.x * (a.y - b.y));
    return (b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x);
}

class edge {
    public:
    int a, b;

    edge(int _a, int _b) {
        if (_a > _b) {
            a = _a; b = _b;
        } else {
            a = _b; b = _a;
        }
    }

    bool operator < (const edge & other) const {
        return a < other.a || (a == other.a && b < other.b);
    }
};

int main() {
    int m, n, x_min_i = 0;
    cin >> m >> n;
    city cities[m];
    for (int i = 0; i < m; i++) {
        city c;
        cin >> c.name >> c.x >> c.y;
        cities[i] = c;
    }
    map<edge, int> edges;
    for (int i = 0; i < n; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        edges[{a-1, b-1}] += 1;
        edges[{b-1, c-1}] += 1;
        edges[{a-1, c-1}] += 1;
    }
    for (auto & e : edges) {
        if (e.second == 1) {
            cities[e.first.a].add_highway_neighbour(e.first.b);
            cities[e.first.b].add_highway_neighbour(e.first.a);
            if (cities[e.first.a].x < cities[x_min_i].x) x_min_i = e.first.a;
            if (cities[e.first.b].x < cities[x_min_i].x) x_min_i = e.first.b;
        }
    }
    city cb = cities[x_min_i];
    int a = cb.highway[0], c = cb.highway[1];
    long long o = orientationTest(cities[a], cb, cities[c]);
    int x = o > 0 ? c : o < 0 ? a : cities[a].y < cities[c].y ? a : c;
    int prev = x_min_i;
    vector<int> v;
    v.push_back(x_min_i);
    size_t i = 0, first = 0;
    do {
        v.push_back(x);
        i++;
        // cout << (cities[v[first]].name > cities[x].name) << " " << cities[v[first]].name << " " << cities[x].name << endl;
        if (cities[v[first]].name > cities[x].name) first = i;
        // cout << cities[x].name << endl;
        int temp = x;
        x = cities[x].next(prev);
        prev = temp;
    } while(x != x_min_i);
    i = first;
    do {
        cout << cities[v[i]].name << endl;
        i = i == v.size() - 1? 0 : i+1; 
    } while(i != first);
}