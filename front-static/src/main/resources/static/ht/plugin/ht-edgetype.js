!function(G){"use strict";var t="ht",C=G[t],b=Math,H=b.max,n=b.min,s=b.abs,a=b.atan2,v=b.cos,J=b.sin,D=b.ceil,w=C.Default,L=w.getInternal(),W=C.List,A=L.Mat,M=L.getNodeRect,T=L.intersectionLineRect,c=w.getDistance,j=w.setEdgeType,e="left",i="right",$="top",B="bottom",P="edge.type",U="edge.gap",_="edge.center",R="edge.extend",E=function(G,$){return M(G,$).width},K=function(G,X){return M(G,X).height},Z=function(k,W){var X=L.getPosition(W.s("edge.source.position"),M(k,W._40I));return X.x+=W.s("edge.source.offset.x"),X.y+=W.s("edge.source.offset.y"),X},N=function(U,R){var Z=L.getPosition(R.s("edge.target.position"),M(U,R._41I));return Z.x+=R.s("edge.target.offset.x"),Z.y+=R.s("edge.target.offset.y"),Z},r=function(j,Y){var a=j.s(P),Q=j.getEdgeGroup();if(Q){var Z=0;if(Q.eachSiblingEdge(function(B){Y.isVisible(B)&&B.s(P)==a&&Z++}),Z>1)return j.s(U)*(Z-1)/2}return 0},Q=function(j,c){var m=j.s(P),r=j.isLooped();if(!j.getEdgeGroup())return r?j.s(U):0;var R,W=0,v=0,y=0;return j.getEdgeGroup().getSiblings().each(function(h){h.each(function(o){if(o._40I===j._40I&&o.s(P)==m&&c.isVisible(o)){var q=o.s(U);R?(v+=y/2+q/2,y=q):(R=o,y=q),o===j&&(W=v)}})}),r?v-W+y:W-v/2},g=function(h,C){var N=h.size(),R=C.s("edge.corner.radius");if(0===R)return{points:h,segments:new W([1,N])};if(0>R)return{points:h};var Q,x=new W,P=new W,I=h.get(0);x.add(I),P.add(1);for(var g=1;N>g;g++)if(N>g+1){var d=h.get(g),O=h.get(g+1),s=I.x,M=I.y,L=d.x,p=d.y,K=p-M,E=L-s,B=a(K,E);Q=n(.99*c(I,d),R),x.add({x:L-Q*v(B),y:p-Q*J(B)}),x.add(d),s=d.x,M=d.y,L=O.x,p=O.y,K=p-M,E=L-s,B=a(K,E),Q=n(.99*c(d,O),R),I={x:s+Q*v(B),y:M+Q*J(B)},x.add(I),P.addAll([2,3])}else x.add(h.get(g)),P.add(2);return{points:x,segments:P}};L.addMethod(C.Style,{"edge.ripple.elevation":-20,"edge.ripple.size":1,"edge.ripple.both":!1,"edge.ripple.straight":!1,"edge.ripple.length":-1,"edge.corner.radius":-1,"edge.ortho":.5,"edge.flex":20,"edge.extend":20},!0),j("boundary",function(r,n,t,H){H||(n=-n);var P,J=Z(t,r),X=N(t,r),e=M(t,r._40I),_=M(t,r._41I),y=new A(a(X.y-J.y,X.x-J.x)),K=c(J,X),o=J.x,R=J.y;return P=y.tf(0,n),J={x:P.x+o,y:P.y+R},P=y.tf(K,n),X={x:P.x+o,y:P.y+R},P=T(J,X,e),P&&(J={x:P[0],y:P[1]}),P=T(J,X,_),P&&(X={x:P[0],y:P[1]}),{points:new W([J,X])}}),j("ripple",function(B,q,L,Q){Q||(q=-q);var R=Z(L,B),G=N(L,B),b=c(R,G),$=n(B.s("edge.offset"),b/2),e=B.s("edge.ripple.size"),H=B.s("edge.ripple.length"),F=B.s("edge.ripple.both"),z=B.s(_),y=B.s("edge.ripple.elevation"),r=new W,g=B.s("edge.ripple.straight")?null:new W,Y=new A(a(G.y-R.y,G.x-R.x));Q||(y=-y),b-=2*$,H>0&&(e=D(b/H));var K=b/e;g&&g.add(1);for(var t=0;e>t;t++)g&&g.add(3),0===t?r.add({x:$+K*t,y:z?0:q}):r.add({x:$+K*t,y:q}),r.add({x:$+K*t+K/2,y:y+q}),F&&(y=-y);for(r.add({x:$+b,y:z?0:q}),t=0;t<r.size();t++){var j=Y.tf(r.get(t));j.x+=R.x,j.y+=R.y,r.set(t,j)}return{points:r,segments:g}}),j("h.v",function(U,L,s){L=Q(U,s);var y=new W,l=U.s(_),X=Z(s,U),H=X.x,m=X.y,C=N(s,U),t=C.x,T=C.y,D=0,A=0,o=t-H,d=T-m;return l||(D=E(s,U._40I)/2,A=K(s,U._41I)/2),o>=0&&0>=d?(y.add({x:H+D,y:m+L}),y.add({x:t+L,y:m+L}),y.add({x:t+L,y:T+A})):0>=o&&d>=0?(y.add({x:H-D,y:m+L}),y.add({x:t+L,y:m+L}),y.add({x:t+L,y:T-A})):o>=0&&d>=0?(y.add({x:H+D,y:m+L}),y.add({x:t-L,y:m+L}),y.add({x:t-L,y:T-A})):(y.add({x:H-D,y:m+L}),y.add({x:t-L,y:m+L}),y.add({x:t-L,y:T+A})),g(y,U)}),j("v.h",function(L,R,Y){R=Q(L,Y);var n=new W,D=L.s(_),u=Z(Y,L),f=u.x,v=u.y,M=N(Y,L),c=M.x,r=M.y,h=0,d=0,S=c-f,i=r-v;return D||(h=E(Y,L._41I)/2,d=K(Y,L._40I)/2),S>=0&&0>=i?(n.add({x:f+R,y:v-d}),n.add({x:f+R,y:r+R}),n.add({x:c-h,y:r+R})):0>=S&&i>=0?(n.add({x:f+R,y:v+d}),n.add({x:f+R,y:r+R}),n.add({x:c+h,y:r+R})):S>=0&&i>=0?(n.add({x:f-R,y:v+d}),n.add({x:f-R,y:r+R}),n.add({x:c-h,y:r+R})):(n.add({x:f-R,y:v-d}),n.add({x:f-R,y:r+R}),n.add({x:c+h,y:r+R})),g(n,L)}),j("ortho",function(X,S,y){var F=new W,z=X.s(_),D=X.s("edge.ortho"),H=X._40I,V=X._41I,T=Z(y,X),Y=T.x,p=T.y,t=N(y,X),m=t.x,k=t.y,j=m-Y,l=k-p,G=z?0:E(y,H)/2,u=z?0:K(y,H)/2,A=z?0:E(y,V)/2,f=z?0:K(y,V)/2,o=(j-(G+A)*(j>0?1:-1))*D,e=(l-(u+f)*(l>0?1:-1))*D;return s(j)<s(l)?j>=0&&0>=l?(F.add({x:Y+S,y:p-u}),F.add({x:Y+S,y:p+e+S-u}),F.add({x:m+S,y:p+e+S-u}),F.add({x:m+S,y:k+f})):0>=j&&l>=0?(F.add({x:Y+S,y:p+u}),F.add({x:Y+S,y:p+e+S+u}),F.add({x:m+S,y:p+e+S+u}),F.add({x:m+S,y:k-f})):j>=0&&l>=0?(F.add({x:Y+S,y:p+u}),F.add({x:Y+S,y:p+e-S+u}),F.add({x:m+S,y:p+e-S+u}),F.add({x:m+S,y:k-f})):(F.add({x:Y+S,y:p-u}),F.add({x:Y+S,y:p+e-S-u}),F.add({x:m+S,y:p+e-S-u}),F.add({x:m+S,y:k+f})):j>=0&&0>=l?(F.add({x:Y+G,y:p+S}),F.add({x:Y+o+S+G,y:p+S}),F.add({x:Y+o+S+G,y:k+S}),F.add({x:m-A,y:k+S})):0>=j&&l>=0?(F.add({x:Y-G,y:p+S}),F.add({x:Y+o+S-G,y:p+S}),F.add({x:Y+o+S-G,y:k+S}),F.add({x:m+A,y:k+S})):j>=0&&l>=0?(F.add({x:Y+G,y:p+S}),F.add({x:Y+o-S+G,y:p+S}),F.add({x:Y+o-S+G,y:k+S}),F.add({x:m-A,y:k+S})):(F.add({x:Y-G,y:p+S}),F.add({x:Y+o-S-G,y:p+S}),F.add({x:Y+o-S-G,y:k+S}),F.add({x:m+A,y:k+S})),g(F,X)}),j("flex",function(h,G,t){var x=new W,w=h.s("edge.flex")+r(h,t),Q=h.s(_),p=h._40I,e=h._41I,J=Z(t,h),k=J.x,O=J.y,L=N(t,h),z=L.x,m=L.y,u=z-k,R=m-O,$=Q?0:E(t,p)/2,y=Q?0:K(t,p)/2,B=Q?0:E(t,e)/2,b=Q?0:K(t,e)/2,f=u>0?w:-w,D=R>0?w:-w;return s(u)<s(R)?u>=0&&0>=R?(x.add({x:k+G,y:O-y}),x.add({x:k+G,y:O+D+G-y}),x.add({x:z+G,y:m-D+G+b}),x.add({x:z+G,y:m+b})):0>=u&&R>=0?(x.add({x:k+G,y:O+y}),x.add({x:k+G,y:O+D+G+y}),x.add({x:z+G,y:m-D+G-b}),x.add({x:z+G,y:m-b})):u>=0&&R>=0?(x.add({x:k+G,y:O+y}),x.add({x:k+G,y:O+D-G+y}),x.add({x:z+G,y:m-D-G-b}),x.add({x:z+G,y:m-b})):(x.add({x:k+G,y:O-y}),x.add({x:k+G,y:O+D-G-y}),x.add({x:z+G,y:m-D-G+b}),x.add({x:z+G,y:m+b})):u>=0&&0>=R?(x.add({x:k+$,y:O+G}),x.add({x:k+f+G+$,y:O+G}),x.add({x:z-f+G-B,y:m+G}),x.add({x:z-B,y:m+G})):0>=u&&R>=0?(x.add({x:k-$,y:O+G}),x.add({x:k+f+G-$,y:O+G}),x.add({x:z-f+G+B,y:m+G}),x.add({x:z+B,y:m+G})):u>=0&&R>=0?(x.add({x:k+$,y:O+G}),x.add({x:k+f-G+$,y:O+G}),x.add({x:z-f-G-B,y:m+G}),x.add({x:z-B,y:m+G})):(x.add({x:k-$,y:O+G}),x.add({x:k+f-G-$,y:O+G}),x.add({x:z-f-G+B,y:m+G}),x.add({x:z+B,y:m+G})),g(x,h)}),j("extend.east",function(m,w,k){var C=new W,j=m.s(R)+r(m,k),D=m.s(_),J=Z(k,m),o=J.x+(D?0:E(k,m._40I)/2),F=J.y,O=N(k,m),$=O.x+(D?0:E(k,m._41I)/2),p=O.y,b=H(o,$)+j;return F>p?(C.add({x:o,y:F+w}),C.add({x:b+w,y:F+w}),C.add({x:b+w,y:p-w}),C.add({x:$,y:p-w})):(C.add({x:o,y:F-w}),C.add({x:b+w,y:F-w}),C.add({x:b+w,y:p+w}),C.add({x:$,y:p+w})),g(C,m)}),j("extend.west",function(e,K,O){var w=new W,J=e.s(R)+r(e,O),$=e.s(_),b=Z(O,e),B=b.x-($?0:E(O,e._40I)/2),F=b.y,M=N(O,e),z=M.x-($?0:E(O,e._41I)/2),H=M.y,h=n(B,z)-J;return F>H?(w.add({x:B,y:F+K}),w.add({x:h-K,y:F+K}),w.add({x:h-K,y:H-K}),w.add({x:z,y:H-K})):(w.add({x:B,y:F-K}),w.add({x:h-K,y:F-K}),w.add({x:h-K,y:H+K}),w.add({x:z,y:H+K})),g(w,e)}),j("extend.north",function(s,P,x){var Q=new W,F=s.s(R)+r(s,x),O=s.s(_),i=Z(x,s),z=i.x,B=i.y-(O?0:K(x,s._40I)/2),a=N(x,s),e=a.x,c=a.y-(O?0:K(x,s._41I)/2),S=n(B,c)-F;return z>e?(Q.add({y:B,x:z+P}),Q.add({y:S-P,x:z+P}),Q.add({y:S-P,x:e-P}),Q.add({y:c,x:e-P})):(Q.add({y:B,x:z-P}),Q.add({y:S-P,x:z-P}),Q.add({y:S-P,x:e+P}),Q.add({y:c,x:e+P})),g(Q,s)}),j("extend.south",function(d,A,Y){var J=new W,v=d.s(R)+r(d,Y),G=d.s(_),k=Z(Y,d),O=k.x,e=k.y+(G?0:K(Y,d._40I)/2),q=N(Y,d),h=q.x,S=q.y+(G?0:K(Y,d._41I)/2),E=H(e,S)+v;return O>h?(J.add({y:e,x:O+A}),J.add({y:E+A,x:O+A}),J.add({y:E+A,x:h-A}),J.add({y:S,x:h-A})):(J.add({y:e,x:O-A}),J.add({y:E+A,x:O-A}),J.add({y:E+A,x:h+A}),J.add({y:S,x:h+A})),g(J,d)});var Y=function(j,A,l,T,N){if(T.sort(function(g,M){var q=g.getSourceAgent()===A?g.getTargetAgent():g.getSourceAgent(),K=M.getSourceAgent()===A?M.getTargetAgent():M.getSourceAgent(),u=q.p(),p=K.p();if(l===e||l===i){if(u.y>p.y)return 1;if(u.y<p.y)return-1}else{if(u.x>p.x)return 1;if(u.x<p.x)return-1}return w.sortFunc(g.getId(),M.getId())}),N){for(var C,r,V,E=j.getSourceAgent(),Y=j.getTargetAgent(),d=0;d<T.size();d++){var m=T.get(d);m.getSourceAgent()===E&&m.getTargetAgent()===Y||m.getTargetAgent()===E&&m.getSourceAgent()===Y?(r||(r=new W),r.add(m,0)):r?(V||(V=new W),V.add(m)):(C||(C=new W),C.add(m))}T.clear(),C&&T.addAll(C),r&&T.addAll(r),V&&T.addAll(V)}var O=T.indexOf(j),c=T.size(),M=j.s(U);return{side:l,index:O,size:c,offset:-M*(c-1)/2+M*O}},q=function(i,j,I,q,Q){var w=j.s(P);return Y(j,I,q,I.getAgentEdges().toList(function(E){return i.isVisible(E)&&E.s(P)===w}),Q)},f=function(E,d){var h=E.getSourceAgent()===d?E.getTargetAgent():E.getSourceAgent(),m=d.p(),K=h.p(),Y=K.x-m.x,g=K.y-m.y;return Y>0&&s(g)<=Y?i:0>Y&&s(g)<=-Y?e:g>0&&s(Y)<=g?B:$},O=function(Z,b,R){var G=b.s(P),W=f(b,R);return Y(b,R,W,R.getAgentEdges().toList(function(C){return Z.isVisible(C)&&C.s(P)===G&&f(C,R)===W}))},V=function(z,R){var m=z.getSourceAgent()===R,s=m?z.getTargetAgent():z.getSourceAgent(),g=R.p(),U=s.p();return m?g.y>U.y?$:B:g.x<U.x?i:e},S=function(z,H,j){var O=H.s(P),t=V(H,j);return Y(H,j,t,j.getAgentEdges().toList(function(F){return z.isVisible(F)&&F.s(P)===O&&V(F,j)===t}),t===i||t===B)},d=function(P,v){var Z=P.getSourceAgent()===v,g=Z?P.getTargetAgent():P.getSourceAgent(),J=v.p(),L=g.p();return Z?J.x<L.x?i:e:J.y>L.y?$:B},X=function(x,D,C){var T=D.s(P),r=d(D,C);return Y(D,C,r,C.getAgentEdges().toList(function(m){return x.isVisible(m)&&m.s(P)===T&&d(m,C)===r}),r===i||r===B)},l=function(o,c,p){var T=o.getSourceAgent(),G=o.getTargetAgent(),n=T.getId()>G.getId(),R=n?G:T,V=n?T:G,F=R.p(),z=V.p(),Y=p(c,o,R),b=p(c,o,V),q=o.s(_),s=q?0:E(c,R)/2,D=q?0:E(c,V)/2,S=q?0:K(c,R)/2,M=q?0:K(c,V)/2,y=Y.offset,H=b.offset,A=Y.side,r=b.side,a=new W;return A===$?(a.add({x:F.x+y,y:F.y-S}),a.add({x:F.x+y,y:z.y+H}),r===e?a.add({x:z.x-D,y:z.y+H}):a.add({x:z.x+D,y:z.y+H})):A===B?(a.add({x:F.x+y,y:F.y+S}),a.add({x:F.x+y,y:z.y+H}),r===e?a.add({x:z.x-D,y:z.y+H}):a.add({x:z.x+D,y:z.y+H})):A===e?(a.add({x:F.x-s,y:F.y+y}),a.add({x:z.x+H,y:F.y+y}),r===B?a.add({x:z.x+H,y:z.y+M}):a.add({x:z.x+H,y:z.y-M})):A===i&&(a.add({x:F.x+s,y:F.y+y}),a.add({x:z.x+H,y:F.y+y}),r===B?a.add({x:z.x+H,y:z.y+M}):a.add({x:z.x+H,y:z.y-M})),n&&a.reverse(),g(a,o)};j("ortho2",function(R,h,u){var r,x,H=R.s(_),j=R.s("edge.ortho"),t=R.getSourceAgent(),A=R.getTargetAgent(),w=t.getId()>A.getId(),D=w?A:t,z=w?t:A,F=D.p(),Q=z.p(),q=O(u,R,D),T=O(u,R,z),X=H?0:E(u,D)/2,P=H?0:K(u,D)/2,d=H?0:E(u,z)/2,N=H?0:K(u,z)/2,J=new W,Z=q.offset,I=T.offset,c=q.side;return c===i?(r=Q.y>F.y?-Z:Z,x=F.x+X+(Q.x-d-F.x-X)*j,J.add({x:F.x+X,y:F.y+Z}),J.add({x:x+r,y:F.y+Z}),J.add({x:x+r,y:Q.y+I}),J.add({x:Q.x-d,y:Q.y+I})):c===e?(r=Q.y>F.y?-Z:Z,x=F.x-X-(F.x-X-Q.x-d)*j,J.add({x:F.x-X,y:F.y+Z}),J.add({x:x-r,y:F.y+Z}),J.add({x:x-r,y:Q.y+I}),J.add({x:Q.x+d,y:Q.y+I})):c===B?(r=Q.x>F.x?-Z:Z,x=F.y+P+(Q.y-N-F.y-P)*j,J.add({x:F.x+Z,y:F.y+P}),J.add({x:F.x+Z,y:x+r}),J.add({x:Q.x+I,y:x+r}),J.add({x:Q.x+I,y:Q.y-N})):c===$&&(r=Q.x>F.x?-Z:Z,x=F.y-P-(F.y-P-Q.y-N)*j,J.add({x:F.x+Z,y:F.y-P}),J.add({x:F.x+Z,y:x-r}),J.add({x:Q.x+I,y:x-r}),J.add({x:Q.x+I,y:Q.y+N})),w&&J.reverse(),g(J,R)},!0),j("flex2",function(w,m,Y){var S,A=w.getSourceAgent(),p=w.getTargetAgent(),z=A.getId()>p.getId(),L=z?p:A,C=z?A:p,I=L.p(),J=C.p(),n=O(Y,w,L),d=O(Y,w,C),F=w.s(_),k=w.s("edge.flex")+(n.size-1)/2*w.s(U),y=F?0:E(Y,L)/2,j=F?0:K(Y,L)/2,o=F?0:E(Y,C)/2,N=F?0:K(Y,C)/2,h=new W,T=n.offset,q=d.offset,R=n.side;return R===i?(S=J.y>I.y?-T:T,h.add({x:I.x+y,y:I.y+T}),h.add({x:I.x+y+k+S,y:I.y+T}),h.add({x:J.x-o-k+S,y:J.y+q}),h.add({x:J.x-o,y:J.y+q})):R===e?(S=J.y>I.y?-T:T,h.add({x:I.x-y,y:I.y+T}),h.add({x:I.x-y-k-S,y:I.y+T}),h.add({x:J.x+o+k-S,y:J.y+q}),h.add({x:J.x+o,y:J.y+q})):R===B?(S=J.x>I.x?-T:T,h.add({x:I.x+T,y:I.y+j}),h.add({x:I.x+T,y:I.y+j+k+S}),h.add({x:J.x+q,y:J.y-N-k+S}),h.add({x:J.x+q,y:J.y-N})):R===$&&(S=J.x>I.x?-T:T,h.add({x:I.x+T,y:I.y-j}),h.add({x:I.x+T,y:I.y-j-k-S}),h.add({x:J.x+q,y:J.y+N+k-S}),h.add({x:J.x+q,y:J.y+N})),z&&h.reverse(),g(h,w)},!0),j("extend.north2",function(v,s,z){var f=v.getSourceAgent(),y=v.getTargetAgent(),N=f.getId()>y.getId(),M=N?y:f,D=N?f:y,x=M.p(),k=D.p(),l=q(z,v,M,$),h=q(z,v,D,$,!0),b=v.s(_),o=b?0:K(z,M)/2,V=b?0:K(z,D)/2,P=l.offset,j=h.offset,E=v.s(R)+(l.size-1)/2*v.s(U),J=n(x.y-o,k.y-V)-E+(x.x<k.x?P:-P),I=new W;return I.add({x:x.x+P,y:x.y-o}),I.add({x:x.x+P,y:J}),I.add({x:k.x+j,y:J}),I.add({x:k.x+j,y:k.y-V}),N&&I.reverse(),g(I,v)},!0),j("extend.south2",function(A,P,p){var G=A.getSourceAgent(),X=A.getTargetAgent(),d=G.getId()>X.getId(),V=d?X:G,Y=d?G:X,l=V.p(),$=Y.p(),Z=q(p,A,V,B),m=q(p,A,Y,B,!0),N=A.s(_),z=N?0:K(p,V)/2,b=N?0:K(p,Y)/2,C=Z.offset,I=m.offset,k=A.s(R)+(Z.size-1)/2*A.s(U),t=H(l.y+z,$.y+b)+k+(l.x>$.x?C:-C),x=new W;return x.add({x:l.x+C,y:l.y+z}),x.add({x:l.x+C,y:t}),x.add({x:$.x+I,y:t}),x.add({x:$.x+I,y:$.y+b}),d&&x.reverse(),g(x,A)},!0),j("extend.west2",function(F,z,G){var Q=F.getSourceAgent(),h=F.getTargetAgent(),o=Q.getId()>h.getId(),d=o?h:Q,T=o?Q:h,H=d.p(),J=T.p(),t=q(G,F,d,$),K=q(G,F,T,$,!0),i=F.s(_),x=i?0:E(G,d)/2,r=i?0:E(G,T)/2,a=t.offset,m=K.offset,Y=F.s(R)+(t.size-1)/2*F.s(U),D=n(H.x-x,J.x-r)-Y+(H.y<J.y?a:-a),Z=new W;return Z.add({x:H.x-x,y:H.y+a}),Z.add({x:D,y:H.y+a}),Z.add({x:D,y:J.y+m}),Z.add({x:J.x-r,y:J.y+m}),o&&Z.reverse(),g(Z,F)},!0),j("extend.east2",function(k,J,d){var N=k.getSourceAgent(),p=k.getTargetAgent(),S=N.getId()>p.getId(),T=S?p:N,K=S?N:p,n=T.p(),b=K.p(),G=q(d,k,T,$),L=q(d,k,K,$,!0),f=k.s(_),Z=f?0:E(d,T)/2,m=f?0:E(d,K)/2,C=G.offset,y=L.offset,D=k.s(R)+(G.size-1)/2*k.s(U),v=H(n.x+Z,b.x+m)+D+(n.y>b.y?C:-C),x=new W;return x.add({x:n.x+Z,y:n.y+C}),x.add({x:v,y:n.y+C}),x.add({x:v,y:b.y+y}),x.add({x:b.x+m,y:b.y+y}),S&&x.reverse(),g(x,k)},!0),j("v.h2",function(v,R,F){return l(v,F,S)},!0),j("h.v2",function(v,M,q){return l(v,q,X)},!0)}(this,Object);