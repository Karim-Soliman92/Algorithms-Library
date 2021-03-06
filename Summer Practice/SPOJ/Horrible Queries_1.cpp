#include<stdio.h>
#include<vector>
#include<algorithm>

using namespace std;

struct segment_tree{
    static const int inf = 0x7fffffff;
    int N;
    vector<long long> vals;
    vector<long long> tree;
    vector<long long> lazy;
    vector<long long> flag;

    segment_tree(int n, vector<long long> v, int type){ //0-rmq, 1-rsq
        nearest(n);
        vals = vector<long long>(v);
        tree = vector<long long>(N<<1,0);
        lazy = vector<long long>(N<<1,0);
        flag = vector<long long>(N<<1,0);
        if (type){
            build_rsq(1,1,N,n);
        }
        else {
            build_rmq(1,1,N,n);
        }
    }

    segment_tree(int n, int value, int type){ //0-rmq, 1-rsq
        nearest(n);
        vals = vector<long long>(n,value);
        tree = vector<long long>(N<<1,0);
        lazy = vector<long long>(N<<1,0);
        flag = vector<long long>(N<<1,0);
        if (type){
            build_rsq(1,1,N,n);
        }
        else {
            build_rmq(1,1,N,n);
        }
    }

    void nearest(int n){
        N = 1;
        while(N<n){
            N<<=1;
        }
    }

    void build_rmq(int node, int s, int e, int n){
        if (s>e){
            return;
        }
        if (s==e){ //leaf node
            if (s>n){
                tree[node] = inf;
            }
            else {
                tree[node] = vals[s-1];
            }
            return;
        }
        int mid = s+((e-s)>>1), ln = node<<1, rn = (node<<1)+1;
        build_rmq(ln, s, mid, n);
        build_rmq(rn, mid+1, e, n);
        tree[node] = min(tree[ln],tree[rn]);
    }

    void build_rsq(int node, int s, int e, int n){
        if (s>e){
            return;
        }
        if (s==e){ //leaf node
            if (s>n){
                tree[node] = 0;
            }
            else {
                tree[node] = vals[s-1];
            }
            return;
        }
        int mid = s+((e-s)>>1), ln = node<<1, rn = (node<<1)+1;
        build_rsq(ln, s, mid, n);
        build_rsq(rn, mid+1, e, n);
        tree[node] = tree[ln]+tree[rn];
    }

    int get_index(int index){ // 1-based index
        return tree[N+index-1];
    }

    int rmq(int s, int e){
        return rmq_query(1,1,N,s,e);
    }

    int rmq_query(int node, int s, int e, int i, int j){
        if (s>e||s>j||e<i){
            return inf;
        }
        int mid = s+((e-s)>>1), ln = node<<1, rn = (node<<1)+1;
        prepare(node,s,e);
        if (s>=i&&e<=j){
            return tree[node];
        }
        return min(rmq_query(ln,s,mid,i,j),rmq_query(rn,mid+1,e,i,j));
    }

    long long rsq(int s, int e){
        return rsq_query(1,1,N,s,e);
    }

    long long rsq_query(int node, int s, int e, int i, int j){
        prepare(node,s,e);
        if (s>e||s>j||e<i){
            return 0;
        }
        int mid = s+((e-s)>>1), ln = node<<1, rn = (node<<1)+1;
        if (s>=i&&e<=j){
            return tree[node];
        }
        return rsq_query(ln,s,mid,i,j)+rsq_query(rn,mid+1,e,i,j);
    }

    void update(int s, int e, int value){
        return update_range(1,1,N,s,e,value);
    }

    void update_range(int node, int s, int e, int i, int j,int value){
        prepare(node,s,e);
        if (s>e||s>j||e<i){
            return;
        }
        int mid = s+((e-s)>>1), ln = node<<1, rn = (node<<1)+1;
        if (s>=i&&e<=j){
            flag[node] = 1;
            lazy[node] += value;
            prepare(node,s,e);
            return;
        }
        update_range(ln,s,mid,i,j,value);
        update_range(rn,mid+1,e,i,j,value);
        tree[node] = tree[ln]+tree[rn];
    }

    void prepare(int node, int s, int e){
        int ln = node<<1, rn = (node<<1)+1;
        if (flag[node]){
            tree[node] += lazy[node]*(e-s+1);
            if (s!=e){
                lazy[ln] += lazy[node];
                flag[ln] = 1;
                lazy[rn] += lazy[node];
                flag[rn] = 1;
            }
            flag[node] = 0;
            lazy[node] = 0;
        }
    }

};



int main(){
    int t, n, ca, a, b, c, d;
    scanf("%d", &t);
    while(t--){
        scanf("%d %d", &n, &ca);
        segment_tree st(n,0,1);
        while(ca--){
            scanf("%d", &a);
            if (a){
                scanf("%d %d",&b,&c);
                printf("%lld\n", st.rsq(b,c));
            }
            else{
                scanf("%d %d %d",&b,&c,&d);
                st.update(b,c,d);
            }
        }
    }
}
