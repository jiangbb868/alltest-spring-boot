var Topo = {
	// this is a namespace
};
/**
 * TOPO BASE
 * each page should instance such a obj, cause it contains the ht-objs, dataModel, graphView for example.
 */
Topo.OneTopo = function(dataModel, graphView, borderPanel, layout) {
	this.showAll = false;
	this.id = null;
	// this.utils = OneTopoUtil.prototype;
	this._handlers = [];
	this._features = [];
	this._disableSpecialEffectEvents = [];
	this._nodeById = {};
	this._htmlNodeInitor = null;
	this.editable = true;
	this.dataModel = dataModel; // ht datamodel
	this.graphView = graphView; // ht graphview
	this.borderPanel = borderPanel; // ht borderPanel
	this.layout = layout; // ht layout
	this._features.push({
    type: "text",
    feature: function(topo) {
      var that = this;
      var featureArray = [{
        name: "文本",
        fn: function(event, url, type, fnDefaultRender) {
          var htNode = new ht.Node();
          htNode.setImage('text');
          htNode.setPosition(topo.graphView.getLogicalPoint(event));
		  htNode.setSize(100, 50);
		  fnDefaultRender(htNode);
		  htNode.a("custom", true);
		  htNode.a('txtNode', true);
		  htNode.a("id", Date.now());

          topo.showTextEditor(htNode)
            .then(function() {
              topo.dataModel.add(htNode);
            })
            .catch(function(e) {
              console.info(e);
            });
        }
      }];
      return featureArray;
    }
	});
	this._features.push({
    type: "image",
    feature: function(topo) {
      var that = this;
      var featureArray = [{
        name: "图片",
        fn: function(event, url, type, fnDefaultRender) {
          var htNode = new ht.Node();
          htNode.setImage(url);
          htNode.setPosition(topo.graphView.getLogicalPoint(event));
		  htNode.setSize(32, 32);
		  fnDefaultRender(htNode);
		  htNode.a("custom", true);
		  htNode.a('img', true);
		  htNode.a("id", Date.now());
          topo.showImageEditor(htNode)
            .then(function() {
              topo.dataModel.add(htNode);
            })
            .catch(function(e) {
              console.info(e);
            });
        }
      }];
      return featureArray;
    }
	});
	this._features.push({
    type: 'line',
    feature: function(topo) {
      var that = this;
      var featureArray = [{
        name: "线",
        fn: function(srcHtNode, destHtNode, fnDefaultRender) {
			var htEdge = new ht.Edge(srcHtNode, destHtNode);
			var fromId = srcHtNode.getId();
			var toId =  destHtNode.getId();
			if(srcHtNode.a("id")!= null) {
				fromId = srcHtNode.a("id");
			}
			if(destHtNode.a("id")!= null) {
				toId = destHtNode.a("id");
			}
			htEdge.a("from", fromId);
			htEdge.a("to", toId);
			htEdge.a("custom", true);
			htEdge.a("id", Date.now());
			fnDefaultRender(htEdge);
			htEdge.a("type", "line");
			topo.dataModel.add(htEdge);
        }
      }];
      return featureArray;
    }
  });
}
Topo.OneTopo.prototype.init = function(conf) {
	var $parentDom = null;
	if (typeof conf.dom === 'string') {
		$parentDom = $('#' + conf.dom);
	} else {
		$parentDom = $(conf.dom);
	}
	// 设定OneTopo的构造函数
	var oneTopo = new OneTopo.constructor($parentDom, conf);
	// return Q.all(
	// 		// 初始化组件自带的feature
	// 		_(OneTopo._features)
	// 		.filter(function(feature) {
	// 			return !!feature.init;
	// 		})
	// 		.map(function(feature) {
	// 			return feature.init(oneTopo);
	// 		})
	// 		.value()
	// 	)
	// 	.then(function() {
	// 		var promises = _(OneTopo.utils)
	// 			.values()
	// 			.filter(function(util) {
	// 				return !!util.init;
	// 			})
	// 			.map(function(util) {
	// 				return util.init.call(oneTopo);
	// 			})
	// 			.value();
	// 		return Q.all(promises);
	// 	})
	// 	.then(function() {
	// 		if (conf.feature) {
	// 			return loadScriptList(conf.feature, oneTopo);
	// 		}
	// 	})
	// 	.then(function() {
	// 		oneTopo.makeDataMap();
	// 	}).thenResolve(oneTopo);
};
Topo.OneTopo.prototype.constructor = function($parentDom, conf) {
    this.$parentDom = $parentDom;
    this.id = conf.id;
    this.className = conf.className;
    this.editable = (conf.editable !== false);

    this._disableSpecialEffectEvents = [];
    this._nodeById = {};
    this.bizData = {};

    // 初始化HighTopo对象
    initHtObject(this);

    if (conf.data) {
        this.dataModel.deserialize(conf.data);
        if (conf.data.bizData) this.bizData = conf.data.bizData;
        this.makeDataMap();
        this.optimizeNodeName(this.dataModel.getDatas()._as);

        if (conf.data.zoom) this.graphView.setZoom(conf.data.zoom);
        if (conf.data.translateX) this.graphView.setTranslateX(conf.data.translateX);
        if (conf.data.translateY) this.graphView.setTranslateY(conf.data.translateY);
        if (conf.data.hideMetric) this._hideMetric = conf.data.hideMetric;
    }

    // 初始化事件
    initEvent(this);
};
Topo.OneTopo.prototype.addLine = function(htNodeFrom, htNodeTo, typeKey) {
    var that = this;
    var htLine = new ht.Edge(htNodeFrom, htNodeTo);
    var handler = _.find(OneTopo._handlers, {
        typeKey: typeKey
    });

    this.dataModel.add(htLine);

    return Q(handler.beforeAddLine())
        .then(function(lineInfo) {
            if (typeof handler.afterAddLine === 'function') {
                return handler.afterAddLine(htLine, that);
            } else if (_.isArray(handler.afterAddLine)) {
                return Q.all(_.map(handler.afterAddLine, function(
                    lineHandler) {
                    return lineHandler.call(handler, htLine, that);
                }));
            }
        })
        .catch(function(e) {
            console.info(e);
            throw new Error(e);
        });
};
Topo.OneTopo.prototype.addHandler = function(handler) {
    if (typeof handler !== 'object') {
        throwError('参数类型必须是“object”！');
    }

    if (!handler.handlerType) {
        throwError('参数对象必须有“handlerType”属性！');
    }

    function Handler() {}
    Handler.prototype = this;
    var handlerObject = new Handler();

    _.assign(handlerObject, handler);

    this._handlers.push(handlerObject);
};
Topo.OneTopo.prototype.addFeature = function(feature) {
    this._features.push(feature);
};
Topo.OneTopo.prototype.addUtil = function(processFn) {
    var m = processFn.toString().match(
        /function\s+(\S*)\s*\(([\w\s,]*)\)\s*{/);

    if (!m || !m[1]) {
        throwError('函数必须有名称！');
    }

    if (OneTopo.utils.hasOwnProperty(name)) {
        throwError('函数名称不能重复，或者出现了脚本重复加载！');
    }

    if (OneTopo.hasOwnProperty(name)) {
        throwError('函数名称保留字，不能使用！');
    }

    OneTopo.utils[m[1]] = processFn;
    if (this !== OneTopo) {
        if (processFn.init) processFn.init(this);
    }
};
Topo.OneTopo.prototype.makeDataMap = function() {
    var that = this;
    this.dataModel.each(function(data) {
        var bizDataId = OneTopo.getAttr(data, 'id');
        if (!bizDataId) return;
        that._nodeById[bizDataId] = data;
    });
};
Topo.OneTopo.prototype.addHtmlNodeInitor = function(fnInitor) {
    this._htmlNodeInitor.push(fnInitor);
};
Topo.OneTopo.prototype.showPopAdd = function(parentDom){
	var oneTopo = this;
	var pop = new Topo.OneTopoPopAdd(parentDom).init(oneTopo);
  var lineTab = pop.addTab('连线', 'line')
        .addStaticImage('fa fa-minus', '线', 'line', 'line');
        // .addStaticImage('glyphicon glyphicon-link', '链路', 'link', 'link');

  pop.addTab('图片', 'image', true);
    // pop.addTab('背景', 'background', true);
	var shapeTab = pop.addTab('形状', 'shape')
        // .addStaticImage('fa fa-minus', '线段', 'shapeLine', 'shapeLine')
        // .addStaticImage('fa fa-square-o', '方形', 'shape', 'square')
        // .addStaticImage('fa fa-circle-thin', '圆形', 'shape', 'circle')
        .addStaticImage('far fa-file-alt', '文本', 'text', 'text');

    // var collectionTab = pop.addTab('集合', 'collection')
    //     .addStaticImage('fa fa-money', '区域', 'region', 'region');

  pop.onDrop = function(type, url, label, event, w, h, fnDefaultRender) {

		oneTopo.showFeatureList(type)
			.then(function(feature) {
				return Q(feature.fn(event, url, type, fnDefaultRender));
			})
			.catch(function(e) {
				console.info(e.stack);
			});
    };

    pop.onSelect = function(tabType, picType, picId, fnDefaultRender) {
        oneTopo.enableDrawLine('line' === tabType, function(fromHtNode, toHtNode) {
			oneTopo.showFeatureList(picType)
					.then(function(feature) {
							if (feature) return Q(feature.fn(fromHtNode, toHtNode, fnDefaultRender));
					})
					.catch(function(e) {
							console.info(e.stack);
					});
        });
    };

    pop.$html.bind('clearBackground', function() {
        _.chain(oneTopo.dataModel.getDatas()._as)
            .filter(function(htData) {
                return OneTopo.isTypeOf(htData, 'background');
            })
            .each(function(background) {
                oneTopo.dataModel.remove(background);
            })
            .value();
    });

    // oneTopo.$parentDom.bind('disableLineEdit', function() {
    //     if (pop.deselect) {
    //         pop.deselect();
    //     }
    // });
    return pop;
};
//add by wangle 通过id定位节点
Topo.OneTopo.prototype.findNodeById = function (id) {
	var oneTopo = this;
	var animation = null;
	var node = oneTopo.dataModel.getDataById(id);
	if(!node) return;
	oneTopo.graphView.fitData(node, !0, 20, !0);
	animation && animation.stop(true);
	animation = ht.Default.startAnim({
		frames: 12,
		interval: 200,
		easing: function (t) { return t * t; },
		finishFunc: function () {
			animation = null;
			delete node.getStyleMap().opacity;
		},
		action: function (v, t) {
			var opacity = node.s('opacity');
			if (opacity == 1) {
				node.s('opacity', 0);
			} else {
				node.s('opacity', 1);
			}
		}
	});
}
Topo.OneTopo.prototype.showSearchInput = function(parentDom,searchAttrs=['ip']){
	var $html = $(
		'<div class="topo_search" style="position:absolute;">' +
			'<div>'+
			'<input name="search" type="text" autocomplete="off"/>'+
			'</div>'+
			'<ul class="search-wrap"></ul>'+
		'</div>'
	);

	var oneTopo = this;
	var animation = null;
	$html.find('input').on('keydown', function(event){
		if (event.keyCode == 13) {
			var txt = $(this).val().toLowerCase();
			var search_wrap = $(".search-wrap");
			search_wrap.empty();
			oneTopo.dataModel.getDatas().each(function(node) {
				var n = node.getName();
				var isInAttrs = false;
				searchAttrs && searchAttrs.forEach((val) => {
					var a = node.a(val);
					if(a && a.indexOf(txt) != -1){
						isInAttrs = true;
					}
				})
				if (txt) {
					if ((n && (n.toLowerCase().indexOf(txt) != -1)) || isInAttrs) {
						var li = $('<li></li>').addClass('search-li').attr('title', n).html(n);
						li.data('node', node);
						search_wrap.append(li);
					}
				}
			});
			$('.search-wrap').off('click');
			$('.search-wrap').on('click','.search-li',function(){
				var node = $(this).data('node');
				oneTopo.graphView.fitData(node, !0, 20, !0);
				animation && animation.stop(true);
				animation = ht.Default.startAnim({
					frames: 12,
					interval: 200,
					easing: function(t){ return t * t; },
					finishFunc: function(){
						animation = null;
						delete node.getStyleMap().opacity;
					},
					action: function(v, t){
						var opacity = node.s('opacity');
						if (opacity == 1) {
							node.s('opacity', 0);
						} else {
							node.s('opacity', 1);
						}
					}
				});
			})
		}
	});
	parentDom.append($html);
	return $html;
};

Topo.OneTopo.prototype.showTextEditor = function(htData) {
	var topo = this;
	var ShapeEditor = new Topo.OneTopoShapeEditor(true, true);
	return this.showEditor(htData, ShapeEditor, undefined, 'text')
		.then();
};
Topo.OneTopo.prototype.showLineEditor = function(htData) {
	var topo = this;
	var lineEditor = new Topo.OneTopoLineEditor(0, !0, true).init(topo);
	return this.showEditor(htData, lineEditor, undefined, 'text');
};
Topo.OneTopo.prototype.showImageEditor = function(htData, isEdit) {
	var topo = this;
	// TODO
	var imageEditor = new Topo.OneTopoImageEditor();
	if (isEdit) {
		return this.showEditor(htData, imageEditor, undefined, 'image', '编辑图片节点');
	} else {
		return this.showEditor(htData, imageEditor, undefined, 'image', '添加图片节点');
	}
};
Topo.OneTopo.prototype.showFeatureList = function(type) {
    var that = this;
    var getListPromises = _.chain(this._features)
      .filter({
        type: type
      })
      .map(function(feature) {
        return feature.feature(that);
      })
      .value();
    return Q.all(getListPromises).then(function(lists) {
      var featureList = _.flattenDeep(lists);
      if (featureList.length === 0) {
        console.info('没有功能与该图标绑定');
        return;
      } else {
        return featureList[0];
      }
    }).catch(function(e) {
      console.info(e.stack);
    });
};
Topo.OneTopo.prototype.enableDrawLine = function(enable, fnCallback) {
	var oneTopo = this;
	if (enable) {
		oneTopo.graphView.setInteractors([
			new ht.graph.DefaultInteractor(oneTopo.graphView),
			new ht.graph.TouchInteractor(oneTopo.graphView, {
				selectable: false
			}),
			new CreateEdgeInteractor(oneTopo.graphView, {
				edgeCreator: fnCallback,
				JudgeNode: JudgeNode
			}),
		]);
		$(oneTopo.graphView.getView()).css('cursor', 'crosshair');
		oneTopo.onRightClick = deselect;
	} else {
		deselect();
	}

	function deselect() {
		oneTopo.graphView.setInteractors([]);
		oneTopo.graphView.setEditable(true);
		$(oneTopo.graphView.getView()).css('cursor', 'default');
		oneTopo.onRightClick = null;
	}

	function JudgeNode(htData) {
		if (htData && htData.a && (htData.a('type') === 'alarmIcon' || htData.a('type') === 'note' || htData.a('type') === 'background')) {
			return;
		} else {
			return htData;
		}
	}
};
Topo.OneTopo.prototype.setEdgeStyle = function(htEdge, conf) {
	var that = this;
	htEdge.s('edge.offset', conf.offset || 37);
	htEdge.s('edge.width', conf.width || 1);
	htEdge.s('edge.color', conf.color || '#1890FF');
	if (conf.animation) {
	  htEdge.s({
			'flow': true,
			'flow.count': 3,
			'flow.step': 5,
			'flow.element.image': '/bmc/static/images/light.png',
			'flow.element.count': 1,
			'flow.element.autorotate': 1,
			'flow.element.max': 32,
			'flow.element.min': 16,
			'flow.element.shadow.visible': false
	  });
	} else {
	  htEdge.s({
			'flow': false,
	  });
	}
	if (conf.lineType && conf.lineType.shape) {
	  if ('straightLine' === conf.lineType.shape) {
			htEdge.s('edge.type', null);
	  } else if ('foldLine' === conf.lineType.shape) {
			htEdge.s('edge.type', 'ortho2');
			htEdge.s('edge.corner.radius', 1);
	  } else if ('hfoldLine' === conf.lineType.shape) {
			htEdge.s('edge.type', 'h.v');
			htEdge.s('edge.corner.radius', 1);
	  } else if ('vfoldLine' === conf.lineType.shape) {
			htEdge.s('edge.type', 'v.h');
			htEdge.s('edge.corner.radius', 1);
	  } else if ('curve' === conf.lineType.shape) {
			htEdge.s('edge.type', 'ortho2');
			htEdge.s('edge.corner.radius', 0);
	  } else if ('flowLink' === conf.lineType.shape) {
		htEdge.addStyleIcon("flowArrow2", {
		  position: 46,
		  keepOrien: true,
		  width: 20,
		  height: 20,
		  names: ['flowArrow2']
		});
		htEdge.addStyleIcon("flowArrow1", {
		  position: 47,
		  keepOrien: true,
		  width: 20,
		  height: 20,
		  names: ['flowArrow1']
		});
		htEdge.s({
		  'label.position': 45,
		  'label2.position': 48,
		  'source.color': conf.color,
		  'target.color': conf.color
		});
	  }
  
	  if ('flowLink' !== conf.lineType.shape) {
		htEdge.removeStyleIcon("flowArrow1");
		htEdge.removeStyleIcon("flowArrow2");
		htEdge.s('label.position', 17);
	  }
  
	  if (conf.lineType.fromArrow) {
			htEdge.addStyleIcon("fromArrow", {
				position: 15,
				keepOrien: true,
				width: 16,
				height: 8,
				names: ['fromArrow']
			});
	  } else {
			htEdge.removeStyleIcon("fromArrow");
	  }
  
	  if (conf.lineType.toArrow) {
		htEdge.addStyleIcon("toArrow", {
			position: 19,
			keepOrien: true,
			width: 16,
			height: 8,
			names: ['toArrow']
		});
	  } else {
		htEdge.removeStyleIcon("toArrow");
	  }
	}
};
Topo.OneTopo.prototype.getEdgeStyle = function(htEdge) {
	var that = this;
	var color, width, animation, lineShape, fromArrow, toArrow;
  
	color = htEdge && htEdge.s('edge.color') || ht.Style['edge.color'];
	width = htEdge && htEdge.s('edge.width') || ht.Style['edge.width'];
	animation = htEdge && htEdge.s('flow') || false;
  
	if (!htEdge) {
	  lineShape = 'straightLine';
	} else if (htEdge && htEdge.getStyleMap() && htEdge.getStyleMap().icons && htEdge.getStyleMap().icons.flowArrow1 && htEdge.getStyleMap().icons.flowArrow2) {
	  lineShape = 'flowLink';
	} else if (!htEdge.s('edge.type')) {
	  lineShape = 'straightLine';
	} else if (htEdge.s('edge.corner.radius')) {
	  lineShape = 'foldLine';
	} else {
	  lineShape = 'curve';
	}
	if (htEdge && htEdge.getStyleMap() && htEdge.getStyleMap().icons) {
	  fromArrow = !!htEdge.getStyleMap().icons.fromArrow;
	  toArrow = !!htEdge.getStyleMap().icons.toArrow;
	}
  
	var config = {
	  width: width,
	  color: color,
	  animation: animation,
	  lineType: {
		shape: lineShape,
		fromArrow: fromArrow,
		toArrow: toArrow
	  }
	};
	return config;
};
Topo.OneTopo.prototype.showEditor = function(htDatas, editor, isSelected, cookieName, title){
	var oneTopo = this;
	var defer = Q.defer();
	var $dlg = $('' +
	  '<div class="modal fade m-l-topo topo-modal" tabindex="-1" role="dialog">' +
	  '  <div class="modal-dialog" role="document">' +
	  '    <div class="modal-content">' +
		'      <div class="modal-header">' +
		'        <h4 class="modal-title">设置</h4>' +
		'        <button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
		'        <span aria-hidden="true" class="ant-modal-close-x" style="margin-top:-56px;"></span></button>' +
	  '      </div>' +
	  // ----------------------------------------------------------------------
	  '      <div class="modal-body"><div style="clear:both"></div></div>' +
	  // ----------------------------------------------------------------------
	  '      <div class="modal-footer color-pick-footer">' +
	  '        <button type="button" class="ant-btn" data-dismiss="modal">' + '取消' + '</button>' +
	  '        <button type="button" class="ant-btn ant-btn-primary" data-dismiss="modal">' + '确定' + '</button>' +
	  '      </div>' +
	  '    </div>' +
	  '  </div>' +
		'</div>');
	if (title) {
		$dlg.find('h4').text(title);
	}
	$dlg.find('.modal-body:first').prepend(editor.$html);

	if (!_.isArray(htDatas)) htDatas = [htDatas];

	try {
	  if (cookieName) {
			var _read = editor.read;
			var _write = editor.write;

			editor.read = function() {
				$.cookie.json = true;
				var config = $.cookie('OneTopoEditor_' + cookieName);
				Array.prototype.unshift.call(arguments, config);
				return _read.apply(editor, arguments);
			};

			editor.write = function() {
				var config = _write.apply(editor, arguments);
				$.cookie.json = true;
				$.cookie('OneTopoEditor_' + cookieName, config);
			};
	  }
	  if (isSelected === false) {
			editor.read();
	  } else {
			editor.read(htDatas[0]);
	  }
	  // editor.read(htDatas[0], isSelected);

	  $dlg.appendTo($('.topo-container7')) // oneTopo.graphView.getView()
		.on('hidden.bs.modal', function() {
		  $dlg.remove();
		})
		.modal({ show: true })
		.find('button.ant-btn-primary')
		.click(function() {
		  _.each(htDatas, function(htData) {
			editor.write(htData);
		  });
		  defer.resolve();
		})
		.end();
	} catch (e) {
	  defer.reject(e);
	}
	return defer.promise;
};
Topo.OneTopo.prototype.fullScreen = function() {
	// var view = this.borderPanel.getView();
	var view = $('.biz-hightopo')[0];
	if (!document.fullscreenElement &&
		!document.mozFullScreenElement &&
		!document.webkitFullscreenElement &&
		!document.msFullscreenElement) {
		if (view.requestFullscreen) {
			view.requestFullscreen();
		} else if (view.msRequestFullscreen) {
			view.msRequestFullscreen();
		} else if (view.mozRequestFullScreen) {
			view.mozRequestFullScreen();
		} else if (view.webkitRequestFullscreen) {
			view.webkitRequestFullscreen();
		}
	} else {
		if (document.exitFullscreen) {
			document.exitFullscreen();
		} else if (document.msExitFullscreen) {
			document.msExitFullscreen();
		} else if (document.mozCancelFullScreen) {
			document.mozCancelFullScreen();
		} else if (document.webkitExitFullscreen) {
			document.webkitExitFullscreen();
		}
	}
};
Topo.OneTopo.prototype.fitContent = function() {
	this.graphView.fitContent(true, 50, true);
};
Topo.OneTopo.prototype.zoomIn = function() {
	this.graphView.zoomIn(!0);
};
Topo.OneTopo.prototype.zoomOut = function() {
	this.graphView.zoomOut(!0);
};
Topo.OneTopo.prototype.layOut = function(type) {
	var oneTopo = this;
	this.layout.layout(type, function(){
		oneTopo.graphView.fitContent(true, 50, true);
	});
};
Topo.OneTopo.prototype.layOutO = function(type) {
	var oneTopo = this;
	this.layout.layout(type, function(){
		oneTopo.graphView.fitContent(true, 50, false);
	});
};
Topo.OneTopo.prototype.layOutN = function(type, callback) {
	var oneTopo = this;
	this.layout.layout(type, function () {
		oneTopo.graphView.fitContent(true, 50, true);
		callback && callback(oneTopo.dataModel.toJSON());
	});
};
Topo.getAttr = function(htData, name) {
	return htData && name && htData.getAttr && htData.getAttr(name);
};

Topo.TopoUtil = function(){};
Topo.TopoUtil.prototype.loadScriptList = function(url, context) {
	return Q($.getJSON(url))
		.then(function(handlerUrls) {
			var queue = Q();
			_.each(handlerUrls, function(url) {
				url = evalUrl(url);
				if (!url) return;
				queue = queue.then(function() {
					return $.ajax({
						url: url,
						type: 'get',
						dataType: 'text'
					});
				}).then(function(scriptText) {
					try {
						window.Function(scriptText).call(context);
					} catch (e) {
						console.info(e.stack);
					}
				}.bind(context));
			});
			return queue;
		});
};
Topo.TopoUtil.prototype.evalUrl = function(url) {
	var reg = /\$\{(.*?)\}/g;
	var name = '';
	var value = '';
	var match = null;
	while ((match = reg.exec(url))) {
		name = match[1];
		value = window[name];
		if (!value) throw new Error();
		url = url.replace(match[0], value);
	}
	return url;
};
Topo.TopoUtil.prototype.throwError = function(text) {
	console.info(text);
	throw new Error(text);
};
Topo.TopoUtil.prototype.throwWarn = function(text) {
	console.info(text);
};
Topo.TopoUtil.prototype.initEvent = function(oneTopo) {
	// 连线事件在外面监听dataModelChange
	oneTopo.dataModel.addDataModelChangeListener(function(e) {
		var bizId = OneTopo.getAttr(e.data, 'id');

		if (e.kind === 'remove') {
			oneTopo.commitRemoveData(e.data);
			if (bizId && oneTopo._nodeById[bizId]) {
				delete oneTopo._nodeById[bizId];
			}
		} else if (e.kind === 'add') {
			oneTopo._nodeById[bizId] = e.data;
		}

		oneTopo.saveTopo(false);
	});

	// 放大缩小窗口时自动刷新
	window.addEventListener('resize', function(e) {
		oneTopo.graphView.invalidate();
	}, false);

	oneTopo.graphView.addPropertyChangeListener(function(e) {
		if (e.property === 'zoom') {
			oneTopo.disableSpecialEffect('zoom');
			clearTimeout(oneTopo.zoomDelay);
			oneTopo.zoomDelay = setTimeout(function() {
				oneTopo.enableSpecialEffect('zoom');
			}, 700);
		}
	});

	oneTopo.graphView.addInteractorListener(function(e) {
		if (e.kind === 'endEditRect') {
			if (e.data.getAttaches()) {
				e.data.getAttaches().each(function(htData) {
					var size = Math.min(e.data.getWidth(), e.data.getHeight()) * 0.45;
					if (htData.a('type') === 'alarmIcon') {
						var pos = e.data.getPosition();
						htData.setPosition(
							pos.x + e.data.getWidth() / 2 - size / 2,
							pos.y + e.data.getHeight() / 2 - size / 2);
						htData.setSize(size, size);
					}
				});
			} else if (e.data.a('type') === 'note') {
				var metricImage = ht.Default.getImage('metric');
				e.data.a('zoomX', e.data._width / metricImage.width.func(e.data));
				e.data.a('zoomY', e.data._height / metricImage.height.func(e.data));
			}
		}
	});

	$(oneTopo.graphView.getView())
		.mousedown(function(e) {
			if (oneTopo.graphView.getDataAt(e) === undefined)
				oneTopo.disableSpecialEffect('mousedownup');
		})
		.mouseup(function(e) {
			oneTopo.enableSpecialEffect('mousedownup');
		});


	oneTopo.dataModel.addDataPropertyChangeListener(function(e) {
		var htData = e.data;
		if (!htData || !(htData instanceof ht.Data)) return;
		if (['image'].indexOf(e.property) < 0) return;

		oneTopo.optimizeNodeName(htData);
	});
};

Topo.TopoUtil.prototype.initHtObject = function(oneTopo) {
	// 数据模型
	initDataModel(oneTopo);
	// 视图
	initGraphicView(oneTopo);

	// 自动布局
	// initAutolayout(oneTopo);

	// 右键菜单
	// if (oneTopo.editable) initContextMenu(oneTopo);
	// 初始化缩略图
	// initOverview(oneTopo);
	// 初始化撤销和重做功能(Ctrl+Z, Ctrl+Y)
	// initHistory(oneTopo);
};
Topo.TopoUtil.prototype.initDataModel = function(oneTopo) {
	oneTopo.dataModel = new ht.DataModel();
	oneTopo.dataModel.enableAnimation(50);
};
Topo.TopoUtil.prototype.initGraphicView = function(oneTopo) {
	oneTopo.graphView = new ht.graph.GraphView(oneTopo.dataModel);
	oneTopo.graphView.setLayers(['background', 'shape', 'note', 'edge', 0,
		'alarm'
	]);
	oneTopo.graphView.setZoom(1);
	oneTopo.graphView.disableToolTip();
	oneTopo._enableToolTip = true;
	oneTopo.graphView.setEditable(true);
	oneTopo.graphView.enableFlow();
	// oneTopo.graphView.setEditableFunc(function(htData) {
	// 	var types = ['background', 'shape', 'text', 'card', 'shapeLine', 'region'];
	// 	var type = OneTopo.getAttr(htData, 'type');
	// 	return types.indexOf(type) >= 0;
	// });

	oneTopo.graphView.setRectEditableFunc(function(data) {
		if (data.a && data.a('type') === 'note') return false;
		else return true;
	});

	oneTopo.graphView.setRotationEditableFunc(function(data) {
		return false;
	});

	oneTopo.graphView.setSelectableFunc(function(data) {
		if (!oneTopo.editable) return false;
		if (OneTopo.getAttr(data, 'isLock')) return false;
		if (OneTopo.getAttr(data, 'selectable') === false) return false;
		else return true;
	});

	oneTopo.graphView.isLabelVisible = function(data) {
		if (data instanceof ht.Edge && oneTopo._hideMetric) return false;
		if (data instanceof ht.Node) return false;
		else return true;
	};

	oneTopo.graphView.setVisibleFunc(function(data) {
		if ((data._image === 'metric' && oneTopo._hideMetric) || (data._image === 'note' && oneTopo._hideMetric)) return false;
		else if (oneTopo.showAll) return true;
		else if (OneTopo.getAttr(data, 'isHidden')) return false;
		else return true;
	});

	// oneTopo.graphView.setHelperEditableFunc(function(data, helper) {
	// 	return false;
	// });

	oneTopo.graphView.getLabelBackground = function(data) {
		if (data instanceof ht.Edge) return ht.Style['label.background'];
		else return 'rgba(0,0,0,0)';
	};

	oneTopo.graphView.setFlowInterval(100);

	$(oneTopo.graphView.getView()).addClass(oneTopo.className);
	oneTopo.$parentDom.append(oneTopo.graphView.getView());
};
Topo.TopoUtil.prototype.initAutolayout = function(oneTopo) {
	oneTopo.autoLayout = new ht.layout.AutoLayout(oneTopo.graphView);
	oneTopo.autoLayout.isLayoutable = function(htData) {
		return OneTopo.getAttr(htData, 'selectable') !== false &&
			htData.a('type') !== 'background' &&
			(!htData.getHost || !htData.getHost() && htData.a('type') !== 'note');
	};
};
Topo.TopoUtil.prototype.initContextMenu = function(oneTopo) {
	oneTopo.contextmenu = new ht.widget.ContextMenu();

	$(oneTopo.contextmenu.getView()).css('boxShadow', 'none');
	oneTopo.contextmenu.addTo(oneTopo.graphView.getView());

	oneTopo.contextmenu.beforeShow = function(e) {

		if (!e.originEvent) return;
		var node = oneTopo.graphView.getDataAt(e);
		if (node && node.a && node.a('type') === 'note') {
			this.setItems([]);
			return;
		}
		if (oneTopo.onRightClick) {
			oneTopo.onRightClick();
			this.setItems([]);
		} else {
			var menu = this;
			var htData, selectedNodes;
			var copyData;

			htData = oneTopo.graphView.getDataAt(e);


			selectedNodes = oneTopo.dataModel.sm().getSelection().toArray();
			copyData = _.filter(selectedNodes, function(htNode) {
				if (!(htNode instanceof ht.Node)) return false;
				if (htNode.a('type') === 'note') return;
				if (!htNode.a('type') || ['background', 'region', 'shape', 'shapeLine',
						'text'
					].indexOf(htNode.a('type')) < 0) {
					return false;
				}
				return true;
			});
			// selectedNodes = _.filter(selectedNodes, function(htNode) {
			// 	return htNode.a('type') !== 'note';
			// });

			var post = {
				viewId: oneTopo.id,
				selection: _.map(selectedNodes, function(htNode) {
					htNode.setAttr('isNode', htNode instanceof ht.Node);
					htNode.setAttr('isLine', htNode instanceof ht.Edge);
					return htNode.getAttrObject();
				}),
				isBackgroundLocked: !!_.find(oneTopo.dataModel.getDatas()._as, function(
					htData) {
					return OneTopo.isTypeOf(htData, 'background') && OneTopo
						.getAttr(htData, 'isLock') === true;
				}),
				bizData: _.cloneDeep(oneTopo.bizData)
			};

			window.OneTopoCommunication.getMenu(JSON.stringify(post, function(key, value) {
					if (key === 'src' && value && value.a && value.a('type') === 'note') {
						return null;
					} else return value;
				}))
				.then(function(menuConf) {
					var insertPos = menuConf.indexOf('separator');
					var copyMenu, pasteMenu;
					menu.hide();
					menuConf = convertMenuConf(menuConf, post, selectedNodes, oneTopo);
					if (oneTopo._copyData) {
						pasteMenu = {
							label: window.lang.translate('粘贴'),
							action: function(menuItem, event) {
								oneTopo.duplicateHtData(oneTopo._copyData, e.clientX, e.clientY);
								oneTopo._copyData = null;
							}
						};
						if (insertPos >= 0) menuConf.splice(insertPos, 0, pasteMenu);
						else menuConf.push(pasteMenu);
					}
					if (copyData.length > 0) {
						copyMenu = {
							label: window.lang.translate('复制'),
							action: function() {
								oneTopo._copyData = copyData;
							}
						};
						menuConf.unshift(copyMenu);
					}
					menu.setItems(menuConf);
					menu.show(e.clientX, e.clientY);
				})
				.catch(function(e) {
					console.info(e);
					window.RiilAlert.info(e.message);
					menu.hide();
				});
		}
		this.setItems([]);
	};
	oneTopo.contextmenu.setItems([]);
};
Topo.TopoUtil.prototype.convertMenuConf = function(menuConfJson, post, selectedNodes, oneTopo) {
	_.each(menuConfJson, function(menuItemConf) {
		var action = menuItemConf.action;
		if (action) {
			menuItemConf.action = function() {
				getFunction(action).call(oneTopo, post, selectedNodes);
			};
		}
		if (menuItemConf.items) {
			menuItemConf.items = convertMenuConf(menuItemConf.items, post,
				selectedNodes, oneTopo);
		}
	});
	return menuConfJson;
};
Topo.TopoUtil.prototype.getFunction = function (action) {
	var path = action.split('.');
	var fnResult = window;
	_.each(path, function(name) {
		fnResult = fnResult[name];
		if (!fnResult) throw new Error('在转换右键在菜单的配置信息时，无法找到"' + action + '"');
	});
	return fnResult;
};
Topo.TopoUtil.prototype.initOverview = function(oneTopo) {
	oneTopo.overview = new ht.graph.Overview(oneTopo.graphView);
	oneTopo.overview.setContentBackground('#205e8c');
	var $overviewParent = $(
			'<div class="map_max" style="width:400px; height: 300px; visibility:hidden;">' +
			'<a class="icon icon_minus"></a>' +
			'</div>' +
			'<div class="map_min"><a class="icon icon_plus"></a></div>'
		)
		.css({
			position: 'absolute',
			right: 0,
			bottom: 0,
		})
		.appendTo(oneTopo.$parentDom);
	$(oneTopo.overview.getView()).css({
		textAlign: 'left',
		marginTop: 30,
		marginLeft: 5,
		width: 390,
		height: 265
	});
	$($overviewParent[0]).append(oneTopo.overview.getView())
		.find('a:first').click(function() {
			$(this).parent().css('visibility', 'hidden');
			$($overviewParent[1]).css('visibility', 'visible');
		});
	$($overviewParent[1]).find('a:first').click(function() {
		$(this).parent().css('visibility', 'hidden');
		$($overviewParent[0]).css('visibility', 'visible');
	});

	$(oneTopo.overview.getView())
		.find('canvas:first')
		.css('backgroundColor', '#2f709f');
};
Topo.TopoUtil.prototype.initHistory = function(oneTopo) {
	oneTopo.historyManager = new ht.HistoryManager(oneTopo.dataModel);
	window.addEventListener('keydown', function(e) {
		if (e.ctrlKey) {
			if (e.keyCode == 90) { //ctrl + z
				oneTopo.historyManager.undo();
			} else if (e.keyCode == 89) { //ctrl + y
				oneTopo.historyManager.redo();
			}
		}
	});
};
/**
 * @param oneTopo topoObject
 * @param top default 16
 * @param left default 16
 * 
 */
Topo.ToolBar = function(oneTopo,top=16,left=16) {
	this.oneTopo = oneTopo;
	this.$html = $('<div class="tp_toolbar" style="position:absolute;top:'+top+'px;left:'+left+'px;"><div class="tp_tb_t">' +
                   '     <div class="tp_tb_m">' +
									 '       <ul></ul></div></div></div>');
	// this.$html.draggable({
	// 	handle: '.tp_tb_t',
	// 	opacity: 0.65,
	// 	cancel: 'a,input,textarea,button,select,option',
	// 	containment: 'parent',
	// 	cursor: 'move'
	// });
}
Topo.ToolBar.prototype = {
	constructor: Topo.ToolBar,
	init: function(callback) {
		callback(this);
	},
	addToDom: function(target) {
		this.$html.appendTo(target);
		this.$html.disableSelection();
	},
	hideToolbar: function() {
		this.$html.hide();
	},
	showToolbar: function() {
		this.$html.show();
	},
	addButton: function(className, title, fn, showCorner, toggleClz) {
		var that = this;
		if (title == '资源面板') {
			var clz = 'respanel';
		} else if (title == '配置关系') {
			var clz = 'relation';
		} else if (title == '保存') {
			var clz = 'save';
		} else if (title == '全屏') {
			var clz = 'fullscreen';
		} else {
			var clz = '';
		}
		var $btn = $('<li class="'+ clz + '"><i></i>' + (showCorner?'<span class="rb-arrow"></span>':'') + '</li>')
			.find('i')
			.addClass(className)
			.attr('title', title)
			.end()
			.appendTo(that.$html.find("ul:first"));
		if (fn && typeof fn === 'object') { // popup event
			$btn.click(function(){
				that.tgClass($btn);
				fn.toggle();
				that.hidePopBut(fn._$html);
				if (fn._$html) that._fixPop($btn, fn._$html);
			}).data('pop', fn);
		} else if (fn && typeof fn === 'function') { // click event
			$btn.click(function(){
				if (toggleClz) {
					var elt = $btn.find('i');
					var title = elt.attr('title');
					if (title == '配置关系') {
						elt.attr('title', '退出配置')
							.removeClass('icon-settings')
							.addClass('icon-logout');
					} else if (title == '退出配置') {
						elt.attr('title', '配置关系')
							.removeClass('icon-logout')
							.addClass('icon-settings');
					}
				}
				fn();
			});
		}
	},
	addMenu: function(className, title) {
		var that = this;
		var $menu = that._renderMenu();
		var sub = {
			addButton: function (className, title, fn) {
				if (typeof fn === 'undefined') {
					fn = title;
					title = className;
					that._textMenu($menu, title, fn);
				} else {
					that._iconMenu($menu, className, title, fn);
				}
				return sub;
			}
		};
		var $menuBtn = $('<li><i></i><span class="rb-arrow"></span></li>')
			.find('i')
			.addClass(className)
			.attr('title', title)
			.click(function (e) {
				that.tgClass($menuBtn);
				$menu.toggle();
				that.hidePopBut($menu);
				that._fixPop($menuBtn, $menu);
			})
			.end()
			.appendTo(that.$html.find("ul:first"));
		that.$html.append($menu);
		return sub;
	},
	_renderMenu: function() {
		return $('<div class="tp_tb_subm"><ul></ul></div>');
	},
	_textMenu: function($menu, title, fn) {
		var that = this;
		$('<li class="text"><span>' + title + '</span></li>')
			.click(fn)
			.appendTo($menu.find('ul:first'));
	},
	_iconMenu: function($menu, className, title, fn) {
		var that = this;
		$('<li class="icon"><a lang></a></li>')
			.click(fn)
			.find('a')
			.addClass(className)
			.attr('title', title)
			.end()
			.appendTo($menu.find('ul:first'));
	},
	_fixPop: function($btn, $pop) {
		$pop.css({top: $btn.position().top});
	},
	hidePopBut: function($except) {
		this.$html.children()
			.not($except)
			.not('.tp_tb_t')
			.hide();
	},
	createPop: function(creator, removeOnHide) {
		var that = this;
		return {
			_$html: null,
			toggle: function() {
				if (this.isVisible()) this.hide();
				else this.show();
			},
			show: function() {
				if (this.isVisible()) return;
				if (!this._$html) {
					this._$html = creator(that.$html);
				} else {
					this._$html.show();
					if (this._$html.is('.topo_search')) {
						this._$html.find('input').val('');
						this._$html.find('ul').empty();
					}
				}
			},
			hide: function() {
				if (!this.isVisible()) return;
				else if (removeOnHide) {
					this._$html.remove();
					this._$html = null;
				} else {
					this._$html.hide();
				}
			},
			isVisible: function() {
				return this._$html && this._$html.is(':visible');
			},
			html: function() {
				return this._$html;
			}
		};
	},
	showResPanel: function(show){
		this.closePop();
		var respanel = this.$html.find('.respanel');
		show ? respanel.show() : respanel.hide();
	},
	showRelation: function(show){
		this.closePop();
		var relation = this.$html.find('.relation');
		show ? relation.show() : relation.hide();
	},
	initRelation: function() {
		var relation = this.$html.find('.relation');
		relation.find('i').removeClass('icon-logout').addClass('icon-settings').attr('title', '配置关系');
	},
	showSaveBtn: function(show) {
		var save = this.$html.find('.save');
		show ? save.show() : save.hide();
	},
	closePop: function(){
		this.rmClass();
		this.$html.find('.topo_res').hide()
			.end().find('.tp_tb_subm').hide()
			.end().find('.topo_search').hide();
	},
	rmClass: function(except) {
		var lis = this.$html.find('.tp_tb_m').find('li');
		if (except) {
			lis = lis.not(except);
		}
		lis.removeClass('clicked');
	},
	tgClass: function(elt) {
		this.rmClass(elt);
		if (elt.hasClass('clicked')) {
			elt.removeClass('clicked');
		} else {
			elt.addClass('clicked');
		}
	},
	tgFullScreen: function(){
		var fullscreen = this.$html.find('.fullscreen').find('i');
		if (fullscreen.hasClass('icon-size-fullscreen')) {
			// $('.biz-hightopo').css('height', '100%');
			fullscreen.removeClass('icon-size-fullscreen').addClass('icon-size-actual');
		} else {
			// $('.biz-hightopo').css('height', '600px');
			fullscreen.removeClass('icon-size-actual').addClass('icon-size-fullscreen');
		}
	}
};
Topo.OneTopoPopAdd = function($parentDom){
	this.$parentDom = $parentDom;
};
Topo.OneTopoPopAdd.prototype = {
	constructor: Topo.OneTopoPopAdd,
	$html: null,
	_lineEditor: null,
	_shapeEditor: null,
	_textEditor: null,
	_linkEditor: null,
	_htmlText: {
		outter: _.template('' +
			'<div class="topo_res" style="position:absolute;">' +
			'  <div class="panel-group" id="popAddPanel" role="tablist" aria-multiselectable="true"></div>' +
			'</div>'
		),

		tab: _.template('' +
			'<div class="panel panel-default" type="<%=type%>" >' +
			'  <div class="panel-heading click" role="tab" id="popAddPanel_title<%=type%>" data-toggle="collapse" data-parent="#popAddPanel" href="#popAddPanel_content_<%=type%>" aria-expanded="true" aria-controls="collapseOne">' +
			'    <h4 class="panel-title"><a role="button" lang><%=label%></a></h4>' +
			'  </div>' +
			'  <div id="popAddPanel_content_<%=type%>" class="panel-collapse collapse" role="tabpanel" aria-labelledby="popAddPanel_title<%=type%>">' +
			'    <div class="panel-body"><ul style="overflow:auto;"></ul></div>' +
			'  </div>' +
			'</div>'
		),

		image: _.template('' +
			'<li class="link_a" typeKey="<%=typeKey%>" id="<%=id%>" isSystem="<%=isSystem%>">' +
			'  <img src="<%-url%>" class="img"></img>' +
			'  <span class="txt" lang><%=label%></span>' +
			// '<input type="text" value="<%=label%>"  style="width:60px;display:none;">' +
			'</li>'
		),

		systemImage: _.template('' +
			'<li class="link_a" typeKey="<%=typeKey%>" id="<%=id%>">' +
			'  <span class="<%-clz%> img"></span>' +
			'  <span class="txt" lang><%=label%></span>' +
			// '<input type="text" value="<%=label%>"  style="width:60px;display:none;">' +
			'</li>'
		),

		buttons: _.template('' +
			'<div style="overflow: hidden;">' +
			// '<a class="f-right glyphicon glyphicon-edit img16px" title="修改"></a>' +
			'<a class="f-right glyphicon glyphicon-minus-sign img16px" title="删除" lang></a>' +
			'<a class="f-right glyphicon glyphicon-plus-sign img16px" title="添加" lang style="overflow:hidden;">' +
			'<input type="file" style="opacity:0; margin-top:-20px;" id="OnetopoFileUploadInput" name="picture" title="添加" lang></a>' +
			'<a class="f-right glyphicon glyphicon-log-out img16px" title="导出" lang></a>' +
			'<a class="f-right fa fa-eraser img16px" style="margin-top:4px"  title="清除背景" lang></a>' +
			'</div>'

		)
	},
	init: function(oneTopo) {
		this.$html = $(this._htmlText.outter());
		this.$parentDom.append(this.$html);
		this._editorManager = (new Topo.OneTopoEditorManager())
			.put('line', 'line', new Topo.OneTopoLineEditor(0, !0).init(oneTopo), 'line')
		// 	.put('line', 'link', window.OneTopoLineEditor.init(), 'link')
		// 	.put('shape', 'shapeLine', window.OneTopoShapeLineEditor.init(), 'shapeLine')
		// 	.put('shape', ['circle', 'square'], window.OneTopoShapeEditor.init(), 'shape')
			.put('shape', 'text', new Topo.OneTopoShapeEditor(true), 'text');
			// .put('image', null, new Topo.OneTopoImageEditor(), 'image');
		// 	.put('collection', 'region', window.OneTopoShapeEditor.init(true), 'region');
		var that = this;
		$(this.$html).on('shown.bs.collapse', function() {
			// that.dolayout();
		});
		this.$html.bind('fixPop', function() {
			// that.dolayout();
		});
		// $(window).resize(function() {
		// 	that.dolayout();
		// });
		return this;
	},
	addTab: function(label, tabType, isCustome) {
		var that = this;
		var $tab = $(this._htmlText.tab({
				label: label,
				type: tabType
			}))
			.appendTo(this.$html.children('.panel-group'));
		var $container = $tab.find('ul:first');


		if (isCustome) { // image or background
			// var $btns = $(this._htmlText.buttons());
			// if (tabType !== 'background') $btns.find('.fa.fa-eraser').remove();
			// $container.before($btns);
			// var $addBtn = $btns.find('a.glyphicon-plus-sign');
			// window.OneTopoCommunication.bindUploadFile($addBtn.find('input[type="file"]'), {
			// 		type: tabType
			// 	}, '.jpg,.jpeg,.bmp,.gif,.png',
			// 	function(filename, resJson) {
			// 		window.RiilAlert.toast(window.lang.translate('添加成功。'));
			// 		reloadPictures();
			// 	},
			// 	function(e) {
			// 		window.RiilAlert.info(window.lang.translate('添加失败。'));
			// 	}, true);

			// this.bindRenamePicBtn($btns, reloadPictures.bind(this));
			// this.bindDeletePicBtn($btns, reloadPictures.bind(this));
			// this.bindExportPicBtn($btns);
			// this.bindClearBackground($btns);

			reloadPictures();
		}

		if (!$tab.prev()[0]) {
			$tab.find('.panel-collapse:first').addClass('in');
		}

		// this.dolayout();

		this._editorManager.each(tabType, null, function(editor) {
			$tab.find('.panel-body:first').append(editor.$html.hide());
		});

		var ret = {
			addImage: addImage,
			addStaticImage: addStaticImage
		};
		return ret;

		function addStaticImage(className, label, picType, picId) {
			var imgHtml = that._htmlText.systemImage({
				clz: className,
				label: label,
				typeKey: picType,
				id: picId || picType
			});

			initImage($(imgHtml), picType, picId || picType);

			return ret;
		}

		function addImage(thnUrl, url, label, picType, data, picId, isSystem) {
			var imgHtml = that._htmlText.image({
				url: thnUrl,
				label: label,
				typeKey: picType,
				id: picId,
				isSystem: isSystem
			});

			initImage($(imgHtml), picType, picId, url, data);

			return ret;
		}

		function initImage($img, picType, picId, url, data) {
			$img.appendTo($container);
			if (tabType !== 'line') {
				$img.draggable({
					helper: 'clone',
					distance: 20,
					start: function(e, ui) {
						that.$html.css('visibility', 'hidden');
						$(ui.helper).css('visibility', 'visible');
					},
					stop: function(e, dragData) {
						var $img = dragData.helper.find('.img:first');
						var width = $img.width();
						var height = $img.height();
						that.$html.css('visibility', 'visible');
						that.onDrop(picType, url, picId, e, width, height, function(htDatas) {
							if (!_.isArray(htDatas)) htDatas = [htDatas];
							that._editorManager.each(tabType, picId, function(editor) {
								_.each(htDatas, function(htData) {
									editor.write(htData);
								});
							});
						});
					}
				});
			}

			// if (tabType == 'shape') { // show shape editor right now, not till click
			// 	that._editorManager
			// 		.each('shape', 'text', function(editor) {
			// 			editor.$html.show();
			// 		}, function(editor) {
			// 			editor.$html.hide();
			// 		});
			// }

			$img.click(function() {
				that.defaultLineRender = null;
				if (tabType != 'shape') {
					that._editorManager
						.each(tabType, picId, function(editor) {
							editor.$html.show();
							if (tabType === 'line') {
								that.defaultLineRender = {
									picType: picType,
									picId: picId,
									fn: function(htEdge) {
										editor.write(htEdge);
									}
								};
							}
						}, function(editor) {
							// editor.$html.hide();
						});
				}
				// that.dolayout();

				if (that.onSelect) {
					that.$html.find('li.link_a').removeClass('on');
					$img.addClass('on');
					that.onSelect(tabType, picType, picId, function(htEdges) {
						if (!_.isArray(htEdges)) htEdges = [htEdges];
						that._editorManager.each(tabType, picId, function(editor) {
							_.each(htEdges, function(htEdge) {
								editor.write(htEdge);
							});
						});
					});

					that.deselect = function() {
						if (tabType === 'line') {
							$img.removeClass('on');
							that._editorManager
								.each(tabType, picId, function(editor) {
									editor.$html.hide();
									that.defaultLineRender = null;
								});
						}
						that.deselect = null;
					};
				}
			});

			$img.data(data);
			return ret;
		}

		function reloadPictures() {
			var pictures = [
				{"type":"image","displayName":"","url":"temp/ht/images/customer_1.png","uuidkeyName":"onetopoPiclib","module":"onetopo","pictureName":"customer_1.png","isSystem":1,"thnUrl":"temp/ht/images/customer_1.png","isTHN":-1,"name":null,"id":"10","tag2":null,"tag4":null,"tag1":null,"companyId":null,"tag3":null,"endIndex":0,"pageCountSqlId":null,"pageIndex":1,"pageSize":10,"sortType":null,"customParamers":null,"pageSqlId":null,"orderBy":null,"sortColumn":null,"startIndex":0}
				,{"type":"image","displayName":"","url":"temp/ht/images/customer_2.png","uuidkeyName":"onetopoPiclib","module":"onetopo","pictureName":"customer_2.png","isSystem":1,"thnUrl":"temp/ht/images/customer_2.png","isTHN":-1,"name":null,"id":"11","tag2":null,"tag4":null,"tag1":null,"companyId":null,"tag3":null,"endIndex":0,"pageCountSqlId":null,"pageIndex":1,"pageSize":10,"sortType":null,"customParamers":null,"pageSqlId":null,"orderBy":null,"sortColumn":null,"startIndex":0}
				,{"type":"image","displayName":"","url":"temp/ht/images/customer_3.png","uuidkeyName":"onetopoPiclib","module":"onetopo","pictureName":"customer_3.png","isSystem":1,"thnUrl":"temp/ht/images/customer_3.png","isTHN":-1,"name":null,"id":"12","tag2":null,"tag4":null,"tag1":null,"companyId":null,"tag3":null,"endIndex":0,"pageCountSqlId":null,"pageIndex":1,"pageSize":10,"sortType":null,"customParamers":null,"pageSqlId":null,"orderBy":null,"sortColumn":null,"startIndex":0}
				,{"type":"image","displayName":"","url":"temp/ht/images/customer_4.png","uuidkeyName":"onetopoPiclib","module":"onetopo","pictureName":"customer_4.png","isSystem":1,"thnUrl":"temp/ht/images/customer_4.png","isTHN":-1,"name":null,"id":"9","tag2":null,"tag4":null,"tag1":null,"companyId":null,"tag3":null,"endIndex":0,"pageCountSqlId":null,"pageIndex":1,"pageSize":10,"sortType":null,"customParamers":null,"pageSqlId":null,"orderBy":null,"sortColumn":null,"startIndex":0}];
			_.each(pictures, function(picture) {
				addImage(picture.thnUrl, picture.url, picture.displayName, picture.type, picture,
								picture.id, picture.isSystem);
			});
			// demo end
			// return window.OneTopoCommunication.loadPictures(tabType)
			// 	.then(function(pictures) {
			// 		$container.children('li.link_a').remove();
			// 		_.each(pictures, function(picture) {
			// 			addImage(picture.thnUrl, picture.url, picture.displayName, picture.type, picture,
			// 				picture.id, picture.isSystem);
			// 		});
			// 	});
		}
	},
	bindDeletePicBtn: function($btns, callback) {
		var $delPicBtn = $btns.find('a.glyphicon-minus-sign');
		$delPicBtn.click(function(e) {
			var picId = $(e.target).closest('.panel-body').find('ul>.link_a.ui-draggable.on').attr('id');
			var picSystem = $(e.target).closest('.panel-body').find('ul>.link_a.ui-draggable.on').attr('isSystem');
			if (picId === undefined) {
				window.RiilAlert.info(window.lang.translate('请选择一个图片。'));
			} else {
				if (picSystem === '1') {
					window.RiilAlert.info(window.lang.translate('此图片为默认图片，禁止删除。'));
				} else {
					window.OneTopoCommunication.delPicture(picId)
						.then(function(filename) {
							window.RiilAlert.toast(window.lang.translate('删除成功。'));
							return callback();
						})
						.catch(function(e) {
							window.RiilAlert.info(window.lang.translate('删除失败。'));
						});
				}
			}
		});
	},
	bindExportPicBtn: function($btns) {
		var $expPicBtn = $btns.find('a.glyphicon-log-out');
		$expPicBtn.click(function(e) {
			var tabType = $(e.target).closest('.panel').attr('type');
			var hasCustom = $(e.target).closest('.panel').find('li[typekey="' + tabType + '"][issystem!="1"]')[0];
			if (hasCustom) {
				window.OneTopoCommunication.exportPictures(tabType);
			} else {
				window.RiilAlert.info(window.lang.translate('没有可导出的图片。'));
			}
		});
	},
	bindClearBackground: function($btns) {
		var popAdd = this;
		$btns.find('.fa.fa-eraser').click(function() {
			$(this).trigger('clearBackground');
		});
	},
	dolayout: function() {
		return;
		if (!$('.topo_res')[0]) return;
		var posY = $('.topo_res').offset().top;
		$('.topo_res .panel-default ul:visible').each(function() {
			var heightGap = $(this).parent().height() - $(this).height();
			var maxHeight = $(window).height() - posY - heightGap - 210;
			// console.log(maxHeight);
			$(this).css('maxHeight', maxHeight);
		});
	}
};
Topo.OneTopoEditorHtml = {
    template: {
      main: _.template('<div class="m_con" style="width:auto;float:none;"><div><%=items%></div></div>'),
      itemLabel: _.template('<div class="topo-label"><%-label%></div>'),
      itemInput: _.template('<div class="topo-input"><%=inputHtml%></div>')
    },

    render: function(items) {
      var template = this.template;
      var itemHtml = _.map(items, function(item, i) {
        if (i % 2) {
          return template.itemInput({
            inputHtml: item
          });
        } else {
          return template.itemLabel({
            label: item
          });
        }
      }).join('\n');
      return template.main({
        items: itemHtml
      });
    }
};
Topo.OneTopoEditorManager = function(){
	this.editors = [];
}
Topo.OneTopoEditorManager.prototype = {
	constructor: Topo.OneTopoEditorManager,
	MSG: {
		PARAM: '"type, id, editor" parameters are required while calling OneTopoEditorManager.createEditor(type, id, editor)'
	},
	_getId: function(type, id) {
		if (!type) type = '';
		if (!id) id = '';
		var editorId = type + '-' + id;
		if (editorId === '-') {
			throw new Error(this.MSG.PARAM);
		}
		return editorId;
	},

	put: function(type, ids, editor, cookieName) {
		var that = this;
		var _read = editor.read;
		var _write = editor.write;

		if (!ids) {
			that.editors.push({
				type: type,
				editor: editor
			});
		} else {
			that.editors.push({
				type: type,
				id: ids,
				editor: editor
			});
		}

		editor.read = function() {
			$.cookie.json = true;
			var config = $.cookie('OneTopoEditor_' + cookieName);
			Array.prototype.unshift.call(arguments, config);
			return _read.apply(editor, arguments);
		};

		editor.write = function() {
			var config = _write.apply(editor, arguments);
			$.cookie.json = true;
			$.cookie('OneTopoEditor_' + cookieName, config);
		};

		editor.read();

		return this;
	},
	each: function(type, id, fn, fnExclude) {
		var that = this;
		_.each(this.editors, function(editor) {
			var isMatch = that.isMatch(editor, type, id);
			if (isMatch) {
			if (fn) fn(editor.editor);
			} else if (fnExclude) fnExclude(editor.editor);
		});
		return this;
	},
	isMatch: function(editor, type, id) {
		if (type && type !== editor.type) return false;
		if (!id || !editor.id) return true;

		if (_.isArray(editor.id)) {
			return editor.id.indexOf(id) >= 0;
		} else {
			return id === editor.id;
		}
	}
};

Topo.defaultColorPickerConf = (function() {
  
	var defaultColorPickerConf = {
	  showPalette: true,
	  togglePaletteMoreText: '>>',
	  togglePaletteLessText: '<<',
	  chooseText: '确定',
	  cancelText: '取消',
	  palette: [
			["#000", "#444", "#666", "#999", "#ccc", "#eee", "#f3f3f3", "#fff"],
			["#f00", "#f90", "#ff0", "#0f0", "#0ff", "#00f", "#90f", "#f0f"],
			["#f4cccc", "#fce5cd", "#fff2cc", "#d9ead3", "#d0e0e3", "#cfe2f3", "#d9d2e9", "#ead1dc"],
			["#ea9999", "#f9cb9c", "#ffe599", "#b6d7a8", "#a2c4c9", "#9fc5e8", "#b4a7d6", "#d5a6bd"],
			["#e06666", "#f6b26b", "#ffd966", "#93c47d", "#76a5af", "#6fa8dc", "#8e7cc3", "#c27ba0"],
			["#c00", "#e69138", "#f1c232", "#6aa84f", "#45818e", "#3d85c6", "#674ea7", "#a64d79"],
			["#900", "#b45f06", "#bf9000", "#38761d", "#134f5c", "#0b5394", "#351c75", "#741b47"],
			["#600", "#783f04", "#7f6000", "#274e13", "#0c343d", "#073763", "#20124d", "#4c1130"]
	  ]
	};
  
	return defaultColorPickerConf;
})();
Topo.OneTopoLineEditor = function(sign, bWithColor, bWithTxt){
	if (!bWithTxt) {
		this.$html = $(this._htmlText.base({
			sign: sign
		}));
	} else {
		this.$html = $(this._htmlText.text() + this._htmlText.base({
			sign: sign
		}));
	}
	// this.$addition = $(this._htmlText.addition({
	//   sign: sign
	// }));
	// this.$color = this.$addition.find('[name="color' + sign + '"]');
	// this.$width = this.$addition.find('[name="width' + sign + '"]');
	// this.$animation = this.$html.find('[name="animation' + sign + '"]');
	// this.$color.spectrum(_.extend({
	//   appendTo: this.$html
	// }, window.defaultColorPickerConf));
	// this.$width.spinner();

	this.bindClick();
	// if (bWithColor) {
	//   this.$html.find('table tr:first').after(this.$addition);
	// }
	// this.read();
};
Topo.OneTopoLineEditor.prototype = {
	constructor: Topo.OneTopoLineEditor,
	$html: null,
	sign: 0,
	_htmlText: {
	  base: _.template('' +
			'<div class="m_con"><table><tbody>' +
			'<!--<tr><td width="50" height="24">' + '线状态:' + 
			'</td><td><label><input name="animation<%=sign%>" type="radio" data-size="lg" value="false" checked="checked">' + 
			'静态线' +
			'</label>' +
			' &nbsp;&nbsp;&nbsp;<label><input name="animation<%=sign%>" type="radio" data-size="lg" value="true">' + '动态线' + '</label></td></tr>-->' +
			'<tr><td height="24" class="line-title-td">' + '线型：' + '</td><td>' + 
			'<div class="line-type">' +
			'<a class="line-1" type="straightLine" toArrow="false" fromArrow="false" class="on"></a>' +
			'<a class="line-2" type="straightLine" toArrow="true" fromArrow="false"></a>' +
			// '<a type="straightLine" toArrow="true" fromArrow="true"></a>' +
			'<a class="line-3" type="hfoldLine" toArrow="false" fromArrow="false"></a>' +
			'<a class="line-4" type="hfoldLine" toArrow="true" fromArrow="false"></a>' +
			// '<a type="vfoldLine" toArrow="false" fromArrow="false"></a>' +
			// '<a type="vfoldLine" toArrow="true" fromArrow="false"></a>' +
			// '<a type="curve" toArrow="false" fromArrow="false"></a>' +
			// '<a type="curve" toArrow="true" fromArrow="false"></a>' +
			// '<a type="flowLink" toArrow="false" fromArrow="false"></a>' +
			'  </div><td></tr></tbody></table></div>'
	  ),
	  addition: _.template('' +
		'  <tr><td height="24">' + '线颜色:' + '</td><td><input type="text" name="color<%=sign%>"></td></tr>' +
		'  <tr><td height="24">' + '线粗细:' + '</td><td><input name="width<%=sign%>" class="rating" min="0" max="5" step="0.5" maxLength="1" data-size="lg" style="color:white;"></td></tr>'
	  ),
	  text: _.template('' +
		'<span class="linetitle">文本：</span><span class="line-input"><input type="text" class="linelabel" name="linelabel"/></span>')
	},
	init: function(oneTopo) {
		this.oneTopo = oneTopo;
		this.read();
		return this;
	},
	bindClick: function() {
		var that = this;
		that.$html.find('.line-type')
			.click(function(e) {
				$(e.target)
					.addClass('on')
					.siblings()
					.removeClass('on');
			});
		// spinnerLimit.init(that.$width, 0, 5);
	},
	read: function(config, htData) {
		config = htData ? this.oneTopo.getEdgeStyle(htData) : config || this.oneTopo.getEdgeStyle(htData);
		// this.$color.spectrum('set', config.color);
		// this.$width.val(config.width);
		// this.$animation.filter('[value="' + config.animation + '"]').attr('checked', 'checked');

		this.$html.find('[fromArrow=' + config.lineType.fromArrow + ']' +
			'[toArrow=' + config.lineType.toArrow + ']' +
			'[type=' + config.lineType.shape + ']').click();
	},
	write: function(htData, picId) {
		// var color = this.$color.spectrum('get').toString();
		// var width = parseFloat(this.$width.val(), 10);
		// var type = this.$animation.filter(':checked').val();
		var lineShape = this.$html.find('.on').attr('type');
		var toArrow = this.$html.find('.on').attr('toArrow') === 'true';
		var fromArrow = this.$html.find('.on').attr('fromArrow') === 'true';
		
		var txt = this.$html.find('input').val();
		if (txt && txt.trim()) {
			htData.setName(txt);
			htData.s('label.position', 17);
			htData.s('label.background', '#E6F7FF');
			htData.s('label.scale', 0.8);
			htData.s('label.offset.x', 0);
			htData.s('label.offset.y', 0);
		}
		if (picId === 'link') {
			type = 'true';
		}
		// var config = {
		// 	width: width,
		// 	color: color,
		// 	animation: type === 'true',
		// 	lineType: {
		// 	shape: lineShape,
		// 	toArrow: toArrow,
		// 	fromArrow: fromArrow
		// 	}
		// };
		var config = {
			lineType: {
				shape: lineShape,
				toArrow: toArrow,
				fromArrow: fromArrow
			}
		};
		this.oneTopo.setEdgeStyle(htData, config);
		return config;
	}
};
Topo.SpinnerLimit = {
	init: function(dom, min, max) {
		var that = this;
		that.onlyNumAlpha(dom);
		that.judge(dom, min, max);
	},
	judge: function(dom, min, max) {
		var lastValue;
		dom.blur(function() {
		  var value = dom.val();
		  if (value <= max && value >= min) {
			lastValue = value;
		  }
		  if (value < min) {
			lastValue = min;
		  }
		  if (value > max) {
			lastValue = max;
		  }
		  dom.val(lastValue);
		});
	},
	onlyNumAlpha: function(dom) {
		if (typeof dom === 'string') {
		  dom = $('#' + dom);
		} else {
		  dom = $(dom);
		}
		$(dom).keypress(function(event) {
		  var eventObj = event || e;
		  var keyCode = eventObj.keyCode || eventObj.which;
		  if (keyCode >= 48 && keyCode <= 57)
			return true;
		  else
			return false;
		}).focus(function() {
		  dom.attr('imeMode', 'disabled');
		}).bind("paste", function() {
		  var clipboard = clipboardData.getData("Text");
		  if (/^\d+$/.test(clipboard))
			return true;
		  else
			return false;
		});
	}
};
Topo.createUUID = function(len, radix) {
	var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split(''),
		uuid = [],
		i;
	radix = radix || chars.length;
	if (len) {
		// Compact form
		for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random() * radix];
	} else {
		// rfc4122, version 4 form
		var r;
		// rfc4122 requires these characters
		uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
		uuid[14] = '4';
		// Fill in random data.  At i==19 set the high bits of clock sequence as
		// per rfc4122, sec. 4.1.5
		for (i = 0; i < 36; i++) {
			if (!uuid[i]) {
				r = 0 | Math.random() * 16;
				uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
			}
		}
	}
	return uuid.join('');
};
Topo.OneTopoShapeEditor = function(bWithText, bWithContent){
	this.bWithText = bWithText;
	this.bWithContent = bWithContent;

	var items = this._items.base.slice();
	if (this.bWithText) items.push(this._items.text.slice());
	if (this.bWithContent) items.unshift(this._items.content.slice());
	this.$html = $(Topo.OneTopoEditorHtml.render(_.flattenDeep(items)));

	this.$width = this.$html.find('[name="width"]');
	this.$fillcolor = this.$html.find('[name="fillcolor"]');
	this.$linecolor = this.$html.find('[name="linecolor"]');
	this.$width.spinner();
	// this.$fillcolor.spectrum(_.extend({
	// 	showAlpha: true,
	// 	appendTo: this.$html,
	// 	containerClassName: 'colorPiker'
	// }, window.defaultColorPickerConf));
	// this.$linecolor.spectrum(_.extend({
	// 	showAlpha: this.bWithText,
	// 	appendTo: this.$html,
	// 	containerClassName: 'colorPiker'
	// }, window.defaultColorPickerConf));

	if (this.bWithText) {
		this.$fontsize = this.$html.find('[name="fontsize"]');
		this.$fontcolor = this.$html.find('[name="fontcolor"]');

		this.$fontsize.spinner();
		this.$fontcolor.spectrum(_.extend({
			appendTo: this.$html,
			// containerClassName: 'colorPiker'
		}, Topo.defaultColorPickerConf));

		if (this.bWithContent) {
			this.$content = this.$html.find('[name="content"]');
		}
		Topo.SpinnerLimit.init(this.$fontsize, 6, 36);

		this.$content = this.$html.find('textarea');
	}
	this.read();
	Topo.SpinnerLimit.init(this.$width, 0, 5);
};
Topo.OneTopoShapeEditor.prototype = {
	$html: null,
	_items: {
		base: [
		  // ['形状填充:', '<input type="text" name="fillcolor">'],
		  // ['形状颜色:', '<input type="text" name="linecolor">'],
		  ['轮廓粗细：', '<input name="width" class="rating" min="0" max="5" maxLength="1" step="0.5" data-size="lg">']
		],
		text: [
		  ['字体大小：', '<input name="fontsize" class="rating" min="6" max="36" step="1" maxLength="2" data-size="lg"></li>'],
		  ['字体颜色：', '<input name="fontcolor" type="text"></li>']
		],
		content: ['文本内容：', '<textarea name="content" type="text" class="topo-textarea"></textarea>']
	},
	read: function(config, htData) {
		var fillcolor = htData && htData.s('shape.background') || config && config.fillcolor || ht.Style['shape.background'],
		  linecolor = htData && htData.s('shape.border.color') || config && config.linecolor || ht.Style['shape.border.color'],
		  lineWidth = htData && parseInt(htData.s('shape.border.width')) || config && config.lineWidth || parseInt(ht.Style['border.width']);
  
		this.$width.val(lineWidth);
		this.$fillcolor.spectrum('set', fillcolor);
		this.$linecolor.spectrum('set', linecolor);
  
  
		if (this.bWithText) {
		  var fontsize = htData && parseInt(htData.s('label.font')) || config && config.fontsize || parseInt(ht.Default.labelFont);
		  var fontcolor = htData && htData.s('label.color') || config && config.fontcolor || ht.Default.labelColor;
  
		  this.$fontsize.val(fontsize);
		  this.$fontcolor.spectrum('set', fontcolor);
  
		  if (this.bWithContent) {
			var content = Topo.getAttr(htData, 'text') || '';
			this.$content.val(content);
		  }
		}
	},
	write: function(htData) {
		var config = {};
  
		var fillcolor = this.$fillcolor.spectrum('get').toString();
		var linecolor = this.$linecolor.spectrum('get').toString();
		var lineWidth = parseInt(this.$width.val(), 10);
  
		// var color = tinycolor(fillcolor);
		// if (color.getAlpha() === 0) {
		//   color.setAlpha(0.01);
		//   fillcolor = color.toString();
		// }
		
		if (lineWidth != 0) {
			fillcolor = '#E6F7FF';
		} else {
			fillcolor = 'transparent';
		}

		htData.s({
		  'shape.background': fillcolor,
		  'shape.border.color': '#1890FF', //linecolor,
		  'shape.border.width': lineWidth
		});
  
		config.fillcolor = fillcolor;
		config.linecolor = linecolor;
		config.lineWidth = lineWidth;
		if (this.bWithText) {
		  var fontsize = parseInt(this.$fontsize.val(), 10);
			var fontcolor = this.$fontcolor.spectrum('get').toString();
			fontcolor = tinycolor(fontcolor).toHexString();
			var font = function() {
			var fontData = ht.Default.labelFont.split(' ').slice(1);
			fontData.unshift(fontsize + 'px');
				return fontData.join(' ');
		  }();
		  htData.s({
				'label.font': font,
				'label.color': fontcolor
		  });
  
		  config.fontsize = fontsize;
		  config.fontcolor = fontcolor;
  
		  if (this.bWithContent) {
				var content = this.$content.val();
				htData.setAttr('text', content);
			}
			
			htData.setAttr('text', this.$content.val());
		}
		return config;
	}
};
Topo.OneTopoImageEditor = function(){
	var items = this._items.base.slice();
	this.$html = $(Topo.OneTopoEditorHtml.render(_.flattenDeep(items)));
	this.$nodeName = this.$html.find('[name="nname"]');
};
Topo.OneTopoImageEditor.prototype = {
	$html: null,
	_items: {
		base: ['显示名称：', '<input name="nname" type="text" placeholder="显示名称为空则不显示名称"/>']
	},
	read: function(config, htData) {
		var name = htData.getName();
		if (name) {
			this.$html.find('input').val(name);
		}
	},
	write: function(htData) {
		htData.setName(this.$nodeName.val());
	}
};

