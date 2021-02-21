!function(l,X,f){"use strict";var u="ht",O=l[u],B=O.Default,T=B.isTouchable,E=O.Color,Y="px",r="0",v="innerHTML",h="className",V="position",g="absolute",S="width",H="height",s="left",k="top",y="right",i="bottom",Q="max-width",x="max-height",P=null,Z="none",n="",N=l.parseInt,t=l.setTimeout,$=B.getInternal(),m=B.animate,d=E.titleIconBackground,D=function(){return document},o=function(Y){return D().createElement(Y)},U=function(){return o("div")},F=function(){return o("canvas")},C=function(Z,S,c){Z.style.setProperty(S,c,P)},G=function(Z,L){return Z.style.getPropertyValue(L)},A=function(x,p){x.appendChild(p)},M=function(Z,s){Z.removeChild(s)},W=function(t){var i=t.scrollWidth,I=t.scrollHeight;return t===D().body&&(i=Math.max(i,D().documentElement.scrollWidth),I=Math.max(I,D().documentElement.scrollHeight)),{width:i,height:I}},q=function(i){var K=i.touches[0];return K?K:i.changedTouches[0]};$.addMethod(B,{panelExpandIcon:{width:16,height:16,comps:[{type:"triangle",rect:[4,4,10,8],background:d,rotation:3.14}]},panelCollapseIcon:{width:16,height:16,comps:[{type:"triangle",rect:[4,4,10,8],background:d}]},panelLockIcon:{width:100,height:100,comps:[{type:"roundRect",rect:[10,50,80,40],borderWidth:10,borderColor:d},{type:"shape",points:[37,45,37,20,37,13,43,13,63,13,69,13,70,19,70,44],segments:[1,2,3,2,3,2],borderWidth:10,borderColor:d}]},panelUnLockIcon:{width:100,height:100,comps:[{type:"roundRect",rect:[10,50,80,40],borderWidth:10,borderColor:d},{type:"shape",points:[37,45,37,20,37,13,43,13,63,13,69,13,70,19,70,26],segments:[1,2,3,2,3,2],borderWidth:10,borderColor:d}]},panelMinimizeIcon:{width:100,height:100,comps:[{type:"shape",points:[10,35,35,35,35,10],segments:[1,2,2],borderWidth:8,borderColor:d},{type:"shape",points:[90,35,65,35,65,10],segments:[1,2,2],borderWidth:8,borderColor:d},{type:"shape",points:[10,65,35,65,35,90],segments:[1,2,2],borderWidth:8,borderColor:d},{type:"shape",points:[65,90,65,65,90,65],segments:[1,2,2],borderWidth:8,borderColor:d}]},panelRestoreIcon:{width:300,height:300,comps:[{type:"rect",rect:[10,24,268,56],background:d},{type:"rect",rect:[10,118,268,56],background:d},{type:"rect",rect:[10,213,268,56],background:d}]},panelTitleLabelColor:B.labelSelectColor,panelTitleLabelFont:B.labelFont,panelContentLabelFont:B.labelFont,panelTitleBackground:E.titleBackground,panelSeparatorWidth:1,panelSeparatorColor:f},!0);var J=O.widget.Panel=function(D){var N=this,q=N._view=$.createView(null,N);N.$1i=0,N.$18i=new O.Notifier,N.$2i="leftTop",C(q,k,r),C(q,s,r),C(q,V,g),C(q,"overflow","hidden"),N._interactor=new e(N),N.setConfig(D),N.addEventListener(function(i){var x=N.getPanelConfig(i.id),X=x.content;("beginRestore"===i.kind||"betweenResize"===i.kind||"endToggle"===i.kind)&&(X&&X.invalidate&&X.invalidate(),x.items&&x.items.forEach(function(d){d&&d.content&&d.content.invalidate&&d.content.invalidate()}))})};B.def(J,X,{ms_v:1,_dragContainment:"parent",setConfig:function(W){function b(p){p.expanded=!p.expanded,B.togglePanel(p.id,!0,!0)}if(W){var B=this;B._view[v]=n,B._config=W,B.$35i=[],W.expanded==P&&(W.expanded=W.expand!=P?W.expand:!0);var L=B._view,q=B.$24i(W,L,!0),j=q[2],J=W.width;B.$35i.push(q[1]),W.items&&W.items.forEach(function(V){V.expanded==P&&(V.expanded=V.expand!=P?V.expand:!0);var l=B.$24i(V,j.children[0]);B.$35i.push(l[1])});var m=U();C(m,S,10+Y),C(m,H,10+Y),C(m,V,g),C(m,i,r),C(m,y,r),m[h]="resize-area",A(L,m),B.$10i(),W.flowLayout&&C(L,V,"relative"),C(L,"opacity",r),A(D().body,L),J==P&&(J=L.offsetWidth),C(L,S,J+Y),C(L,Q,J+Y),W.items&&W.items.forEach(function(u){b(u)}),W.buttons&&W.buttons.indexOf("toggle")<0||b(W),W.minimized==P&&W.minimize!=P&&(W.minimized=W.minimize),W.minimized&&W.minimizable!==!1&&B.minimize(!0),M(D().body,L),C(L,"opacity",n),B.iv()}},getPanelConfig:function(Y){var H=this,r=H._config,d=r.items;if(r.id===Y)return r;if(d)for(var A=0;A<d.length;A++){var w=d[A];if(w.id===Y)return w}},getPanelView:function(g){for(var c=this,A=c.$35i,I=0;I<A.length;I++){var P=A[I],l=P.parentNode;if(l.$15i===g)return l}},setDragContainment:function(P){this._dragContainment=P},getDragContainment:function(){return this._dragContainment},$20i:function(){var t=this._config,N=t.restoreIconSize||24;return N},$5i:function(W){var L=W.titleIconSize||16;return T&&(L*=1.2),L},$4i:function(S){var o=S.titleHeight||B.widgetTitleHeight;return o},setTitle:function(o,V){if(V==P&&(V=this._config.id),V!=P){var F=this.getPanelConfig(V),Z=this.getPanelView(V);F.title=o,Z.querySelector(".panel-title span").innerHTML=o}},setInnerPanel:function(J){var f,q,O=this,A=J.id,M=O.$35i,N=!1;if(J.expanded==P&&(J.expanded=!0),A!=P){var i=O.getPanelConfig(A);if(i){N=!0;var U,S=O.getPanelView(A),y=S.parentNode,e=S.children[0];if(S!==O._view){O.$11i();for(U in i)delete i.key;for(U in J)i[U]=J[U];f=O.$24i(J,y,!1,S),q=f[1],y.removeChild(S);for(var m=0;m<M.length;m++)if(M[m]===e){M.splice(m,1,q);break}O.$12i(),i.expanded=!i.expanded,O.togglePanel(i.id,!0,!0),O.iv()}}}if(!N){O.$11i(),f=O.$24i(J,O._view.children[1]),q=f[1],M.push(q),O._config.items||(O._config.items=[]);var R=O._config.items;if(R.push(J),O.$12i(),R.length>1){var X=R[R.length-2],H=O.getPanelView(X.id).children[0];C(H,"border-bottom",O.$55i(X))}J.expanded=!J.expanded,O.togglePanel(J.id,!0,!0),O.iv()}},removeInnerPanel:function(z){var M,w=this,J=-1,B=w._config.items;if(B)for(M=0;M<B.length;M++){var A=B[M];if(A.id===z){J=M;break}}if(w.$11i(),J>=0){var y=w.$35i,H=w.getPanelView(z),G=H.children[0];for(M=0;M<y.length;M++)if(y[M]===G){y.splice(M,1);break}B.splice(J,1),H.parentNode.removeChild(H)}if(w.$12i(),B.length>0){var g=B[B.length-1],a=w.getPanelView(g.id).children[0];C(a,"border-bottom",w.$55i(g))}},$6i:function(x){C(x,"cursor","pointer"),C(x,"display","inline-block"),C(x,"margin-right",(T?8:4)+Y),C(x,"vertical-align",k)},$24i:function(V,v,w,E){var Q=this,f=Q._config.flowLayout,d=w?v:U(),J=Q.$50i(V),e=Q.$3i(V,w);d[h]="ht-widget-panel"+(w?" outer-panel":" inner-panel"),V.borderWidth==P&&(V.borderWidth=w?2:0);var t=V.borderWidth;if(t="0 "+t+Y+" "+t+Y+" "+t+Y+" ",C(d,"padding",t),A(d,e),A(d,J),w||(E?v.insertBefore(d,E):A(v,d)),!f&&w&&V.minimizable!==!1){var j=F(),R=Q.$20i(),y=V.restoreToolTip;$.setCanvas(j,R,R),j[h]="control-button button-minimize button-minimize-restore",Q.$6i(j),C(j,"display","none"),A(v,j),j.title=y||""}var I=V.titleBackground||B.panelTitleBackground;if(C(d,"background-color",I),V.id==P){for(var S=Q.$1i++;Q.getPanelConfig(S);)S=Q.$1i++;V.id=S}return d.$15i=V.id,V.width&&(d.style.width=V.width+Y),[d,e,J]},$9i:function(a){var T=F();T[h]="control-button button-toggle button-toggle-expand",T.title=a.toggleToolTip||"";var g=this.$4i(a),c=this.$5i(a);return this.$6i(T),$.setCanvas(T,c,g),T},$8i:function(r){var Z=F(),L="control-button button-independent-switch";Z[h]=r.independent===!0?L+" button-independent-switch-on":L+" button-independent-switch-off",Z.title=r.independentSwitchToolTip||"";var d=this.$4i(r),O=this.$5i(r);return this.$6i(Z),$.setCanvas(Z,O,d),Z},$7i:function(Q){var M=F();M[h]="control-button button-minimize button-minimize-minimize",M.title=Q.minimizeToolTip||"";var j=this.$4i(Q),o=this.$5i(Q);return this.$6i(M),$.setCanvas(M,o,j),M},$55i:function(u){var H=this._config,X=H.items,h=u.separatorWidth||B.panelSeparatorWidth,N=u.titleBackground||B.panelTitleBackground,w=u.expanded!==!1?N:u.separatorColor||B.panelSeparatorColor||B.brighter(N);return(H===u||X&&X.indexOf(u)===X.length-1)&&(h=0),h+Y+" solid "+w},$3i:function(L,w){var x=this,D=x._config.flowLayout,q=U(),W=U(),b=x.$4i(L),m=L.titleBackground,f=L.titleColor,I=L.titleIcon,e=L.buttons;if(q[h]="panel-title",C(q,V,"relative"),C(q,"background",m||B.panelTitleBackground),C(q,"color",f||B.panelTitleLabelColor),C(q,k,r),C(q,"box-sizing","border-box"),C(q,"-moz-box-sizing","border-box"),C(q,"padding","0 5px 0 0"),C(q,S,"100%"),C(q,"cursor","default"),C(q,"white-space","nowrap"),C(q,"font",B.panelTitleLabelFont),I){var d=F();d[h]="control-button panel-title-icon";var X=x.$4i(L),J=x.$5i(L);x.$6i(d),$.setCanvas(d,J,X),A(q,d)}var j=o("span");C(j,"display","inline-block"),C(j,"margin-left","5px"),j[v]="<span>"+L.title+"</span>",A(q,j),C(q,"line-height",b+Y),W[h]="panel-title-controls",C(W,V,g),C(W,s,r),C(W,y,5+Y),C(W,k,r),C(W,i,r),C(W,"text-align",y);var H=function(){var f=x.$9i(L);A(W,f)},a=function(){if(!D&&w&&L.minimizable!==!1){var n=x.$7i(L);A(W,n)}},u=function(){if(!w){var z=x.$8i(L);A(W,z)}},E=function(Y){var S=F();S[h]="control-button custombutton-"+Y.name,S.title=Y.toolTip||"",S._action=Y.action;var V=x.$4i(L),D=x.$5i(L);x.$6i(S),$.setCanvas(S,D,V),A(W,S)};if(e)for(var l=0;l<e.length;l++){var K=e[l];"string"==typeof K?"minimize"===K?a():"independentSwitch"===K?u():"toggle"===K&&H():"object"==typeof K&&E(K)}else a(),H();return A(q,W),q},$50i:function(N){var W=U(),F=N.contentHeight,J=U();if(C(J,V,"relative"),W[h]="panel-body",C(W,"overflow","hidden"),C(W,"background","white"),C(W,"font",B.panelContentLabelFont),A(W,J),N.content){var L;N.content.getView?(A(J,N.content.getView()),L=J.children[0]):N.content instanceof Element?(A(J,N.content),L=J.children[0]):J[v]=N.content,L&&(C(L,S,"100%"),C(L,H,"100%")),F&&C(J,H,F+Y)}return W},$10i:function(){var L=this,M=L._config,w=L._view,s=w.querySelector(".resize-area").style;s.display=M.flowLayout||M.minimized===!0||M.expanded===!1?Z:"block"},$11i:function(){var Y=this._view,c=Y.children[1];this.$13i>=0?this.$13i++:this.$13i=1,C(c,x,n),C(Y,Q,n)},$12i:function(){var $=--this.$13i;if(0===$){var a=this._view,X=a.children[1];C(X,x,X.scrollHeight+Y),C(a,Q,a.offsetWidth+Y)}},$14i:function(){var X=this._view,t=X.children[0],B=t.children[1].children,y=this._config,u=0;X.$26i=X.offsetWidth,u+=t.children[0].offsetWidth,y.titleIcon&&(u+=t.children[1].offsetWidth,B=t.children[2].children);for(var z=0;z<B.length;z++){var n=B[z];u+=n.offsetWidth+5}X.$51i=u+15},togglePanel:function(u,V,L){function y(A){var R=A.target,H=R.parentNode,$=w.getPanelConfig(H.$15i);delete H.$19i,R!==F&&w.$12i(),w.$18i.fire({kind:"endToggle",target:w,id:$.id})}for(var w=this,c=w._view,F=c.children[1],f=P,M=w.$35i,d=M.length,l=w._config.exclusive,W=w.$2i,g=[],a=w._config.narrowWhenCollapse,R=0;d>R;R++){var k=M[R],j=k.parentNode,T=j.$15i,A=w.getPanelConfig(T);T===u&&(f=j),!V&&l&&A.expanded&&j!==c&&T!==u&&A.independent!==!0&&g.push(j)}if(f&&!f.$19i){f.$19i=!0;var q=f.children[1],o=f.querySelector(".button-toggle"),K=w.getPanelConfig(f.$15i);if(!o)return;f===c||K.expanded||K.independent===!0||g.forEach(function(W){w.togglePanel(W.$15i,!0)}),f!==c&&w.$11i();var i=200;if(L&&(i=0),w.$18i.fire({kind:"beginToggle",target:w,id:f.$15i}),K.expanded){var E=function(){o[h]="control-button button-toggle",o[h]+=W.indexOf("Bottom")>=0?" button-toggle-expand":" button-toggle-collapse",C(q,S,q.clientWidth+Y),K.expanded=!1,C(f.children[0],"border-bottom",w.$55i(K)),m(q).duration(i).set("opacity",r).set(x,r).end(y),a&&f===c&&m(f).duration(i).set(Q,f.$51i+Y).end(),f[h]+=" panel-collapse",m(f).duration(i).set("padding-bottom",r).end(),w.$28i(K,!0),w.$10i()};a&&f===c&&w.$14i(),E()}else o[h]="control-button button-toggle",o[h]+=W.indexOf("Bottom")>=0?" button-toggle-collapse":" button-toggle-expand",C(q,S,n),K.expanded=!0,C(f.children[0],"border-bottom",w.$55i(K)),m(q).duration(i).set("opacity","1").set(x,q.scrollHeight+Y).end(y),a&&f===c&&m(f).duration(i).set(Q,(f.$26i||f.offsetWidth)+Y).end(),f[h]=f[h].replace(" panel-collapse",n),m(f).duration(i).set("padding-bottom",K.borderWidth+Y).end(),w.$28i(K,!0),w.$10i()}},$16i:function(){var e=this._view,E=e.$22i,F=e.$23i,Q=this.$2i;return E==P&&(Q.indexOf(s)>=0?E=e.$22i=0:Q.indexOf(y)>=0&&(E=e.$22i=100)),F==P&&(Q.indexOf("Top")>=0?F=e.$23i=0:Q.indexOf("Bottom")>=0&&(F=e.$23i=100)),[E,F]},$25i:function(){var F=this,o=F._view,m=o.$21i,e=F.$20i(),t=F.$16i(),q=t[0],J=t[1],L=F.$2i;o.children[0].style.display=Z,o.children[1].style.display=Z,o.children[2].style.display=n,C(o,"padding",r),C(o,Q,e+Y),"leftTop"===L?(C(o,s,N(G(o,s))+(m.width-e)*q/100+Y),C(o,k,N(G(o,k))+(m.height-e)*J/100+Y)):"leftBottom"===L?(C(o,s,N(G(o,s))+(m.width-e)*q/100+Y),C(o,i,N(G(o,i))+(m.height-e)*(1-J/100)+Y)):"rightTop"===L?(C(o,y,N(G(o,y))+(m.width-e)*(1-q/100)+Y),C(o,k,N(G(o,k))+(m.height-e)*J/100+Y)):"rightBottom"===L&&(C(o,y,N(G(o,y))+(m.width-e)*(1-q/100)+Y),C(o,i,N(G(o,i))+(m.height-e)*(1-J/100)+Y)),o[h]+=" panel-minimized",F.$18i.fire({kind:"endMinimize",target:F,id:o.$15i})},$17i:function(){var U=this,f=U._config,Z=U._view;C(Z,"-webkit-transform",n),C(Z,"-ms-transform",n),C(Z,"transform",n),f.minimized?U.$25i():(U.$18i.fire({kind:"endRestore",target:U,id:f.id}),Z[h]=Z[h].replace(" panel-minimized",n)),delete Z.$19i},minimize:function(M){var p=this,G=p._view;if(!G.$19i&&G.children[0].style.display!==Z){var R=p._config,h=G.getBoundingClientRect(),w=p.$20i(),T=h.width,n=h.height,H=w/T,c=w/n,f=p.$16i(),l=f[0],j=f[1];G.$52i=H,G.$53i=c,G.$21i=h,p.$18i.fire({kind:"beginMinimize",target:p,id:G.$15i});var o=200;M&&(o=0),R.minimized=!0,G.$19i=!0,R.expanded&&(G.$26i=G.offsetWidth);var J=l+"% "+j+"%";C(G,"-webkit-transform-origin",J),C(G,"-ms-transform-origin",J),C(G,"transform-origin",J),m(G).duration(o).scale(H,c).end(function(){p.$17i()}),p.$10i()}},restore:function(){var q,x,D,V,f,T,r,I,p,l,b,w,u=this,n=u._view,M=n.parentNode,L=u._config;if(!n.$19i&&L.minimized){var o=n.$21i,$=n.$52i,j=n.$53i,v=L.borderWidth+Y,P=v,R=v,K=0,E=u.$20i(),e=W(M),H=u.$2i;"leftTop"===H?(q=N(G(n,s)),D=N(G(n,k)),f=q,r=D,p=q+o.width-e.width,l=D+o.height-e.height,p>0&&(p>=q?q=0:q-=p),l>0&&(l>=D?D=0:D-=l),b=100*((f-q)/(o.width-E)),w=100*((r-D)/(o.height-E)),C(n,s,q+Y),C(n,k,D+Y)):"leftBottom"===H?(q=N(G(n,s)),V=N(G(n,i)),f=q,I=V,p=q+o.width-e.width,l=V+o.height-e.height,p>0&&(p>=q?q=0:q-=p),l>0&&(l>=V?V=0:V-=l),b=100*((f-q)/(o.width-E)),w=100*(1-(I-V)/(o.height-E)),C(n,s,q+Y),C(n,i,V+Y)):"rightTop"===H?(x=N(G(n,y)),D=N(G(n,k)),T=x,r=D,p=x+o.width-e.width,l=D+o.height-e.height,p>0&&(p>=x?x=0:x-=p),l>0&&(l>=D?D=0:D-=l),b=100*(1-(T-x)/(o.width-E)),w=100*((r-D)/(o.height-E)),C(n,y,x+Y),C(n,k,D+Y)):"rightBottom"===H&&(x=N(G(n,y)),V=N(G(n,i)),T=x,I=V,p=x+o.width-e.width,l=V+o.height-e.height,p>0&&(p>=x?x=0:x-=p),l>0&&(l>=V?V=0:V-=l),b=100*(1-(T-x)/(o.width-E)),w=100*(1-(I-V)/(o.height-E)),C(n,y,x+Y),C(n,i,V+Y)),n.children[0].style.display="block",n.children[1].style.display="block",n.children[2].style.display=Z,C(n,"padding",K+" "+P+" "+R+" "+v),C(n,"-webkit-transform","scale("+$+", "+j+")"),C(n,"-ms-transform","scale("+$+", "+j+")"),C(n,"transform","scale("+$+", "+j+")"),n.$22i=b,n.$23i=w,C(n,"-webkit-transform-origin",b+"% "+w+"%"),C(n,"-ms-transform-origin",b+"% "+w+"%"),C(n,"transform-origin",b+"% "+w+"%"),L.narrowWhenCollapse&&!L.expanded?C(n,Q,n.$51i+Y):C(n,Q,n.$26i+Y),u.$18i.fire({kind:"beginRestore",target:u,id:n.$15i}),n.$19i=!0,L.minimized=!1,t(function(){m(n).scale(1,1).end(function(){u.$17i()})},30),u.$10i()}},addEventListener:function($,f,g){this.$18i.add($,f,g)},removeEventListener:function(V,x){this.$18i.remove(V,x)},setPosition:function(a,x){var h=this._view,A=this.$2i;"leftTop"===A?(C(h,s,a+Y),C(h,k,x+Y),C(h,y,n),C(h,i,n)):"leftBottom"===A?(C(h,s,a+Y),C(h,i,x+Y),C(h,y,n),C(h,k,n)):"rightTop"===A?(C(h,y,a+Y),C(h,k,x+Y),C(h,s,n),C(h,i,n)):"rightBottom"===A&&(C(h,y,a+Y),C(h,i,x+Y),C(h,s,n),C(h,k,n)),delete h.$22i,delete h.$23i},getPosition:function(){var L=this._view,q=this.$2i;return"leftTop"===q?{x:N(G(L,s)),y:N(G(L,k))}:"leftBottom"===q?{x:N(G(L,s)),y:N(G(L,i))}:"rightTop"===q?{x:N(G(L,y)),y:N(G(L,k))}:"rightBottom"===q?{x:N(G(L,y)),y:N(G(L,i))}:void 0},setPositionRelativeTo:function(M){var j=this,B=j._view.querySelectorAll(".button-toggle"),e="control-button button-toggle",v=j.getPosition();j.$2i=M,j.setPosition(v.x,v.y);for(var H=0;H<B.length;H++){var C=B[H],S=j.getPanelConfig(C.parentNode.parentNode.parentNode.$15i);C[h]=S.expanded?M.indexOf("Bottom")>=0?e+" button-toggle-collapse":e+" button-toggle-expand":M.indexOf("Bottom")>=0?e+" button-toggle-expand":e+" button-toggle-collapse"}j.iv()},getPositionRelativeTo:function(){return this.$2i},invalidate:function(j){var z=this;z._68I||(z._68I=1,B.callLater(z.validate,z,P,j),z.onInvalidated&&z.onInvalidated(),z.fireViewEvent("invalidate"));var T=this._config,h=T.content;h&&h.invalidate&&h.invalidate(),T.items&&T.items.forEach(function(h){h&&h.content&&h.content.invalidate&&h.content.invalidate()})},$27i:function(t,f,s,K){var x=$.initContext(t);$.translateAndScale(x,0,0,1),x.clearRect(0,0,s,s);var i=(s-K)/2;B.drawStretchImage(x,B.getImage(f),"fill",0,i,K,K),x.restore()},$28i:function(j){var G,u,H,z=this,C=j.id,h=z.getPanelView(C),k=h.querySelector(".button-toggle"),Z=z.$2i.indexOf("Bottom")>=0;if(u=Z?B.panelCollapseIcon:B.panelExpandIcon,H=Z?B.panelExpandIcon:B.panelCollapseIcon,k){G=j.expanded?B.getImage(H):B.getImage(u);var X=z.$4i(j),g=z.$5i(j);z.$27i(k,G,X,g)}},$29i:function(t){var K,S=this,l=t.id,M=S.getPanelView(l),e=M.querySelector(".button-independent-switch"),$=B.panelUnLockIcon,C=B.panelLockIcon;if(e){K=t.independent!==!0?B.getImage(C):B.getImage($);var v=S.$4i(t),G=S.$5i(t);S.$27i(e,K,v,G)}},$30i:function(w){var u=this,T=w.id,Q=u.getPanelView(T),h=Q.querySelector(".button-minimize-minimize"),I=B.panelMinimizeIcon;if(h){var e=u.$4i(w),b=u.$5i(w);u.$27i(h,B.getImage(I),e,b)}},$31i:function(H){var g=this,k=H.id,N=g.getPanelView(k),Y=N.querySelector(".button-minimize-restore"),j=H.titleIcon||B.panelRestoreIcon;if(Y){var I=g.$20i();g.$27i(Y,B.getImage(j),I,I)}},$32i:function(o){var $=this,W=o.id,p=$.getPanelView(W);if(o.buttons){var j=o.buttons;j.forEach(function(d){var c=d.name,H=d.icon;if(c&&H){var z=p.querySelector(".custombutton-"+c);if(z){var U=$.$4i(o),h=$.$5i(o)-1;$.$27i(z,B.getImage(H),U,h)}}})}},$33i:function(g){var U=this,H=g.id,K=U.getPanelView(H),l=K.querySelector(".panel-title-icon"),O=g.titleIcon;if(l&&O){var t=U.$4i(g),T=U.$5i(g);U.$27i(l,B.getImage(O),t,T)}},validateImpl:function(){var b=this,t=b._config;b.$28i(t),b.$30i(t),b.$31i(t),b.$32i(t),b.$33i(t),t.items&&t.items.forEach(function(X){b.$28i(X),b.$29i(X),b.$32i(X)})}});var e=function(S){var B=this,$=S.getView();B.$34i=S,B.addListeners(),$.addEventListener("dblclick",B.$42i.bind(B))};B.def(e,X,{ms_listener:1,getView:function(){return this.$34i.getView()},clear:function(){delete this.$37i,delete this.$38i,delete this.$36i,delete this.$39i},$42i:function(S){for(var W=this.$34i,u=S.target,$=W.$35i,i=$.length,n=0;i>n;n++){var T=$[n];T.contains(u)&&(S.preventDefault(),W.togglePanel(T.parentNode.$15i))}},handle_touchstart:function(g){var y=this,$=y.$34i,s=$._config,m=s.flowLayout,A=g;if(B.preventDefault(g),B.isLeftButton(g)){var c=g.target,K=$.getView().children[0],b=$.getView().querySelector(".button-minimize-restore");T&&(A=q(g));var D=y.$40i={x:A.pageX,y:A.pageY};y.$41i={x:D.x,y:D.y},(!m&&K.contains(c)||b&&b.contains(c))&&(y.$38i=!0,B.startDragging(y,g)),!m&&y.handle_mousemove(g)&&(y.$37i=!0,B.startDragging(y,g),$.$11i())}},handle_mousedown:function(k){this.handle_touchstart(k)},handle_touchend:function(C){var r=this,a=r.$34i,f=C.target,q=a.$35i,i=q.length,j=0,S=a.getView(),n=S.querySelector(".button-minimize"),D=S.querySelector(".button-minimize-restore");if(!r.$39i&&!r.$36i){if(n&&n.contains(f)||D&&D.contains(f))C.preventDefault(),a._config.minimized?a.restore():a.minimize();else for(;i>j;j++){var O=q[j],b=O.parentNode,p=b.$15i,H=a.getPanelConfig(p),g=O.querySelector(".button-toggle"),u=O.querySelector(".button-independent-switch");if(g===f)C.preventDefault(),a.togglePanel(p);else if(u===f){C.preventDefault();var l="button-independent-switch-off",d="button-independent-switch-on";H.independent=H.independent==P?!0:!H.independent,u[h]=H.independent?u[h].replace(l,d):u[h].replace(d,l),a.$29i(H)}else f[h]&&f[h].indexOf("control-button custombutton-")>=0&&O.contains(f)&&f._action.call(a,H,a.getPanelView(p),C)}delete r.$40i,delete r.$41i}},handle_mouseup:function(n){this.handle_touchend(n)},handleWindowTouchEnd:function(){var c=this,K=c.$34i;c.$37i&&c.$36i?(K.$18i.fire({kind:"endResize",target:K,id:K.getView().$15i}),K.$12i()):c.$38i&&c.$39i&&K.$18i.fire({kind:"endMove",target:K,id:K.getView().$15i}),this.clear()},handleWindowMouseUp:function(T){this.handleWindowTouchEnd(T)},handle_mousemove:function(g){var x=this,l=x.getView(),L=l.querySelector(".resize-area"),H=L.getBoundingClientRect(),Y={x:H.left,y:H.top,width:H.width,height:H.height};g=T?q(g):g;var W=g.clientX,_=g.clientY,P=x.$34i._config;return P.expanded&&P.minimized!==!0&&B.containsPoint(Y,{x:W,y:_})?(l.style.cursor="nwse-resize",!0):(l.style.cursor=n,void 0)},handleWindowTouchMove:function(p){p.preventDefault();var o=p;T&&(o=q(p));var I=this,O=I.$40i,z=I.$41i;if(!(z.x==O.x&&z.y==O.y&&B.getDistance(z,{x:o.pageX,y:o.pageY})<=1)){var D=I.$34i,$=I.getView(),d=$.parentNode,n=D._config,M=n.resizeMode||"wh",P=o.pageX-O.x,v=o.pageY-O.y,j=D.$2i;if(I.$37i){var f=$.children[1].children[0],R=$.clientWidth,l=f.clientHeight,m=R+P,h=l+v;m=Math.max(m,100),h=Math.max(h,100),"w"===M?(C($,S,m+Y),n.width=m,O.x+=m-R,j.indexOf(y)>=0&&C($,y,N(G($,y))-(m-R)+Y)):"h"===M?(C(f,H,h+Y),n.contentHeight=h,O.y+=h-l,j.indexOf("Bottom")>=0&&C($,i,N(G($,i))-(h-l)+Y)):"wh"===M&&(C($,S,m+Y),C(f,H,h+Y),n.width=m,n.contentHeight=h,O.x+=m-R,O.y+=h-l,j.indexOf("right")>=0&&C($,y,N(G($,y))-(m-R)+Y),j.indexOf("Bottom")>=0&&C($,i,N(G($,i))-(h-l)+Y)),I.$36i?D.$18i.fire({kind:"betweenResize",target:D,id:D.getView().$15i}):(I.$36i=!0,D.$18i.fire({kind:"beginResize",target:D,id:D.getView().$15i}))}else if(I.$38i){var Z,t,A,a,x,F,K,g,J,e,c=$.getBoundingClientRect(),r=c.width,u=c.height,V=W(d),U=V.width,Q=V.height,w=D._dragContainment;"leftTop"===j?(Z=N(G($,s))||0,A=N(G($,k))||0,x=Z+P,K=A+v,"parent"===w&&(x+r>U&&(x=U-r),K+u>Q&&(K=Q-u),0>x&&(x=0),0>K&&(K=0)),J=x-Z,e=K-A,D.setPosition(x,K),O.x+=J,O.y+=e):"rightBottom"===j?(t=N(G($,y))||0,a=N(G($,i))||0,F=t-P,g=a-v,"parent"===w&&(0>F&&(F=0),0>g&&(g=0),F+r>U&&(F=U-r),g+u>Q&&(g=Q-u)),J=F-t,e=g-a,D.setPosition(F,g),O.x-=J,O.y-=e):"rightTop"===j?(t=N(G($,y))||0,A=N(G($,k))||0,F=t-P,K=A+v,"parent"===w&&(0>F&&(F=0),0>K&&(K=0),F+r>U&&(F=U-r),K+u>Q&&(K=Q-u)),J=F-t,e=K-A,D.setPosition(F,K),O.x-=J,O.y+=e):"leftBottom"===j&&(Z=N(G($,s))||0,a=N(G($,i))||0,x=Z+P,g=a-v,"parent"===w&&(0>x&&(x=0),0>g&&(g=0),x+r>U&&(x=U-r),g+u>Q&&(g=Q-u)),J=x-Z,e=g-a,D.setPosition(x,g),O.x+=J,O.y-=e),I.$39i?D.$18i.fire({kind:"betweenMove",target:D,id:D.getView().$15i}):(I.$39i=!0,D.$18i.fire({kind:"beginMove",target:D,id:D.getView().$15i}))}}},handleWindowMouseMove:function(Q){this.handleWindowTouchMove(Q)}});var I=O.widget.PanelGroup=function(b){var V=this,s=V._view=$.createView(null,V);s.style.border="1px dashed black",s.style.position="absolute",s.style.background="rgba(120, 120, 120, 0.4)",V.$48i=new O.List,V._tolerance=100,V._config=b||{hGap:0,vGap:0},V.bindHandlePanelMove=V.handlePanelMove.bind(V),V.bindHandlePanelEvent=V.handlePanelEvent.bind(V),V.invalidate()};B.def(I,X,{invalidate:function(){var C=this;C._68I||(C._68I=1,t(function(){C.validate()},50))},validate:function(){if(this._68I){delete this._68I;var T=this.$48i.get(0);if(T){var $=T.getView().parentNode;$&&(this.layoutPanels($,"leftTop"),this.layoutPanels($,"rightTop"),this.layoutPanels($,"leftBottom"),this.layoutPanels($,"rightBottom"))}}},setLeftTopPanels:function(){var L=this,p=L.$43i,E=L.$48i;p==P&&(p=L.$43i=new O.List);for(var _=0;_<arguments.length;_++){var y=arguments[_];if("string"==typeof y)p.$49i=y;else{if(y._config.flowLayout)continue;y.setPositionRelativeTo("leftTop"),p.contains(y)||p.add(y),E.contains(y)||L.add(y)}}},setRightTopPanels:function(){var J=this,g=J.$44i,s=J.$48i;g==P&&(g=J.$44i=new O.List);for(var l=0;l<arguments.length;l++){var Z=arguments[l];if("string"==typeof Z)g.$49i=Z;else{if(Z._config.flowLayout)continue;Z.setPositionRelativeTo("rightTop"),g.contains(Z)||g.add(Z),s.contains(Z)||J.add(Z)}}},setLeftBottomPanels:function(){var Z=this,k=Z.$45i,h=Z.$48i;k==P&&(k=Z.$45i=new O.List);for(var U=0;U<arguments.length;U++){var d=arguments[U];if("string"==typeof d)k.$49i=d;else{if(d._config.flowLayout)continue;d.setPositionRelativeTo("leftBottom"),k.contains(d)||k.add(d),h.contains(d)||Z.add(d)}}},setRightBottomPanels:function(){var Q=this,a=Q.$46i,F=Q.$48i;a==P&&(a=Q.$46i=new O.List);for(var V=0;V<arguments.length;V++){var A=arguments[V];if("string"==typeof A)a.$49i=A;else{if(A._config.flowLayout)continue;A.setPositionRelativeTo("rightBottom"),a.contains(A)||a.add(A),F.contains(A)||Q.add(A)}}},add:function($){if(!$._config.flowLayout){var S=this,L=S.$48i;L.contains($)||($.addEventListener(S.bindHandlePanelMove),$.addEventListener(S.bindHandlePanelEvent),L.add($))}},remove:function(K){var L=this,r=L.$48i;r.contains(K)&&(K.removeEventListener(L.bindHandlePanelMove),K.removeEventListener(L.bindHandlePanelEvent),r.remove(K),L.$43i.contains(K)&&L.$43i.remove(K),L.$44i.contains(K)&&L.$44i.remove(K),L.$45i.contains(K)&&L.$45i.remove(K),L.$46i.contains(K)&&L.$46i.remove(K))},layoutPanels:function(G,F,h){var D=this,Z=D._config,d=Z.hGap||0,S=Z.vGap||0;if(G){var W=D.$43i;if("leftBottom"===F?W=D.$45i:"rightTop"===F?W=D.$44i:"rightBottom"===F&&(W=D.$46i),!W)return;var z=W.$49i,x=d,k=S;if(G.contains(D._view)&&G.removeChild(D._view),W&&W.size()>0)for(var R=0;R<W.size();R++){var O=W.get(R),X=O.getView();G=G||X.parentNode,h!==R?O.setPosition(x,k):("leftTop"===F?(D._view.style.right="",D._view.style.bottom="",D._view.style.left=x+Y,D._view.style.top=k+Y):"leftBottom"===F?(D._view.style.right="",D._view.style.top="",D._view.style.left=x+Y,D._view.style.bottom=k+Y):"rightTop"===F?(D._view.style.left="",D._view.style.bottom="",D._view.style.right=x+Y,D._view.style.top=k+Y):"rightBottom"===F&&(D._view.style.left="",D._view.style.top="",D._view.style.right=x+Y,D._view.style.bottom=k+Y),D._view.style.width=X.offsetWidth+Y,D._view.style.height=X.offsetHeight+Y,G.insertBefore(D._view,X)),"h"===z?x+=X.offsetWidth+d:"v"===z&&(k+=X.offsetHeight+S)}}},handlePanelEvent:function(h){if("beginToggle"===h.kind||"endToggle"===h.kind||"beginRestore"===h.kind||"endMinimize"===h.kind||"endResize"===h.kind){var g=this,c=h.target,s=c.getView(),u=s.parentNode,L=g.$43i,K=g.$44i,n=g.$45i,G=g.$46i,H=g._config,$=P,E=P,Q=c.$47i;if(Q==P&&(Q=c.$47i=0),"beginToggle"===h.kind?Q=c.$47i=Q+1:"endToggle"===h.kind&&(Q=c.$47i=Q-1),L&&L.contains(c)?($="leftTop",E=L):n&&n.contains(c)?($="leftBottom",E=n):K&&K.contains(c)?($="rightTop",E=K):G&&G.contains(c)&&($="rightBottom",E=G),"beginToggle"===h.kind&&$&&1===Q){var y=U(),T=y.style,w=E.$49i,J="each";T.fontSize="0",T.position="absolute",T.width="100%","leftTop"===$?(T.left=0,T.top=0):"leftBottom"===$?(T.left=0,T.bottom=0,"v"===w&&(J="reverseEach")):"rightTop"===$?(T.right=0,T.top=0,T.textAlign="right","h"===w&&(J="reverseEach")):"rightBottom"===$&&(T.right=0,T.bottom=0,T.textAlign="right",J="reverseEach"),E[J](function(n){var S=n.getView(),r=S.style,C=U();r.position="static",C.style.textAlign="left",C.style.position="relative",C.style.display="inline-block","leftTop"===$?(C.style.marginLeft=H.hGap+Y,C.style.marginTop=H.vGap+Y):"leftBottom"===$?(C.style.marginLeft=H.hGap+Y,C.style.marginBottom=H.vGap+Y):"rightTop"===$?(C.style.marginRight=H.hGap+Y,C.style.marginTop=H.vGap+Y):"rightBottom"===$&&(C.style.marginRight=H.hGap+Y,C.style.marginBottom=H.vGap+Y),C.appendChild(S),y.appendChild(C),"h"===w?C.style.verticalAlign="leftTop"===$||"rightTop"===$?"top":"bottom":y.appendChild(o("br"))}),g.$54i=y,u.appendChild(y)}else"endToggle"===h.kind&&$&&0===Q?t(function(){u=u.parentNode.parentNode,u.removeChild(g.$54i),delete g.$54i,E.each(function(I){var Y=I.getView(),Q=Y.style;Q.position="absolute",u.appendChild(Y)}),g.layoutPanels(u,$)},30):("beginRestore"===h.kind||"endMinimize"===h.kind||"endResize"===h.kind)&&$&&g.layoutPanels(u,$)}},handlePanelMove:function(X){if(!(X.kind.indexOf("Move")<0)){var s=this,o=s._config,r=o.hGap||0,A=o.vGap||0,H=X.target,J=H._view,c=J.getBoundingClientRect(),x=c.width,M=c.height,a=x/2,w=M/2,p=J.parentNode,j=s.$43i,G=s.$44i,d=s.$45i,f=s.$46i,e=p.getBoundingClientRect(),K=s._tolerance;if("endMove"===X.kind){var h=s._corner;h&&(H.setPositionRelativeTo(h),s.layoutPanels(p,h)),delete s._corner}if("betweenMove"===X.kind){var U=e.left,S=e.top,V=e.width,L=e.height,y=c.left+x/2,n=c.top+M/2;j==P&&(j=s.$43i=new O.List),d==P&&(d=s.$45i=new O.List),G==P&&(G=s.$44i=new O.List),f==P&&(f=s.$46i=new O.List),delete s._corner,j.contains(H)?(j.remove(H),s.layoutPanels(p,"leftTop")):d.contains(H)?(d.remove(H),s.layoutPanels(p,"leftBottom")):G.contains(H)?(G.remove(H),s.layoutPanels(p,"rightTop")):f.contains(H)&&(f.remove(H),s.layoutPanels(p,"rightBottom"));var b=function(o,G){var Q=U+r,m=S+A;if(0===G.size()){var X=Q+a,v=m+w;"leftBottom"===o?v=S+L-A-w:"rightTop"===o?X=U+V-r-a:"rightBottom"===o&&(X=U+V-r-a,v=S+L-A-w);var W=y-X,z=n-v,C=Math.sqrt(W*W+z*z);if(K>C)return s._corner=o,G.add(H),s.layoutPanels(p,o,0),!0}else if(1===G.size()){var i=G.get(0),Y=i.getView().getBoundingClientRect(),j=Y.left+a,u=Y.top+w,$=Y.left+Y.width+r+a,Z=m+w,T=Q+a,R=Y.top+Y.height+A+w;"leftBottom"===o?(u=Y.top+Y.height-w,Z=S+L-A-w,R=Y.top-A-w):"rightTop"===o?(j=Y.left+Y.width-a,$=Y.left-r-a,T=U+V-r-a):"rightBottom"===o&&(j=Y.left+Y.width-a,u=Y.top+Y.height-w,$=Y.left-r-a,Z=S+L-A-w,T=U+V-r-a,R=Y.top-A-w);var E=y-j,q=n-u,l=y-$,k=n-Z,e=y-T,_=n-R,D=N(Math.sqrt(E*E+q*q)),h=N(Math.sqrt(l*l+k*k)),F=N(Math.sqrt(e*e+_*_)),M=[D,h,F];M.sort(function(p,N){return p-N});var B=M[0];if(K>B){if(s._corner=o,B===D)return G.add(H,0),s.layoutPanels(p,o,0),!0;if(B===h)return G.add(H),G.$49i="h",s.layoutPanels(p,o,1),!0;if(B===F)return G.add(H),G.$49i="v",s.layoutPanels(p,o,1),!0}}else if(G.size()>1){for(var I=P,b={},g=[],d=G.$49i,f=0;f<G.size();f++){var x=G.get(f),J=x.getView(),c=J.getBoundingClientRect(),t=c.left+a,O=c.top+w;"leftBottom"===o?O=c.top+c.height-w:"rightTop"===o?t=c.left+c.width-a:"rightBottom"===o&&(t=c.left+c.width-a,O=c.top+c.height-w),f===G.size()-1&&(I=c);var W=y-t,z=n-O,C=N(Math.sqrt(W*W+z*z));b[C]=f,g.push(C)}"leftTop"===o&&"h"===d?(X=I.left+I.width+r+a,v=m+w):"leftTop"===o&&"v"===d?(X=Q+a,v=I.top+I.height+A+w):"leftBottom"===o&&"h"===d?(X=I.left+I.width+r+a,v=S+L-A-w):"leftBottom"===o&&"v"===d?(X=Q+a,v=I.top-A-w):"rightTop"===o&&"h"===d?(X=I.left-r-a,v=m+w):"rightTop"===o&&"v"===d?(X=U+V-r-a,v=I.top+I.height+A+w):"rightBottom"===o&&"h"===d?(X=I.left-r-a,v=S+L-A-w):"rightBottom"===o&&"v"===d&&(X=U+V-r-a,v=I.top-A-w),W=y-X,z=n-v,C=N(Math.sqrt(W*W+z*z)),b[C]=f,g.push(C),g.sort(function(K,N){return K-N});var B=g[0];if(K>B)return s._corner=o,G.add(H,b[B]),s.layoutPanels(p,o,b[B]),!0}};b("leftTop",j)||b("leftBottom",d)||b("rightTop",G)||b("rightBottom",f)}}}})}("undefined"!=typeof global?global:"undefined"!=typeof self?self:"undefined"!=typeof window?window:(0,eval)("this"),Object);