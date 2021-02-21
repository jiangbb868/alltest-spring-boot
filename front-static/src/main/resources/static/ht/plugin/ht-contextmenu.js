!function(E,Q){"use strict";var o="ht",L=E[o],H="position",V="absolute",u="px",g="left",P="top",J="innerHTML",O="className",j="width",k="height",m="0",p="▶",R="display",G="none",v="visibility",d="user-select",C="margin",e="padding",W=null,r=L.Color,h=L.Default,i=h.getInternal(),I=E.setTimeout,$=E.setInterval,F=E.clearTimeout,A=E.clearInterval,z=E.parseInt,K=h.isLeftButton,Y=h.isDragging,S=h.startDragging,b=h.getDistance,n=h.isTouchable,D=r.widgetIconHighlight,q=r.widgetIconBorder,c=r.widgetIconGradient,s=function(){return document},f=function(v,_){return v.querySelectorAll(_)},x=function(Q,$){var i=s().createElement(Q);return"ul"===Q&&(U(i,H,"relative"),U(i,C,m),U(i,e,m),U(i,"list-style",G),U(i,"box-sizing","border-box"),U(i,"-moz-box-sizing","border-box"),U(i,R,"inline-block"),U(i,"vertical-align","text-bottom"),U(i,"border","1px solid "+h.contextMenuBorderColor),U(i,"box-shadow","0 0 16px 1px "+h.contextMenuShadowColor),U(i,"overflow","hidden"),$||U(i,"border-radius",5+u)),i},_=function(N){var c=N.touches[0];return c?c:N.changedTouches[0]},M=function(){return x("div")},a=function(){return x("canvas")},U=function(M,s,o){M.style.setProperty(s,o,W)},Z=function(V,F,D){h.def(L.widget[V],F,D)},N=function(c,d){c.appendChild(d)},l=function(d,x){d.removeChild(x)},B=function(){return s().body},X=function(F,T,C,B){F.addEventListener(T,C,!!B)},T=function(f,H,a,B){f.removeEventListener(H,a,!!B)};i.addMethod(h,{contextMenuCheckIcon:{width:16,height:16,comps:[{type:"border",rect:[1,1,14,14],width:1,color:q},{type:"shape",points:[13,3,7,12,4,8],borderWidth:2,borderColor:D}]},contextMenuRadioIcon:{width:16,height:16,comps:[{type:"circle",rect:[2,2,12,12],borderWidth:1,borderColor:q},{type:"circle",rect:[4,4,8,8],borderWidth:1,borderColor:D,gradient:h.imageGradient,gradientColor:c,background:D}]},contextMenuLabelFont:(n?"16":"13")+"px arial, sans-serif",contextMenuLabelColor:"#000",contextMenuBackground:"#fff",contextMenuDisabledLabelColor:"#888",contextMenuHoverBackground:"#648BFE",contextMenuHoverLabelColor:"#fff",contextMenuSeparatorWidth:1,contextMenuSeparatorColor:"#E5E5E5",contextMenuScrollerColor1:"#FDFDFD",contextMenuScrollerColor2:"#D3D3D3",contextMenuScrollerBorderColor:"#C3C3C3",contextMenuBorderColor:"#C3C3C3",contextMenuShadowColor:"rgba(128, 128, 128, 0.5)"},!0);var y=function(M){var E=this,t=M._view;if(E.$11b=M,E.addListeners(),X(t,"contextmenu",function(_){_.preventDefault()}),!n){var P=E.$37b=E.$36b.bind(E);X(t,"mouseover",P),X(t,"mouseout",P)}};h.def(y,Q,{ms_listener:1,getView:function(){return this.$11b._view},handle_touchstart:function(U){if(h.preventDefault(U),K(U)){for(var H=this,J=H.$11b,x=H.getView(),e=x.children,i=0;i<e.length;i++){var F=e[i],v=F.$24b,k=F.$25b;if(v&&v.contains(U.target))return J.scrollUp(F),H.$28b=I(function(){H.$29b=$(function(){J.scrollUp(F)},100)},500),S(H,U),void 0;if(k&&k.contains(U.target))return J.scrollDown(F),H.$28b=I(function(){H.$29b=$(function(){J.scrollDown(F)},100)},500),S(H,U),void 0}n&&(U=_(U)),H.$30b={x:U.pageX,y:U.pageY}}},handle_mousedown:function(V){this.handle_touchstart(V)},handle_touchend:function(x){if(K(x)){var T=this,X=T.$30b,$=n?{x:_(x).pageX,y:_(x).pageY}:{x:x.pageX,y:x.pageY};if(!X||b(X,$)>1)return delete T.$30b,void 0;for(var o=T.getView(),P=T.$11b,s=x.target,D=W,c=W,y=P._items,N=o.$26b,B=0;B<N.length;B++)if(c=N[B],c.contains(s)){D=c.getAttribute("data-id");break}if(D&&y){var C=P.$17b(y,function(S){return S._id===D});if(C){var E=!1;C.disabled instanceof Function?E=C.disabled.call(P,C):C.disabled===!0&&(E=!0),E||(C.items?n&&T.$36b(c,!0):P.$1b(C,x))}}delete T.$30b}},$36b:function(n,D){if(!Y()){var Q,I=this,e=I.$11b,H=I.getView(),T=e.$20b||H.children[0],t=e.$19b,l=H.$26b,G=H.children,S=n.target,z=H.getBoundingClientRect(),c=h.getWindowInfo(),j=c.width,w=c.height,q=function(B){for(var p=0;p<G.length;p++){var z=G[p],N=new RegExp(B+"$"),D=z[O];if(D&&(N.test(D)||D.indexOf(B+" ")>=0))return z}},b=function(S){for(var U=0;U<l.length;U++){var H=l[U],L=new RegExp(S+"$"),G=H[O];if(G&&(L.test(G)||G.indexOf(S+" ")>=0))return H}},V=function(x){var $=b("menu-item"+x.$45b),a=$.getBoundingClientRect(),t=a.top-z.top,G=a.left-z.left;U(x,P,t+u),U(x,g,G+a.width+u);var R=x.getBoundingClientRect(),m=R.top,D=R.left,c=R.height,e=R.width,l=m+c+2,B=D+e+2;l>w&&U(x,P,t+w-l+u),B>j&&U(x,g,G-e+u)};if(D)Q=n;else{if("mouseover"===n.type){for(var d=0;d<l.length;d++){var A=l[d];if(A.contains(S)){Q=A;break}}if(!Q&&t){var k=t.parentNode,K=q("submenu"+t.getAttribute("data-id"));(K&&K.contains(S)||k&&k.contains(S))&&(Q=t)}}else if("mouseout"===n.type){for(var W=!1,y=n.relatedTarget,d=0;d<G.length;d++){var F=G[d];if("hidden"!==F.style.visibility&&F.contains(y)){W=!0;break}}if(W)return}!Q&&T&&(Q=b("menu-item"+(T.$45b||"NaN")))}if(Q!=t){if(t)for(var J=t;J;){J[O]=J[O].replace(" menu-item-hover",""),J[O].indexOf("disabled")<0&&(U(J,"background-color",h.contextMenuBackground),U(J,"color",h.contextMenuLabelColor));var B=q("submenu"+J.getAttribute("data-id"));B&&U(B,v,"hidden");var N=J.parentNode;J=b("menu-item"+(N.$45b||"NaN"))}if(Q){for(var s=Q,_=[];s;){s[O]+=" menu-item-hover",s[O].indexOf("disabled")<0&&(U(s,"background-color",h.contextMenuHoverBackground),U(s,"color",h.contextMenuHoverLabelColor));var p=q("submenu"+s.getAttribute("data-id"));p&&(U(p,v,"visible"),_.push(p));var N=s.parentNode;s=b("menu-item"+(N.$45b||"NaN"))}_.reverse(),_.forEach(function(T){V(T)})}}e.$19b=Q,e.$20b=Q?Q.parentNode:H.children[0]}},handle_mouseup:function(a){this.handle_touchend(a)},handleWindowTouchEnd:function(){var B=this;B.$28b!=W&&(F(B.$28b),delete B.$28b),B.$29b!=W&&(A(B.$29b),delete B.$29b),delete B.$34b,delete B.$30b,delete B.$35b},handleWindowMouseUp:function(U){this.handleWindowTouchEnd(U)},handle_mousemove:function(c){this.handle_touchmove(c)},handle_touchmove:function(e){if(!Y()&&K(e)){for(var z=this,a=z.getView().children,j=W,k=0;k<a.length;k++){var V=a[k];if(V.contains(e.target)){j=V;break}}var g=z.$30b,r=n?{x:_(e).pageX,y:_(e).pageY}:{x:e.pageX,y:e.pageY};j&&g&&b(g,r)>2&&(S(z,e),z.$34b=j,z.$35b=j.$18b)}},handleWindowTouchMove:function(z){var l=this,e=l.$11b,A=l.$34b,$=l.$35b,C=l.$30b;if(C&&A){var M=n?{x:_(z).pageX,y:_(z).pageY}:{x:z.pageX,y:z.pageY},B=M.y-C.y;B>0?e.scrollUp(A,A.$18b-($-B)):e.scrollDown(A,$-B-A.$18b)}},handleWindowMouseMove:function(W){this.handleWindowTouchMove(W)},$10b:function(p,J){p.preventDefault();for(var V=this,k=V.getView().children,S=W,Y=0;Y<k.length;Y++){var e=k[Y];if(e.contains(p.target)){S=e;break}}if(S){var O=this.$11b,w=O.getRowHeight();Math.abs(J)>.05&&(J>0?O.scrollUp(S,J*w):0>J&&O.scrollDown(S,-J*w))}},handle_mousewheel:function(E){this.$10b(E,E.wheelDelta/40)},handle_DOMMouseScroll:function(l){this.$10b(l,-l.detail)},$44b:function(G){this.getView().contains(G.target)||this.$11b.hide()},$41b:function(y){this.$11b.show(y)},$4b:function(y,K){var A=this.$11b;if(K=K||A._items,K&&K.length&&y.keyCode){var G=[y.keyCode];y.shiftKey&&G.push(16),y.ctrlKey&&G.push(17),y.altKey&&G.push(18),/mac/.test(E.navigator?E.navigator.userAgent.toLowerCase():"")?y.metaKey&&G.push(17):y.metaKey&&G.push(91),G.sort();var q=G.join(),o=A.$17b(K,function(L){if(L.key){var O=L.key.slice(0);return O.sort(),q===O.join()}});if(o){o.preventDefault!==!1&&y.preventDefault();var $=!1;o.disabled instanceof Function?$=o.disabled.call(A,o):o.disabled===!0&&($=!0),$||A.$1b(o,y)}}},$39b:function(W){var u=this,X=_(W);u.$32b={x:X.pageX,y:X.pageY}},$38b:function(p){var K=this,j=_(p);K.$31b={x:j.pageX,y:j.pageY},K.$33b=I(function(){K.$31b&&(K.$32b?b(K.$31b,K.$32b)<10&&K.$11b.show(p):K.$11b.show(p)),delete K.$33b,delete K.$31b,delete K.$32b},600)},$40b:function(){var o=this;o.$33b!=W&&(F(o.$33b),delete o.$33b,delete o.$31b,delete o.$32b)}}),L.widget.ContextMenu=function(t){var E=this,j=E._view=M();j[O]="ht-widget-contextmenu",E.setItems(t),E.$13b=new y(E),U(j,"font",h.contextMenuLabelFont),U(j,H,V),U(j,"cursor","default"),U(j,"-webkit-"+d,G),U(j,"-moz-"+d,G),U(j,"-ms-"+d,G),U(j,d,G),U(j,"box-sizing","border-box"),U(j,"-moz-box-sizing","border-box"),h.baseZIndex!=W&&U(j,"z-index",z(h.baseZIndex)+2+""),E.$3b=function(M){E.$13b.$4b(M)}},Z("ContextMenu",Q,{$5b:0,_items:W,$21b:W,_enableGlobalKey:!1,ms_v:1,enableGlobalKey:function(){var D=this,B=D._enableGlobalKey;B===!1&&(X(s(),"keydown",D.$3b),D._enableGlobalKey=!0)},disableGlobalKey:function(){this._enableGlobalKey=!1,T(s(),"keydown",this.$3b)},setItems:function(C){this._items=C},setVisibleFunc:function(i){this.$16b=i},setLabelMaxWidth:function(w){this.$43b=w},$1b:function($,V){var j=this;if("check"===$.type)$.selected=!$.selected;else if("radio"===$.type){var H=$.groupId;j.$17b(j._items,function(c){c.groupId===H&&(c.selected=!1)}),$.selected=!0}if(j.hide(),$.action)$.action.apply($.scope||j,[$,V]);else if($.href){var n=$.linkTarget||"_self";E.open($.href,n)}},getItemByProperty:function(T,M,Q){var x=this;if(Q=Q||x._items,!Q||0===Q.length)return W;var H=x.$17b(Q,function(k){return k[T]===M});return H||W},scrollUp:function(C,G){var u=this;if(G=G==W?20:G,G=z(G),0!==G){var q=0;C.$18b>G&&(q=C.$18b-G),u.$42b(C,q),C.scrollTop=q,C.$18b=q}},scrollDown:function(F,V){var O=this;if(V=V==W?20:V,V=z(V),0!==V){var n=F.$22b,D=F.$23b,p=n-D;D+F.$18b+V<n&&(p=F.$18b+V),O.$42b(F,p),F.scrollTop=p,F.$18b=p}},$42b:function(H,M){M=M==W?H.$18b:M;var j=H.$24b,Y=H.$25b;j&&(U(j,"top",M+u),0==M?U(j,R,G):U(j,R,"block")),Y&&(U(Y,"bottom",-M+u),M==H.$22b-H.$23b?U(Y,R,G):U(Y,R,"block"))},getRowHeight:function(){return this._view.querySelector(".menu-item").offsetHeight},$17b:function($,D){for(var e=0;e<$.length;e++){var K=$[e];if(D(K))return K;if(K.items){var g=this.$17b(K.items,D);if(g)return g}}},$2b:function(r,K){for(var B=this,Q=0;Q<r.length;Q++){B.$5b++;var I=r[Q];if(!B.$16b||B.$16b.call(B,I)){var i=x("li"),D=B.$5b+"";if(U(i,"white-space","nowrap"),U(i,R,"block"),"separator"===I){var Y=M();Y[O]="separator",U(Y,k,h.contextMenuSeparatorWidth+u),U(Y,"background",h.contextMenuSeparatorColor),N(i,Y)}else{I._id=D,i.setAttribute("data-id",D);var z=x("span"),o=x("span"),c=a(),n=M();if(U(z,R,"inline-block"),U(o,R,"inline-block"),c[O]="prefix",U(c,R,"inline-block"),U(c,j,"1em"),U(c,k,"1em"),U(c,"vertical-align","middle"),U(c,C,"0 0.2em"),"check"===I.type&&I.selected?c[O]+=" check-prefix":"radio"===I.type&&I.selected&&(c[O]+=" radio-prefix"),N(i,c),I.icon){var s=a();s[O]="contextmenu-item-icon",U(s,R,"inline-block"),U(s,"vertical-align","middle"),U(s,k,"1.2em"),U(s,j,"1.2em"),U(s,"margin-right","0.2em"),s.$50b=I.icon,N(z,s)}if(o[J]=I.label,N(z,o),N(i,z),n[O]="suffix",U(n,R,"inline-block"),U(n,"margin-left","1em"),U(n,"margin-right","0.4em"),U(n,"text-align","right"),U(n,"font-size","75%"),I.items&&(n[J]=p),I.suffix&&(n[J]=I.suffix),N(i,n),i[O]="menu-item menu-item"+D,U(i,"background-color",h.contextMenuBackground),U(i,"color",h.contextMenuLabelColor),U(i,e,"3px 0"),I.disabled instanceof Function){var m=I.disabled.call(B,I);m&&(i[O]+=" disabled",U(i,"color",h.contextMenuDisabledLabelColor))}else I.disabled&&(i[O]+=" disabled",U(i,"color",h.contextMenuDisabledLabelColor));if(I.items){B.$21b||(B.$21b=new L.List);var W=x("ul",B._r);W[O]="submenu"+D,W.$45b=D,U(W,v,"hidden"),U(W,H,V),N(B._view,W),B.$21b.add(W),B.$2b(I.items,W)}}N(K,i)}}},rebuild:function(){var A=this,k=A._items,V=A._view;if(V&&(V[J]="",A.$21b=W,A.$5b=0,A.$19b=W,A.$20b=W,V.$26b=W,k&&0!==k.length)){var f=x("ul",A._r);N(V,f),A.$2b(k,f)}},addTo:function(s){if(s){var Q=this,o=Q.$13b;if(Q.$12b=s,Q.$9b=function(F){o.$44b(F)},Q.$27b=function(n){o.$41b(n)},n){var S=Q.$6b=function(z){o.$38b(z)},a=Q.$7b=function(s){o.$39b(s)},G=Q.$8b=function(y){o.$40b(y)};X(s,"touchstart",S,!0),X(s,"touchmove",a),X(s,"touchend",G)}else X(s,"contextmenu",Q.$27b)}},show:function(o,Y,G){var k=this,G=G==W?!0:!1,D=k._view;if(D){if(k.invalidate(),1===arguments.length){var K=o;if(n){var l=_(K);o=l.pageX,Y=l.pageY}else o=K.pageX,Y=K.pageY}var Q=h.getWindowInfo(),z=Q.width,a=Q.height,t=Q.left,I=Q.top,p={pageX:o,pageY:Y,clientX:o-t,clientY:Y-I,target:1,originEvent:K},A=p.clientX,F=p.clientY,x=function(Y){Y.style.height=a-6+u;var P=M(),R=M(),v=function(o){U(o,H,V),U(o,"text-align","center"),U(o,j,"100%"),U(o,"font-size",10+u),U(o,"padding","2px 0"),U(o,"border","0px solid "+h.contextMenuScrollerBorderColor),U(o,"background-color",h.contextMenuScrollerColor1),o.style.backgroundImage="-webkit-linear-gradient(top, "+h.contextMenuScrollerColor1+", "+h.contextMenuScrollerColor2+")",o.style.backgroundImage="linear-gradient(to bottom, "+h.contextMenuScrollerColor1+", "+h.contextMenuScrollerColor2+")"};P[O]="menu-arrow-item menu-arrow-item-top",R[O]="menu-arrow-item menu-arrow-item-bottom",v(P),U(P,"top",m),U(P,"left",m),U(P,"border-bottom-width",1+u),P[J]="▲",v(R),U(R,"bottom",m),U(R,"left",m),U(R,"border-top-width",1+u),R[J]="▼",Y.$24b=P,Y.$25b=R,Y.$18b=Y.scrollTop,Y.$22b=Y.scrollHeight,Y.$23b=Y.clientHeight,N(Y,P),N(Y,R),k.$42b(Y)};k.beforeShow&&k.beforeShow(p);var d=k._items;if(d&&(K&&K.preventDefault(),d.length)){k.rebuild(),N(B(),D),D.$26b=f(D,".menu-item");var r=D.children[0];r.offsetHeight>a&&x(r);var $=F+(G?1:0),c=A+(G?1:0),v=function(b){for(var Y=0,n=0,F=0,W=k.$43b;F<b.children.length;F++){var Z=b.children[F];if(Z.getAttribute("data-id")){var o=Z.children[1],I=Z.children[2],l=o.children;if(W){var g=l[0];l.length>1&&(g=l[1]),g.offsetWidth>W&&(g[J]="<marquee scrollamount='3'>"+g[J]+"</marquee>",g.children[0].style.verticalAlign="text-bottom",U(g,j,W+u),U(g,R,"inline-block"))}var v=o.offsetWidth,H=I.offsetWidth;v>Y&&(Y=v),H>n&&(n=H)}}for(F=0;F<b.children.length;F++){var Z=b.children[F];if(Z.getAttribute("data-id")){var o=Z.children[1],I=Z.children[2],h=o.children[0],s=o.children[1];!s&&h.style.width&&U(h,j,Y+u),U(o,j,Y+u),U(I,j,n+u)}}};v(r);var T=F+3+D.offsetHeight,E=A+3+D.offsetWidth;T>a?U(D,P,$-(T-a)+I+u):U(D,P,$+I+u),E>z?U(D,g,c-(E-z)+t+u):U(D,g,c+t+u);var y=k.$21b;y&&y.each(function(F){v(F),F.offsetHeight>a&&x(F)}),k.$9b&&X(s(),n?"touchstart":"mousedown",k.$9b,!0),k.afterShow&&k.afterShow(p),k.$47b()}}},isShowing:function(){return this._view?this._view.parentNode!=W:!1},getRelatedView:function(){return this.$12b},hide:function(){var Q=this,e=Q._view;e&&e.parentNode&&(l(e.parentNode,e),T(s(),n?"touchstart":"mousedown",Q.$9b,!0),Q.afterHide&&Q.afterHide())},dispose:function(){var N=this,w=N.$12b,H=N._view;H&&(H.parentNode&&l(H.parentNode,H),N.disableGlobalKey(),w&&(n?(T(w,"touchstart",N.$6b,!0),T(w,"touchmove",N.$7b),T(w,"touchend",N.$8b)):T(w,"contextmenu",N.$27b)),N._view=N._items=N.$21b=N.$19b=N.$12b=N.beforeShow=N.afterShow=N.afterHide=N.$9b=N.$3b=N.$6b=N.$7b=N.$8b=N.$27b=W)},$46b:function(N,s,e,B){var W=i.initContext(N);i.translateAndScale(W,0,0,1),W.clearRect(0,0,e,B),h.drawStretchImage(W,h.getImage(s),"fill",0,0,e,B),W.restore()},$47b:function(){var n,y,z,h=this,I=h._view;if(h.isShowing()){var o=f(I,".check-prefix");for(z=0;z<o.length;z++){var K=o[z];n=K.clientWidth,y=K.clientHeight,K.$48b=n,K.$49b=y,i.setCanvas(K,n,y)}var X=f(I,".radio-prefix");for(z=0;z<X.length;z++){var t=X[z];n=t.clientWidth,y=t.clientHeight,t.$48b=n,t.$49b=y,i.setCanvas(t,n,y)}var Y=f(I,".contextmenu-item-icon");for(z=0;z<Y.length;z++){var M=Y[z];n=M.clientWidth,y=M.clientHeight,M.$48b=n,M.$49b=y,i.setCanvas(M,n,y)}}},validateImpl:function(){var _,T,G,J=this,d=J._view;if(J.isShowing()){var q=f(d,".check-prefix");for(G=0;G<q.length;G++){var z=q[G];_=z.$48b,T=z.$49b,_&&T&&J.$46b(z,h.contextMenuCheckIcon,_,T)}var k=f(d,".radio-prefix");for(G=0;G<k.length;G++){var N=k[G];_=N.$48b,T=N.$49b,_&&T&&J.$46b(N,h.contextMenuRadioIcon,_,T)}var H=f(d,".contextmenu-item-icon");for(G=0;G<H.length;G++){var S=H[G];_=S.$48b,T=S.$49b,_&&T&&J.$46b(S,h.getImage(S.$50b),_,T)}}}})}(this,Object);