Topo.OneTopoImageEditorN = function(bSelectPic) {
	var that = this;
	this.$html = $(this._htmlText.base());
	this.$panel = $(this._htmlText.panel());
	this.$text = $(this._htmlText.text());
	this.$fontsize = this.$html.find('[name="fontsize"]');
	this.$imgsize = this.$html.find('[name="imgsize"]');
	this.$fontcolor = this.$html.find('[name="fontcolor"]');
	this.$fontsize.spinner({
		numberFormat: "n"
	});
	this.$imgsize.spinner();
	this.$fontcolor.spectrum(_.extend({
		appendTo: this.$html
	}, defaultColorPickerConf));

	this.read();
	if (bSelectPic) {
		this.bindEvent();
		this.$html.find('[name="fontcolor"]').parent().parent().parent().append(this.$text);
		// this.reloadPicture();
	}
	Topo.SpinnerLimit.init(that.$imgsize, 32, 128);
	Topo.SpinnerLimit.init(that.$fontsize, 9, 20);
};
Topo.OneTopoImageEditorN.prototype = {
	$html: null,
	_htmlText: {
		base: _.template('' +
			'<table width="100%" border="0">' +
			'<tbody>' +
			'  <tr>' +
			'    <td width="85" height="24">' + '图片大小：' + '</td>' +
			'    <td><input name="imgsize" value=32 class="rating" min="32" max="128" step="8" data-size="lg" maxLength="3"  style="color:white;"></td>' +
			'  </tr>' +
			'  <tr>' +
			'    <td height="24">' + '节点文字大小：' + '</td>' +
			'    <td><input name="fontsize" value=12 class="rating" min="9" max="20" step="1" data-size="lg" maxLength="2" style="color:white;width:60px"></td>' +
			'  </tr>' +
			'  <tr>' +
			'    <td height="24">' + '节点文字颜色：' + '</td>' +
			'    <td ><input name="fontcolor" type="text"></td>' +
			'  </tr>' +
			'</tbody></table>'
		),
		text: _.template('' +
			'<tr>' +
			'  <td height="24">' + '图标:' + '</td>' +
			'  <td>' +
			'    <div class="link_a">' +
			'     <img src=# style="width:40px;height:40px" name="priImg"></img>' +
			'    </div>' +
			'    <div style="padding-top:6px;"><button type="button" class="btn_a btn-primary ml8">' + '更改图标' + '</button>' +
			'    <br><span class="glyphicon glyphicon-info-sign ml8"></span>' + '要求256*256的PNG格式图标。' + '</div>' +
			'  </td>' +
			'</tr>'
		),
		panel: _.template('' +
			'<div class="t_n01" style="position:absolute; left:169px; height:200px;display:none; width:297px; overflow:auto; padding:10px;">' +
			'  <ul>' +
			'    <li class="link_add on">' +
			'      <span class="glyphicon glyphicon-plus img"></span>' +
			'      <span class="txt" title="' + '上传新图标' + '">' + '添加图标' + '</span>' +
			'      <input type="file" style="opacity:0; margin-top:-60px;height:60px;width:60px;" id="OnetopoFileUploadInput" name="picture">' +
			'    </li>' +
			'  </ul>' +
			'</div>'
		),
		nodeImage: _.template('' +
			'<li class="link_a" typeKey="<%=typeKey%>" id="<%=id%>">' +
			'  <img src="<%-url%>" class="img"></img>' +
			'  <span class="txt" ><%=label%></span>' +
			'</li>'
		)
	},
	bindEvent: function() {
		var that = this;
		that.$text.find('.glyphicon-info-sign').parent().append(that.$panel);
		that.$text.find('.btn-primary').click(function() {
			that.$panel.toggle();
		});
		// OneTopoCommunication.bindUploadFile(that.$panel.find('input[type="file"]'), {
		// 		type: 'image'
		// 	}, '.png',
		// 	function(json) {
		// 		RiilAlert.toast(lang.translate('添加成功。'));
		// 		that.reloadPicture(json);
		// 		that.$panel.hide();
		// 	},
		// 	function(e) {
		// 		RiilAlert.info(lang.translate('添加失败。'));
		// 	});
	},
	// reloadPicture: function(json) {
	// 	var that = this;
	// 	if (!!json && json[0]) {
	// 		json = json[0];
	// 		if (!!json.url) {
	// 			if (!json.thnUrl) {
	// 				that.$text.find('[name="priImg"]').attr('src', json.url);
	// 				that.$text.find('[name="priImg"]').attr('bigSrc', json.url);
	// 			} else {
	// 				that.$text.find('[name="priImg"]').attr('src', json.thnUrl);
	// 				that.$text.find('[name="priImg"]').attr('bigSrc', json.url);
	// 			}
	// 		}

	// 	}
	// 	return OneTopoCommunication.loadPictures('image')
	// 		.then(function(pictures) {
	// 			that.$panel.children('ul:first').find('[typekey="image"]').remove();
	// 			_.each(pictures, function(picture) {
	// 				that.addImage(picture.thnUrl, picture.url, picture.displayName, picture.type,
	// 					picture,
	// 					picture.id);
	// 			});
	// 		});

	// },
	addImage: function(thnUrl, url, label, imgType, data, id) {
		var that = this;
		var $img = $(that._htmlText.nodeImage({
				url: thnUrl,
				typeKey: imgType,
				label: label,
				id: id
			})).appendTo(that.$panel.find('ul'))
			.click(function() {
				that.$panel.hide();
				that.$text.find('[name="priImg"]')
					.attr('src', thnUrl || '')
					.attr('bigSrc', url || '');
			});
	},
	read: function(config, htData) {
		var that = this;
		var fontsize = htData && parseFloat(htData.s('label.font')) || config && config.fontsize || parseFloat(ht.Default.labelFont),
			fontcolor = htData && htData.s('label.color') || config && config.fontcolor || ht.Default.labelColor,
			imgsize = htData && htData.getSize().height || config && config.imgsize || 32,
			imgUrl = htData && (htData.a('bufferImage') || htData.getImage());

		that.$imgsize.val(imgsize);
		that.$fontsize.val(fontsize);
		that.$fontcolor.spectrum('set', fontcolor);
		that.$text.remove();
		if (imgUrl) {
			that.$html.find('[name="fontcolor"]').parent().parent().parent().append(that.$text);
			that.$text.find('[name="priImg"]').attr('src', imgUrl);
			this.bindEvent();
			// this.reloadPicture(); to do
		}
	},
	write: function(htData) {
		if (htData._layer && (htData._layer == "background" || htData._layer == "shape"|| htData._layer == "note"))
			return;
		if (htData._image && (htData._image == 'text' || htData._image == 'note'))
			return;
		var that = this;
		var fontsize = parseFloat(that.$fontsize.val(), 10);
		var imgsize = parseFloat(that.$imgsize.val(), 10);
		var fontcolor = that.$fontcolor.spectrum('get').toString();
		htData.s({
			'label.font': fontsize + 'px arial, sans-serif，默认文字字体',
			'label.color': fontcolor
		});
		htData.setSize(imgsize, imgsize);
		if (that.$text.find('[name="priImg"]:visible')[0]) {
			if (!that.$text.find('[name="priImg"]').attr('bigSrc')) {
				htData.setImage(that.$text.find('[name="priImg"]').attr('src'));
			} else {
				htData.setImage(that.$text.find('[name="priImg"]').attr('bigSrc'));
			}
		}
		return {
			fontsize: fontsize,
			fontcolor: fontcolor,
			imgsize: imgsize
		};
	}
};

