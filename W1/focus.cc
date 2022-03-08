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

class bst {
    bool isred = true;
    bool isleftchild = true;
    bool isleaf = false;
    int value = 0;
    bst *left, *right;
    bst *parent;

    bst(bst *parent, bool isleftchild) {
        isleaf = true;
        this->isleftchild = isleftchild;
        this->parent = parent;
    }

    void set(bst *b) {
        bst *oldleft = left;
        bst *oldright = right;
        isleaf = b->isleaf;
        if (!b->isleaf) {
            value = b->value;
            left = b->left;
            right = b->right;
            left->parent = this;
            right->parent = this;
        }
        delete oldleft;
        delete oldright;
    }

    bst *findmin() {
        if (left->isleaf) return this;
        return left->findmin();
    }

    void leftRotate() {
        bst *y = right;
        if (!y->left->isleaf) {
            right = y->left;
            y->left->parent = this;
            y->left->isleftchild = false;
        }
        y->parent = parent;
        y->isleftchild = isleftchild;
        if (parent != NULL) {
            if (isleftchild) parent->left = y;
            else parent->right = y;
        }
        parent = y;
        isleftchild = true;
        y->left = this;
    }

    void rightRotate() {
        bst *x = left;
        if (!x->right->isleaf) {
            left = x->right;
            x->right->parent = this;
            x->right->isleftchild = true;
        }
        x->parent = parent;
        x->isleftchild = isleftchild;
        if (parent != NULL) {
            if (isleftchild) parent->left = x;
            else parent->right = x;
        }
        parent = x;
        isleftchild = false;
        x->right = this;
    }

    void fixDeletion() {
        bst *k = this, *w;
        while (k->parent != NULL && !k->isred) {
            if (k->isleftchild) {
                w = k->parent->right;
            } else {
                w = k->parent->left;
            }
            if (w->isred) {
                w->isred = false;
                k->parent->isred = true;
                if (k->isleftchild) {
                    k->parent->leftRotate();
                    w = k->parent->right;
                } else {
                    k->parent->rightRotate();
                    w = k->parent->left;
                } 
            }
            if (!w->left->isred && !w->right->isred) {
                w->isred = true;
                k = k->parent;
            } else {
                if (!w->right->isred) {
                    w->left->isred = false;
                    w->isred = true;
                    if (k->isleftchild) {
                        w->rightRotate();
                        w = k->parent->right;
                    } else {
                        w->leftRotate();
                        w = k->parent->left;
                    }
                    
                }
                w->isred = k->parent->isred;
                k->parent->isred = false;
                w->right->isred = false;
                if (k->isleftchild) {
                   k->parent->leftRotate();
                } else {
                   k->parent->rightRotate();
                }
                k->parent = NULL;
            }
        }
        k->isred = false;
    }

    void fixInsertion() {
        bst *u, *k = this;
        if (k->parent == NULL) {
            k->isred = false;
            return;
        }
        if (k->parent->parent == NULL) {
                return;
            }
        while (k->parent->isred) {
            if (!k->parent->isleftchild) {
                u = k->parent->parent->left;
            } else {
                u = k->parent->parent->right;
            }
            if (u->isred) {
                u->isred = false;
                k->parent->isred = false;
                k->parent->parent->isred = true;
                k = k->parent->parent;
            } else {
                if (!k->parent->isleftchild) {
                    if (k->isleftchild) {
                        k = k->parent;
                        k->rightRotate();
                    }
                    k->parent->isred = false;
                    k->parent->parent->isred = true;
                    k->parent->parent->leftRotate();
                } else {
                    if (!k->isleftchild) {
                        k = k->parent;
                        k->leftRotate();
                    }
                    k->parent->isred = false;
                    k->parent->parent->isred = true;
                    k->parent->parent->rightRotate();
                }
            }
            if (k->parent == NULL) break;
        }
        while (k->parent != NULL) {
            k = k->parent;
        }
        k->isred = false;
    }

    public: 

    int findmax() {
        if (right->isleaf) return value;
        return right->findmax();
    }

    void add(int value) {
        if(isleaf) {
            isleaf = false;
            this->value = value;
            left = new bst(this, true);
            right = new bst(this, false);
            fixInsertion();
            return;
        }
        if(value > this->value) {
            right->add(value);
        } else {
            left->add(value);
        }
    }

    void remove(int value) {
        if (isleaf) return;
        if (this->value == value) {
            bool isoriginalred = isred;
            if (left->isleaf) {
                set(right);
            } else if (right->isleaf) {
                set(left);
            } else {
                bst* newroot = right->findmin();
                isoriginalred = newroot->isred;
                this->value = newroot->value;
                newroot->remove(newroot->value);
                if (!isoriginalred) fixDeletion();
            }
        } else {
            if(value > this->value) {
                right->remove(value);
            } else {
                left->remove(value);
            }
        }
    }

    bst() {
        isleaf = true;
        parent = NULL;
    }

    void debug() {
        if (isleaf) return;
        cout << "<";
        left->debug();
        if (isred) cout << "[ RED ";
        else cout << "[ BLA ";
        cout << value << " ]";
        right->debug();
        cout << ">";
    }
};

int main() {
    int d, n;
    cin >> d >> n;
    if (d > n) {
        cout << "impossible";
        return -1;
    }
    int tasks[n];
    for (int i = 0; i < n; i++) {
        cin >> tasks[i];
    }
    int best_idx = 0;
    bst current;
    int max = tasks[0];
    for (int i = 0; i < d; i++) {
        current.add(tasks[i]);
        if (tasks[i] > max) max = tasks[i];
    }
    int best = max;

    for (int i = 0; i < n - d; i++) {
        current.remove(tasks[i]);
        current.add(tasks[i + d]);
        current.debug();
        cout << endl;
        if (tasks[i + d] > max || tasks[i] == max) {
            max = current.findmax();
            if (max < best) {
                best = max;
                best_idx = i + 1;
            }
        }
    }
    cout << best_idx + 1;
    return 1;
}