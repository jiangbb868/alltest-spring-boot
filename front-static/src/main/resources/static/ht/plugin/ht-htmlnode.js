!function(w){"use strict";var z="ht",F=w[z],H=function(){return document},k=function(){return H().body},j=function(Z,o,P){Z.style.setProperty(o,P,null)},W=function(n){return H().createElement(n)},x=function(){return W("div")},V=function(){var y=W("canvas");return y},m=function(M,Y){j(M,"-webkit-transform",Y),j(M,"-ms-transform",Y),j(M,"transform",Y)},P=function(r,X){j(r,"-webkit-transform-origin",X),j(r,"-ms-transform-origin",X),j(r,"transform-origin",X)},S=function(X,U){X.appendChild(U)},r=function(Z,m){Z.removeChild(m)},p=w.parseInt,c=F.Default,n=c.getInternal(),b=Math.PI,h="white-space",M="visibility",B="left",U="top",l="width",O="height",Z="position",_="display",X="z-index",v="px",s="0 0",t="absolute",D="visible",E="hidden",K="none",i="block",Y="nowrap",f="rgba(0, 0, 0, 0.005)";c.setImage("node_dragger","data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAN9JREFUeNrsV9sNhDAMKyzQVdgARmGzrMJNUFZhAh6nfkVcG9PQgHSR8lEksJs6pnGuLCimSRzAa0yyBK9O4gy8GokU+O0kJOAwiQYg0LP1xNYDW3+0CfBYNb7VOuN4LAGpiOaYUhFDas9F2NPHDELNENJqaHgBgSQJ3ufakfQJqckERcOiK+Ae1FGWBNKGh9oX5WPpLpdNYfffijbsxTHh7VKP7388n1g1h7OKUoUuyGpJakQEuhwkZAKcDXVOdWcrOrL/feBVBHI/q8fcjE1nA9PpyHQ+NJ2Qi8A3AQYAOtS27fCoRY0AAAAASUVORK5CYII=");var A=F.graph.GraphView.prototype,e=A._42;A.adjustHtmlNodeIndex=!0,A._42=function(G,w){if(e.call(this,G,w),this.adjustHtmlNodeIndex)for(var D=this.getDataModel()._datas._as,C=D.length,T=1,z=0;C>z;z++){var U=D[z];if(U instanceof q){var i=this.getDataUI(U);j(i.$2f,X,T+""),j(i.$3f,X,T+1+""),T+=2}}};var I=F.HtmlNodeUI=function(A,U){var L=this;I.superClass.constructor.call(L,A,U);var H=L.$2f=x(),p=L.$3f=V();j(H,Z,t),j(H,M,E),j(H,h,Y),p.draggable=!1,j(p,Z,t),j(p,_,K),P(p,s),H.addEventListener("change",function(s){var i=s.target,X=i.bind||i.getAttribute("bind"),d=i.type&&"checkbox"===i.type?i.checked:i.value,q=U.getContext();X&&q&&(q[X]=d,L.$4f=JSON.stringify(q))}),["mousedown","touchstart","keydown","mousewheel","DOMMouseScroll"].forEach(function(A){H.addEventListener(A,this.$9f.bind(this))},L)};c.def(I,n.ui().NodeUI,{_visible:!0,$11f:function(){var C=this,a=C.$3f,N=C._data,Y=N.getDraggerImageWidth(),Q=N.getDraggerImageHeight(),w=N.getDraggerImage(),e=n.initContext(a);e.beginPath(),n.setCanvas(a,Y,Q),n.translateAndScale(e,0,0,1),e.clearRect(0,0,Y,Q),c.drawImage(e,c.getImage(w),0,0,Y,Q),e.restore()},_80o:function(L){I.superClass._80o.call(this,L);var k=this,F=k._data,P=F._padding,R=2*P,G=k.$2f,$=k.$3f,u=k.gv,o=u.getZoom(),s=u.getTranslateX(),w=u.getTranslateY(),c=u.getView(),H=k._83o,g=F._width,h=F._height,A=H.position,r=H.rotation,d=(g-R)/F.$5f*o,z=(h-R)/F.$6f*o,N=k._html,T=F._html,W=F.getHtmlType();if("html"===W){var Q=F.getContext()||{},Y=k.$4f,q=F.$10f,a=JSON.stringify(Q);N&&Y&&N===T&&Y===a||(k.$4f=a,k._html=T,G.innerHTML=q?q(Q):T)}else if(null!=W){var C=F.getHtml();"ht"===W&&(C=C.getView()),N&&N===C&&G.contains(N)||(k._html=C,G.innerHTML="",S(G,C))}if(!G.parentNode){var y=u.$1f;if(!y){var V=x();j(V,Z,t),j(V,X,"0"),y=u.$1f=V;var J=u._canvas.nextSibling;J?c.insertBefore(V,J):S(c,V)}S(y,G),S(y,$),F.onContentInitialized&&F.onContentInitialized(G)}if(F._scalable){var n=F.$5f,e=F.$6f;m(G,"rotate("+180*(r/b)+"deg) scale("+d+","+z+")"),j(G,l,""),j(G,O,""),j(G,B,(A.x-n/o/2)*o+s+v),j(G,U,(A.y-e/o/2)*o+w+v)}else{var Ip=p(G.style.width),wm=p(G.style.height),eb=p((g-R)*o),ej=p((h-R)*o),Ro="100%",Hd=G.children[0];j(Hd,l,Ro),j(Hd,O,Ro),(Ip!==eb||wm!==ej)&&(j(G,l,eb+v),j(G,O,ej+v),"ht"===W&&T.invalidate()),m(G,"rotate("+180*(r/b)+"deg)"),j(G,B,(A.x-eb/o/2)*o+s+v),j(G,U,(A.y-ej/o/2)*o+w+v)}var vr=k.dragRect;u.isMovable(F)&&u.isSelected(F)&&vr?(L.save(),L.fillStyle=f,L.fillRect(vr.x,vr.y,vr.width,vr.height),L.restore(),j($,B,vr.x*o+s+v),j($,U,vr.y*o+w+v),m($,"scale("+o+","+o+")"),j($,_,i),k.$11f()):j($,_,K),j(G,M,this._visible?D:E)},dispose:function(){var n=this.gv.$1f;this.$2f.parentNode===n&&n.removeChild(this.$2f),this.$3f.parentNode===n&&n.removeChild(this.$3f)},_84o:function(s){this._visible=s,j(this.$2f,M,s?D:E),j(this.$3f,_,s?i:K)},_3O:function(){var z=this,t=z.gv,g=z._data;I.superClass._3O.call(z);var k=g.getRect();t.isEditable(g)&&(z.dragRect={x:k.x+k.width+g._padding,y:k.y+10,width:g.getDraggerImageWidth(),height:g.getDraggerImageHeight()},z._68o(z.dragRect))},rectIntersects:function(l){var e=this._79o();return F.Default.intersection(e,l)?!0:void 0},$9f:function(P){var X=this.gv,M=this._data;X.sm().contains(M)&&P.stopPropagation()}});var q=F.HtmlNode=function(){q.superClass.constructor.call(this)};F.Default.def(q,F.Node,{ms_ac:["html","context","scalable","padding","draggerImage","draggerImageWidth","draggerImageHeight"],_padding:F.Default.isTouchable?12:6,_image:null,_scalable:!0,_draggerImage:"node_dragger",_draggerImageWidth:20,_draggerImageHeight:20,setHtml:function(Q){var S=this,n=S._html;S._html=Q,"html"===S.getHtmlType()&&"Handlebars"in w&&(S.$10f=Handlebars.compile(Q)),S.$13f(),S.fp("html",n,Q)},setContext:function(e){var y=this,k=y._context;y._context=e,y.fp("context",k,e),y.$13f()},setScalable:function(A){var Q=this,U=Q._scalable;Q._scalable=A,Q.fp("scalable",U,A),Q.$13f()},getHtmlType:function(){var G=this._html;return G?"string"==typeof G?"html":G.getView?"ht":"dom":null},$13f:function(){var e=this,l=e._html,s=e.$10f;if(l){var o=x(),$=!1,u=e.getHtmlType();if(j(o,Z,t),j(o,h,Y),j(o,M,E),"html"===u?(o.innerHTML=s?s(e.getContext()||{}):l,$=!0):"ht"===u?(S(o,l.getView()),$=!0):"dom"===u&&(S(o,l),$=!0),$){var d=2*e._padding;S(k(),o),e.$5f=o.scrollWidth,e.$6f=o.scrollHeight,e._width=e.$5f+d,e._height=e.$6f+d,e._originWidth=e._width,e._originHeight=e._height,r(k(),o)}}},getUIClass:function(){return F.HtmlNodeUI}})}("undefined"!=typeof global?global:"undefined"!=typeof self?self:"undefined"!=typeof window?window:(0,eval)("this"),Object);