// static method
Topo.OneTopoCommunication = {
	URL: {
		ctx: window.ctx,
		module: window._module,
		menu: '/onetopo/menu/getMenu', //参数：htData序列化数组
		loadTopo: '/onetopo/view/load', //参数：viewId（拓扑图ID）
		saveTopo: '/onetopo/view/saveTopo', //参数：viewId（拓扑图ID），viewJson（拓扑图JSON字符串）
		delTopo: '/onetopo/view/delete', //参数：viewId（拓扑图ID）
		savePicture: '/onetopo/picLib/savePic', //参数：displayName（显示名称）, type（类型）, picture（图片文件）
		loadPictures: '/onetopo/picLib/loadTHN', //参数：type（图片类型）
		loadPicture: '/onetopo/picLib/load', //参数：path（图片路径）
		delPicture: '/onetopo/picLib/delete', // 参数：id（图片ID）
		updatePicture: '/onetopo/picLib/update', //参数：id（图片ID），displayName（显示名称）, picture（图片文件）
		exportPictures: '/onetopo/picLib/export'
	},
	loadTopo: function(topoId) {
		// return Q($.getJSON(this.URL.ctx + this.URL.loadTopo, {
		// 	viewId: topoId,
		// 	module: this.URL.module  //感觉没什么用
		// }));
		return Q($.ajax({
			url: this.URL.ctx + this.URL.loadTopo,
			type: "get",
			data: {
				viewId: topoId
			},
			cache: false,
			dataType: "json"
		}));
	},
	delPicture: function(picId) {
		return Q($.ajax({
				url: this.URL.ctx + this.URL.delPicture,
				type: 'post',
				data: {
					id: picId
				},
				cache: false,
				dataType: 'html'
			}))
			.then(function(response) {
				var resText = $(response).text();
				if (resText !== 'true' && response !== 'true')
					throw new Error(resText);
			});
	},
	updatePicture: function(picId, picName) {
		return Q($.ajax({
			url: this.URL.ctx + this.URL.updatePicture,
			type: 'post',
			data: {
				id: picId,
				displayName: picName
			},
			cache: false,
			dataType: 'html'
		})).then(function(response) {

			var resText = $(response).text();
			if (resText !== 'true') throw new Error(resText);
		});

	},
	saveTopo: function(topoId, topoData) {
		var queryString = $.param({
			viewId: topoId,
			module: this.URL.module
		});
		return Q($.ajax({
				url: this.URL.ctx + this.URL.saveTopo + '?' + queryString,
				type: 'post',
				data: topoData,
				contentType: 'text/plain',
				dataType: 'json'
			}))
			.then(function(res) {
				if (res.error) throw new Error(JSON.stringify(res, null, 4));
			}).catch(function(e) {
				window.RiilAlert.info(window.lang.translate('保存失败'));
				console.info(e.stack);
			});
	},
	delTopo: function(topoId) {
		var defer = Q.defer();
		setTimeout(function() {
			defer.resolve();
		}, 500);
		return defer.promise;
	},
	getMenu: function(jsonData) {
		jsonData.module = this.URL.module;
		return Q($.ajax(this.URL.ctx + this.URL.menu, {
				type: 'post',
				accepts: 'application/json',
				contentType: 'text/plain',
				data: jsonData,
				cache: false,
				dataType: 'json'
			}))
			.then(function(res) {
				if (_.isArray(res)) {
					return res;
				} else {
					throw new Error(res.errMessage, res.errCode);
				}
			});
	},
	bindUploadFile: function(dom, data, type, successUpload, uploadFailed, multi) {
		// type = '.jpg,.jpeg,.bmp,.gif,.png';
		var that = this;
		if (multi) $(dom).attr('multiple', 'multiple');
		$(dom).attr('accept', type)
			.AjaxFileUpload({
				action: that.URL.ctx + that.URL.savePicture,
				onSubmit: function(filename, files) {
					if (type.split(/,?\./).indexOf(filename.split('.').pop()) < 0) {
						window.RiilAlert.info(window.lang.translate('上传的文件类型不可接受。'));
						return false;
					}
					if (files) {
						if (_.find(files, function(file) {
								return type.split(/,?\./).indexOf(file.name.split('.').pop()) < 0;
							})) {
							window.RiilAlert.info(window.lang.translate('上传的文件类型不可接受。'));
							return false;
						}
					}
					data.displayName = filename.split('.')[0];
					data.module = that.URL.module;
					return data;
				},
				onComplete: function(filename, response) {
					var json = $.parseJSON($(response).text());

					if (json) {
						if (!_.isArray(json)) json = [json];
						successUpload(json);
					} else {
						uploadFailed(filename);
					}

				}
			});
	},
	loadPictures: function(type) {
		return Q($.getJSON(this.URL.ctx + this.URL.loadPictures, {
			type: type,
			module: this.URL.module
		}));
	},
	exportPictures: function(type) {
		var queryString = $.param({
			type: type,
			module: this.URL.module
		});

		var $exportPictures = $('#export_pictures_iframe');
		if (!$exportPictures[0]) {
			$exportPictures = $('<iframe style="visibility:hidden;" id="export_pictures_iframe"></iframe>')
				.appendTo(document.body);
		}
		$exportPictures.attr('src', this.URL.ctx + this.URL.exportPictures + '?' + queryString);
	}
};
// overview
Topo.ToggleOverview = function (graphView) {
	var self = this;
	Topo.ToggleOverview.superClass.constructor.apply(self, [graphView]);
	self._expand = false;

	var div = document.createElement("div");
	div.className = 'button expand';
	self._view.appendChild(div);

	self._view.style.setProperty("width", "24px", null);
	self._view.style.setProperty("height", "24px", null);
	self._canvas.style.setProperty("opacity", "0", null);
	self._mask.style.setProperty("opacity", "0", null);

	function handleTransitionEnd(e) {
			if (e.propertyName === "width") {
					self.invalidate();
			}
	}
	self._view.addEventListener("webkitTransitionEnd", handleTransitionEnd, false);
	self._view.addEventListener("transitionend", handleTransitionEnd, false);
	var eventName = ht.Default.isTouchable ? "touchstart" : "mousedown";
	div.addEventListener(eventName, function (e) {
			if (self._expand) {
					self._view.style.setProperty("width", "24px", null);
					self._view.style.setProperty("height", "24px", null);
					self._canvas.style.setProperty("opacity", "0", null);
					self._mask.style.setProperty("opacity", "0", null);
					div.className = 'button expand';
					self._expand = false;
			} else {
					self._view.style.setProperty("width", "", null);
					self._view.style.setProperty("height", "", null);
					self._canvas.style.setProperty("opacity", "1", null);
					self._mask.style.setProperty("opacity", "1", null);
					div.className = 'button shrink';
					self._expand = true;
			}
			self.invalidate();
			e.stopPropagation();
	});
	self.setContentBackground("white");
};
ht.Default.def(Topo.ToggleOverview, ht.graph.Overview, {});

// 文本文本
ht.Default.setImage('text', {
    width: {
      func: function(htData) {
        return htData._width || 100;
      }
    },
    height: {
      func: function(htData) {
        return htData._height || 50;
      }
    },
    comps: {
      func: function(htData) {
				var text = htData.a('text');
        var font = htData.s('label.font') || ht.Style['label.font'];
        var width = htData._width || 100;
        var height = htData._height || 50;
        var comps = [{
          type: 'rect',
          rect: [0, 0, htData._width || 100, htData._height || 50],
          background: {
            func: 'style@shape.background'
          },
          borderWidth: {
            func: 'style@shape.border.width'
          },
          borderColor: {
            func: 'style@shape.border.color'
          }
        }];

        var line = '';
        var lineIndex = 0;
        var lineHeight = ht.Default.getTextSize(font, 'bp').height;
        _.each(text, function(c) {
          var textWidth = ht.Default.getTextSize(font, line + c).width;
          if (c === '\r') return;
          if (line.length > 0 && textWidth > width - 5 || c === '\n') {
            addText();
            line += c;
          } else line += c;
        });
				
				if (!!line) addText();
        return comps;

        function addText() {
          comps.push({
            type: 'text',
            text: line,
            rect: [5, lineIndex * lineHeight + 5, width - 10, lineHeight],
            color: {
              func: 'style@label.color'
            },
            font: font,
            align: 'left',
            vAlign: 'top'
          });
          line = '';
          lineIndex++;
        }
      }
    }
});

(function() {
	function fnPoints(from, to) {
	  return function(data, view) {
		var halfWidth = data.s('edge.width') / 2 + 2,
		  top = 8 - halfWidth,
		  bottom = 42 + halfWidth,
		  centerTop = 25 - halfWidth,
		  centerBottom = 25 + halfWidth,
		  middle = from > to ? to + 20 : to - 20;
  
		if (top < 0)
		  top = 0;
		if (bottom > 50)
		  bottom = 50;
  
		return [from, top, to, centerTop, to, centerBottom, from, bottom, middle, 25, from, top];
	  };
	}

	ht.Default.setImage('toArrow', {
	  width: 80,
	  height: 50,
	  comps: [{
		type: 'shape',
		points: {
		  func: fnPoints(0, 40)
		},
		background: {
		  func: 'style@edge.color'
		}
	  }]
	});
	ht.Default.setImage('fromArrow', {
	  width: 80,
	  height: 50,
	  comps: [{
		type: 'shape',
		points: {
		  func: fnPoints(80, 40)
		},
		background: {
		  func: 'style@edge.color'
		}
	  }]
	});
	ht.Default.setImage('flowArrow1', {
	  width: 40,
	  height: 50,
	  comps: [{
		type: 'shape',
		points: {
		  func: fnPoints(40, 0)
		},
		background: {
		  func: 'style@edge.color' //'style@target.color'
		}
	  }]
	});
	ht.Default.setImage('flowArrow2', {
	  width: 40,
	  height: 50,
	  comps: [{
		type: 'shape',
		points: {
		  func: fnPoints(0, 40)
		},
		background: {
		  func: 'style@edge.color' //'style@source.color'
		}
	  }]
	});
})();